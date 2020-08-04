# import libraries
import sys
import re
import numpy as np
import pandas as pd
import nltk
import pickle

from sqlalchemy import create_engine

from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from nltk.stem.wordnet import WordNetLemmatizer

from sklearn.model_selection import train_test_split
from sklearn.pipeline import Pipeline
from sklearn.feature_extraction.text import CountVectorizer, TfidfTransformer
from sklearn.metrics import classification_report

from sklearn.multioutput import MultiOutputClassifier
from sklearn.svm import LinearSVC

lemmatizer = WordNetLemmatizer()

def load_data(database_filepath):
    """ Load data from the database path and from it get X and y values.
    Args:
    database_filepath: File location of the database, to load and write back.
    """
    engine = create_engine(f"sqlite:///{database_filepath}")
    df = pd.read_sql_table("CategorizedResponse", engine)
    X = df["message"]
    y = df.drop(["id", "message", "original", "genre"], axis = "columns")

    return X, y

def tokenize(text):
    """ Normalize, tokenize, lemmatize and remove stop words.
    Args:
    text: The text to be tokenized.
    """    
    # Normalize text
    # Replace non-alphanumeric characters with space, convert to lower and remove trailing spaces 
    text = re.sub("[^a-zA-Z09]", " ", text).lower().strip()
    # To be extra-clean remove double spaces
    text = re.sub("\s{2,}", " ", text)
    
    # After normalization, tokenize text into tokens, remove stop words and lemmatize
    tokens = word_tokenize(text)
    # Remove stop words
    tokens = [lemmatizer.lemmatize(word) for word in tokens if word not in stopwords.words("english")]
    
    return tokens   

def build_model(classifier):
    """ Function to create the pipeline with tokenization plus classifier wrapped in multi output.
    Args:
    classifier: The classifier to insert into the pipeline.
    """
    return Pipeline([
        ('vect', CountVectorizer(tokenizer = tokenize)),
        ('tfidf', TfidfTransformer()),
        ('clf', MultiOutputClassifier(classifier))
    ])

def optimize_model(X_train, y_train, cv_size_divisor, parameters):
    """ Function to run gridsearch and improve the model.
    Args:
    X_train: The feature set.
    y_train: The label set.
    cv_size_divisor: Can be used to reduce the training data, to finish more quickly.
    parameters: Hyperparameters for the grid search.
    """
    X_cv_reduced = X_train.copy(deep = True)[:int(len(X_train) / cv_size_divisor)]
    y_cv_reduced = y_train.copy(deep = True)[:int(len(y_train) / cv_size_divisor)]
    
    cv = GridSearchCV(estimator = pipeline, param_grid = parameters, scoring='f1_macro', cv = None, n_jobs = -1, verbose = 10)
    cv.fit(X_cv_reduced, y_cv_reduced)

    return cv

def evaluate_model(model, X_test, y_test, column_names):
    """ Evaluate the model.
    Args:
    model: The model to evaluate.
    X_test: Test feature set to evaluate on.
    y_test: Test label set to evaluate on.
    column_names: The names of the columns in the test label set.
    """
    y_pred = model.predict(X_test)
    print(classification_report(y_test, y_pred, target_names = column_names))

def save_model(model, model_filepath):
    """ Save the created model to the file path that is provided.
    Args:
    model: The model to save.
    model_filepath: Where to save the model.
    """
    pickle.dump(model, open(model_filepath, 'wb'))   
    
def main():
    """ Run the training process to generate the model from the user provided parameters. """
    if len(sys.argv) == 3:
        
        # Download necessary nltk data
        nltk.download('stopwords')
        nltk.download('words')
        nltk.download('wordnet')
        nltk.download('punkt')

        # Load database
        database_filepath, model_filepath = sys.argv[1:]
        print('Loading data...\n    DATABASE: {}'.format(database_filepath))
        X, y = load_data(database_filepath)
        
        # Split data into test and trainig data        
        X_train, X_test, y_train, y_test = train_test_split(X, y)
        
        # Build, train, evaluate, and save the model
        
        print('Building model...')
        model = build_model(LinearSVC())        
        
        print('Training model...')
        model.fit(X_train, y_train)
        
        print('Evaluating model...')
        evaluate_model(model, X_test, y_test, y_train.columns)

        # We have already optimized the model. To make sure this runs as fast as possible, the optimizer is commented out.
        # For future datasets, above code needs to be commented out and this code commented in one time, then reverted to current state.
        #arameters = {
        #   'vect__ngram_range': ((1, 1), (1, 2)),
        #   'vect__max_df': (0.5, 1.0)
        #}
        #model = optimize_model(X_train, y_train, 2, pameters)
        
        print('Saving model...\n    MODEL: {}'.format(model_filepath))
        save_model(model, model_filepath)

        print('Trained model saved!')

    else:
        print('Please provide the filepath of the disaster messages database '\
              'as the first argument and the filepath of the pickle file to '\
              'save the model to as the second argument. \n\nExample: python '\
              'train_classifier.py ../data/DisasterResponse.db classifier.pkl')


if __name__ == '__main__':
    main()