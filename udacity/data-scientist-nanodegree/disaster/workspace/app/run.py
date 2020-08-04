import json
import plotly
import pandas as pd

from nltk.stem import WordNetLemmatizer
from nltk.tokenize import word_tokenize

from flask import Flask
from flask import render_template, request, jsonify
from plotly.graph_objs import Bar
from sklearn.externals import joblib
from sqlalchemy import create_engine

import re
import operator
import itertools
from sklearn.feature_extraction.text import CountVectorizer
from nltk.corpus import stopwords

app = Flask(__name__)

def tokenize(text):
    """ Simplified tokenizer for the disaster messages. Does not need to remove stop words.
    Args:
    text: Text to tokenize.
    """
    tokens = word_tokenize(text)
    lemmatizer = WordNetLemmatizer()

    clean_tokens = []
    for tok in tokens:
        clean_tok = lemmatizer.lemmatize(tok).lower().strip()
        clean_tokens.append(clean_tok)

    return clean_tokens

def tokenizeAndRemoveStops(text):
    """ Tokenizer from original ETL, stop words and some other non-sensical (in tis context) words are being removed to build the graph.
    Normalize, tokenize, lemmatize and remove stop words.
    Args:
    text: Text to tokenize.
    """    
    lemmatizer = WordNetLemmatizer()
    # Normalize text
    # Replace non-alphanumeric characters with space, convert to lower and remove trailing spaces 
    text = re.sub("[^a-zA-Z09]", " ", text).lower().strip()
    # To be extra-clean remove double spaces
    text = re.sub("\s{2,}", " ", text)    
    # After normalization, tokenize text into tokens, remove stop words and lemmatize
    tokens = word_tokenize(text)
    # Remove stop words
    tokens = [lemmatizer.lemmatize(word) for word in tokens if word not in stopwords.words("english")]    
    tokens = [word for word in tokens if word not in ["0", "00", "000"]]    
    return tokens  

# load data
engine = create_engine('sqlite:///../data/DisasterResponse.db')
df = pd.read_sql_table('CategorizedResponse', engine)

# load model
model = joblib.load("../models/model.pickle")

# index webpage displays cool visuals and receives user input text for model
@app.route('/')
@app.route('/index')
def index():
    """ Landing page, display message entry form and some data info as graphs """
    
    # extract data needed for visuals    
    # Genres of messages
    genre_counts = df.groupby('genre').count()['message']
    genre_names = list(genre_counts.index)
    
    # Most common words of the last 1000 messages (stored in the model data files)
    cv = CountVectorizer(tokenizer = tokenizeAndRemoveStops)     
    cv_fit = cv.fit_transform(df["message"].tail(1000))  
    word_list = cv.get_feature_names();    
    count_list = cv_fit.toarray().sum(axis=0)    

    word_dict = dict(zip(word_list, count_list))
    sorted_d = dict(sorted(word_dict.items(), key = operator.itemgetter(1), reverse = True))
    sorted20 = dict(itertools.islice(sorted_d.items(), 10))

    keys20 = list(sorted20.keys())
    values20 = pd.Series(sorted20)
            
    # create visuals
    graphs = [
        {
            'data': [
                Bar(
                    x=genre_names,
                    y=genre_counts
                )
            ],

            'layout': {
                'title': 'Distribution of Message Genres',
                'yaxis': {
                    'title': "Count"
                },
                'xaxis': {
                    'title': "Genre"
                }
            }
        },
        {
            'data': [
                Bar(
                    x=keys20,
                    y=values20                    
                )
            ],

            'layout': {
                'title': 'Most used words in last 1000 messages',
                'yaxis': {
                    'title': "Count"
                },
                'xaxis': {
                    'title': "Words"
                }
            }
        }        
    ]
    
    # encode plotly graphs in JSON
    ids = ["graph-{}".format(i) for i, _ in enumerate(graphs)]
    graphJSON = json.dumps(graphs, cls=plotly.utils.PlotlyJSONEncoder)
    
    # render web page with plotly graphs
    return render_template('master.html', ids=ids, graphJSON=graphJSON)


@app.route('/go')
def go():
    """ Web page that handles user query and displays model results """    
    
    # save user input in query
    query = request.args.get('query', '') 

    # use model to predict classification for query
    classification_labels = model.predict([query])[0]   
    classification_results = dict(zip(df.columns[4:], classification_labels))

    # This will render the go.html Please see that file. 
    return render_template(
        'go.html',
        query=query,
        classification_result=classification_results
    )


def main():
    """ Run the web app """
    app.run(host='0.0.0.0', port=3001, debug=True)

if __name__ == '__main__':
    main()