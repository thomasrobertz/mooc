import sys
import pandas as pd
import numpy as np
from sqlalchemy import create_engine

# How to process duplicates
keepDuplicatesStrategy = "first"

def load_data(messages_filepath, categories_filepath):
    """ Load the data from the passed paths to csv files.
    Args:
    messages_filepath: Path to the messages csv file.
    categories_filepath: Path to the categories csv file.
    """
    messages = pd.read_csv(messages_filepath)
    categories = pd.read_csv(categories_filepath)
    return messages, categories

def getInvalidColumns(df):
    """ 
    Get columns with only one class 
    F.i. column "child_alone" has exclusively 0 values.
    This can throw an error later when we fit a multilabel classifier (f.i. SVC: Expected more than one classes, got 1)
    Also, if only 0 values are present, there is nothing to learn and nothing to predict.
    Therefore in an additional step here, we check for any such columns and remove them before proceeding further.
    Args:
    df: The dataframe to check for invalid columns. 
    """
    singleClassColumns = []
    classesExceededColumns = []
    for categoryColumn in df.columns:
        if ((df[categoryColumn] == 0).all() or (df[categoryColumn] == 1).all()):
            singleClassColumns.append(categoryColumn)
        if (len(np.unique(df[categoryColumn])) > 2):
            classesExceededColumns.append(categoryColumn)            
            
    return singleClassColumns, classesExceededColumns     

def clean_data(df):
    """ Clean the dataframe.
    Drop duplicates, fill missing original messages, replace invalid class, remove obsolete column.
    Args:
    df: The dataframe to clean.
    """
    df.drop_duplicates(keep = keepDuplicatesStrategy, inplace = True)
    df["related"].replace(2, 1, inplace = True)
    # Fill missing values of original untranslated message with translated message and appended id. 
    # Reasoning: 
    #    1. We are not using original now, but maybe it's important later to see the original message so we keep it for now. 
    #    2. Better to remove as many NaN values as possible
    df["original"].fillna(df["message"] + ";id-{}".format(df["id"]), inplace = True)
    # SVC will not accept singular class, remove offending column
    df.drop(["child_alone"], axis = "columns", inplace = True)
    return df
 
def transform_data(messages, categories):
    """ Transform the data.
    Merges the datasets, creates category column names, one-hot encodes the categories, and replaces their string values with binary values.
    Args:
    messages: The messages column.
    categories: The categories column, where most of the work is done.
    """
    df = messages.merge(categories)
    categories = df["categories"].str.split(";", expand = True)
    
    # select the first row of the categories dataframe
    categoryNameParts = df["categories"][0].split(";")
    categoryColumnNames = []

    for categoryNamePart in categoryNameParts:
        categoryColumnNames.append(categoryNamePart.split("-")[0])
    
    categories.columns = categoryColumnNames
    
    for column in categories:
        # set each value to be the last character of the string
        categories[column] = categories[column].str.split("-").str[1]
        # convert column from string to numeric
        categories[column] = pd.to_numeric(categories[column]) 
    
    df.drop(["categories"], axis = "columns", inplace=True)
    df = pd.concat([df, categories], axis =  "columns", sort = False)
    
    return df

def save_data(df, database_filename):
    """ 
    Args:
    df: The dataframe to save back to the database.
    database_filename: Name of the database to save to.
    """
    engine = create_engine(f"sqlite:///{database_filename}")

    # Safest way to deal with an existing table is to simply drop and rewrite it
    with engine.connect() as conn:
        conn.execute("DROP TABLE IF EXISTS CategorizedResponse;")

    df.to_sql('CategorizedResponse', engine, index = False)

def main():
    """ Perform the ETL pipeline process with the passed parameters. """
    if len(sys.argv) == 4:

        messages_filepath, categories_filepath, database_filepath = sys.argv[1:]

        print('Loading data...\n    MESSAGES: {}\n    CATEGORIES: {}'
              .format(messages_filepath, categories_filepath))
        messages, categories = load_data(messages_filepath, categories_filepath)

        print('Transforming and cleaning data...')
        df = clean_data(transform_data(messages, categories))
        
        print('Saving data...\n    DATABASE: {}'.format(database_filepath))
        save_data(df, database_filepath)
        
        print('Cleaned data saved to database!')
    
    else:
        print('Please provide the filepaths of the messages and categories '\
              'datasets as the first and second argument respectively, as '\
              'well as the filepath of the database to save the cleaned data '\
              'to as the third argument. \n\nExample: python process_data.py '\
              'disaster_messages.csv disaster_categories.csv '\
              'DisasterResponse.db')


if __name__ == '__main__':
    main()