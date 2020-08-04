import json
from flask import Flask
from flask import render_template, request, jsonify

import re
import operator
import itertools
from sys import stderr
file = stderr

import findspark
findspark.init()
findspark.find()

import pyspark
from pyspark import SparkContext, SparkConf
from pyspark.sql import SparkSession
conf = pyspark.SparkConf().setAppName('sparkify-capstone-web').setMaster('local')
sc = pyspark.SparkContext(conf=conf)
spark = SparkSession(sc)

from pyspark.ml.classification import GBTClassifier
from pyspark.ml.classification import GBTClassificationModel
from pyspark.ml.feature import VectorAssembler
from pyspark.sql.types import StructType, StructField, IntegerType, DoubleType

predictionsPath = "../out/predictions.parquet"

df_predictions = spark.read.parquet(predictionsPath)
model = GBTClassificationModel.load("../out/model")

# Current churn rate: We got these from ETL, should get from dataset in real app
currentTotal = 488
currentChurn = 99

# Calculate predicted churn rate
predictedTotal = df_predictions.count()
predictedChurn = df_predictions.filter(df_predictions["prediction"] == 1).count()

# Calculate rates
currentChurnRate = "{:.0f}".format(currentChurn / currentTotal * 100)
predictedChurnRate = "{:.0f}".format(predictedChurn / predictedTotal * 100)

# Take 5 users where churn is predicted
usersPredictedToChurn = df_predictions.filter(df_predictions["prediction"] == 1).take(5)
usersPredictedToChurnList = []
for row in usersPredictedToChurn:
    usersPredictedToChurnList.append(int(row["userId"]))

app = Flask(__name__)

@app.route('/')
@app.route('/index')
def index():
	""" Landing page """

	return render_template('master.html', currentChurnRate = currentChurnRate, \
		predictedChurnRate = predictedChurnRate, usersPredictedToChurn = usersPredictedToChurnList)
	
@app.route('/predict')
def predict():
	""" Predict churn for user id or accumulated values """

	query = request.args.get('query', '')

	if ("," in query):
		if (len(query) < 8):
			prediction_result = "Error, need exactly 8 comma-separated values. Example: 1.0,0.0,10,4,307,0,76200,10"
		else:
			prediction_result = predictionFromValues(query)
	else:
		prediction_result = df_predictions[df_predictions["userId"] == int(query)].select("prediction").collect()[0][0]

	return render_template(
		'prediction.html',
		query = query,
		prediction_result = prediction_result
	)

def predictionFromValues(query):

	# Split to values
	values = query.split(",")

	# Prepare dictionary for feature dataframe from web form values
	features_dict = [{
		"level_index": float(values[0]),
		"gender_index": float(values[1]),
		"thumbs_up_sum": int(values[2]),
		"thumbs_down_sum": int(values[3]),
		"nextsong_sum": int(values[4]),
		"downgrade_sum": int(values[5]),
		"length_sum": float(values[6]),
		"sessionId_count": int(values[7]),
	}]

	# Create a user row to use in VectorAssembler
	df_user_row = spark.createDataFrame(features_dict)

	# Create feature dataframe with VectorAssembler
	df_features = VectorAssembler(inputCols= \
									  ["level_index", "gender_index", "thumbs_up_sum", "thumbs_down_sum", \
									   "nextsong_sum", "downgrade_sum", "length_sum", "sessionId_count"], \
								  outputCol="features").transform(df_user_row)

	# Select features
	df_features = df_features.select("features")

	# Predict on model
	prediction = model.transform(df_features)
	return prediction.select("prediction").collect()[0][0]

def main():
	""" Run the web app """
	app.run(host='0.0.0.0', port=3001, debug=True)

if __name__ == '__main__':
	main()
