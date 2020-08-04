![alt text](https://github.com/thomasrobertz/udacity-disaster/blob/master/img/5-Table1-1.png?raw=true "Twitter Disaster")

# Disaster Response 


In case of a natural disasters occurring such as fires, hurricanes, floods or earthquakes, message relay becomes a life-saving task.

> During an incident, communication with the community becomes especially critical. (1)

> We found that social media has changed the landscape of public sector communication, and is now a key tool during disasters and emergencies. (2)

Filtering out relevant messages in tweets and posts, but also directly in disaster response agencies is a time consuming and error-prone activity. Also due to the sheer amount of messages and many sources (Think of all the different social media sites) it would probably not be the right job for humans to do anyway.

If we use machine learning tools to help classify messages like these, we may be able to dispatch the right type of help where it is needed more quickly than before. A simple example of what such a classification tool could look like is presented here.

# Data

The file disaster_messages.csv contains a message id, the messages in original format, a translation of the message, and a genre.
It contains plain messages entered by people in need of help like the following

>We are in dire need of food, water and temporary shelter. We need to know where we can get supplies. We are located in Frere Boukan, La plaine.

The file disaster_categories.csv contains the categories, which are used to classify the messages. 
The categories are linked to the messages by an id, and so for the above message the category is:

`related-1;request-1;offer-0;aid_related-1;medical_help-0;medical_products-0;search_and_rescue-0;security-0;military-0;child_alone-0;water-1;food-1;shelter-1;clothing-0;money-0;missing_people-0;refugees-0;death-0;other_aid-1;infrastructure_related-0;transport-0;buildings-0;electricity-0;tools-0;hospitals-0;shops-0;aid_centers-0;other_infrastructure-0;weather_related-0;floods-0;storm-0;fire-0;earthquake-0;cold-0;other_weather-0;direct_report-1`

We can see that the categories "water", "food" and "shelter" are flagged. 
Naturally we needed to convert these categories into binary labels (One-hot encoding). Finally the records were simply merged and fitted on an ML algorithm for training.

Some cleaning had to be performed, f.i. the column "related" showed more than two classes.
It was assumed here, that the present values of "2" in this column were intended as a positive and were therefore converted to a "1".

Note that in the data some values are underrepresented and thus the dataset is severely imbalanced.
Some ideas to work around this could be:
* Re-examine the message data and try to improve the balance.
* Leave out these labels and re-examine from time to time when new data arrives.
* On top of the exisiting algorithm, build a conventional scanner or a subset/auxiliary ML algorithm exclusively for these labels.
* Use SMOTE or simlilar oversampling techniques to generate synthetic data points. However with value counts in the low hundreds vs. near 20k records this is not likely to succeed i.m.o.

# Files

```
├── app								
│   ├── run.py						
│   └── templates
│       ├── go.html
│       └── master.html
├── data							
│   ├── disaster_categories.csv			
│   ├── disaster_messages.csv
│   ├── DisasterResponse.db
│   └── process_data.py					
├── models						
│   ├── model.pickle
│   └── train_classifier.py				
```
The flask web app resides in the app folder and can be run using the file run.py.

The ETL process files resides in the data folder.

The ML process files reside in the models folder. 

See next paragraph "Installing and running the app" on how to use these files to build and run the application.

# Installing and running the app

### I. If you have access to the udacity project conda terminal:

1. Run the following commands in the project's root directory to set up your database and model.

    - To run ETL pipeline that cleans data and stores in database
        `python data/process_data.py data/disaster_messages.csv data/disaster_categories.csv data/DisasterResponse.db`
    - To run ML pipeline that trains classifier and saves
        `python models/train_classifier.py data/DisasterResponse.db models/model.pickle`

2. Run the following command in the app's directory to run your web app.
    `python run.py`

3. Go to http://0.0.0.0:3001/

### II. If you don't have access to the conda project console

1. Clone this repository into a python environment with a numpy, pandas, scikit-learn, sqlalchemy and flask environment set up. 
2. Then you need to unzip the database file "DisasterResponse.zip" in the workspace/data folder
3. Perform the above steps under **I.** from the /workspace folder.

# Libraries

The project uses common machine learning libraries like numpy, pandas, and scikit-learn. The web app runs on flask.

# Screenshots

Area to enter disaster message and see classification
![alt text](https://github.com/thomasrobertz/udacity-disaster/blob/master/img/disaster-web1.png?raw=true "Enter message")

Some data infos
![alt text](https://github.com/thomasrobertz/udacity-disaster/blob/master/img/disaster-web2.png?raw=true "Data info")

# Acknowledgements and image credits

Teaser image:
Published in PACIS 2016
Enhancing Disaster Management through Social Media Analytics to Develop Situation Awareness What can be Learned from Twitter Messages about Hurricane Sandy?
Alivelu Mukkamala, Roman Beck

I would especially like to thank Figure8 for providing the dataset.

---

1 FEMA Training manual
https://training.fema.gov/emiweb/is/is242b/student%20manual/sm_03.pdf

2 https://theconversation.com/good-communication-is-a-key-part-of-disaster-response-119591
