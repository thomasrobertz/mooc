Introduction
------------

In this data science project I analyse a stack overflow survey.
The data analysis follows the industry standard CRISP-DM:

1. Business understanding
2. Data understanding
3. Data preparation
4. Modeling
5. Evaluation
6. Deployment


Business Understanding
----------------------

The purpose of gaining a business understanding is that we want to understand the problem domain and come up with objectives or questions.

Motivation: As a developer of many years myself, I always found it interesting and useful to know what languages are popular and / or upcoming.
With the data from the survey, there were some further questions I thought that may shed some light on what language to choose for new developers.

As objectives, I wanted to answer the following questions:
1. Market: What languages are most represented?
2. Role: Refine the languages by developer type role. \
	Note: As developers were allowed multiple nominations for their job type as well as languages, I simplified the data and assumed the first given role is also the primary role.
	This is however consistent with the survey question which read: 
	Which of the following best describe you?
3. Job satisfaciton and salary: What languages guarantee high job satisfaction and salary?
4. What language did the programmers that were looking for new positions currently use?

I did come up with some other questions, but as we see in the next section there wasn't enough data available.


Data Understanding
------------------

Now that our business understanding and objectives are outlined, it's time to take a closer look at the survey data,
to build our *_Data Understanding_*.
Here we need to ask questions like:

> For the questions that were chosen, what columns of the survey data would I need to take into consideration? \
> Is there enough data for it to be representative? \
> What should happen if there are rows with missing values? Should we impute them? 

The data I was looking at like role, satisfaction, and salary did not lend themselves particularly well for imputation. \
For this reason I opted to always remove NaN data. However for each question I made a sanity check to verify there was enough data. 

As mentioned above, I first considered other metrics like "EnjoyDebugging" and "HoursPerWeek" but found there were a lot of answers missing so I dropped those.

HaveWorkedLanguage has 5576 missing values. \
WantWorkLanguage has 6579 missing values. \
CareerSatisfaction has 3000 missing values. \
JobSatisfaction has 3849 missing values. \
JobSeekingStatus has 6250 missing values. \
DeveloperType has 5330 missing values. \
HoursPerWeek has 11381 missing values. \
EnjoyDebugging has 7844 missing values. \
Salary has 14093 missing values.

So these are the columns I ended up with analyzing:

| Column             | Type        |
|--------------------|-------------|
| HaveWorkedLanguage | Categorical |
| JobSatisfaction    | Numeric     |
| JobSeekingStatus   | Categorical |
| DeveloperType   | Categorical |
| Salary   | Numeric |

Unique values of the categorical columns were:

DeveloperType		 \
['Web developer' 'Mobile developer' \
 'Embedded applications/devices developer' \
 'Desktop applications developer' 'Data scientist' \
 'Machine learning specialist' 'DevOps specialist' \
 'Developer with a statistics or mathematics background' \
 'Database administrator' 'Graphics programming' 'Systems administrator' \
 'Quality assurance engineer'] \
HaveWorkedWith: \
['Swift' 'JavaScript' 'Python' 'Ruby' 'SQL' 'Java' 'PHP' 'Matlab' 'R' \
 'Rust' 'CoffeeScript' 'Clojure' 'Elixir' 'Erlang' 'Haskell' 'C#' \
 'Objective-C' 'C' 'C++' 'Assembly' 'VB.NET' 'Perl' 'Scala' 'F#' \
 'TypeScript' 'Lua' 'VBA' 'Groovy' 'Go' 'Smalltalk' 'Visual Basic 6' \
 'Common Lisp' 'Dart' 'Julia' 'Hack'] \
JobSeekingStatus: \
["I'm not actively looking, but I am open to new opportunities", \
 'I am actively looking for a job', \
 'I am not interested in new job opportunities'] \
 
 The data is from a stack overflow survey csv file, the link to which you can find below.
 
 
Data Preparation and Modeling
-----------------------------

These steps form the basis of the implementation. 
To aid in answering the questions I created a helper class that would clean and preselect the data. \
My goal was to first transform the data into a form as final as possible. 

In the class, only relevant columns are kept to make the dataframe leaner and well-arranged. \
There are functions that clean the data in the following ways:
* Remove NaN values
* Remove 0 values

Also there is a function that takes the language category and separates the values by the delimiter ";".
This way we can easily count languages for each  of the questions. Notable here is that pandas allows us to [stack](https://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.stack.html) the split values and _reshape_ the dataframe.
I used this as an alternative to _one-hot_ encode the language category.

In *Question 2* I skipped the "Other" type because in this case I wanted the results to be clean and meaningful. I also dropped the type "Graphic designer" because there were only three results, this is not representative.

In *Question 3* I needed to group values of the features salary and job satisfaction into a "high" class.
Here I chose to drop those values that fall below the mean.



Evaluation and Deployment
-------------------------

In terms of evaluation, since there is no inferential part in the analysis, I simply did cross- and sanity checks of the 
results by hand calculation and some short checks which you can find at the bottom of the notebook.

The project is contained and deployed in a Jupyter notebook in full. Further steps taken in the deploymenent process were:
* Creating a blog post to communicate the findings 
* Creating this README file
* Uploading the notebook and survey to an online repository
* Ensuring the project is runnable and without errors


Files
-----

README.md		This file \
blog_post.ipynb		Notebook code with pandas analysis


Links
-----

Medium post \
https://medium.com/@thomas.robertz/what-programming-language-should-you-learn-next-940937d924af \

Notebook on kaggle \
https://www.kaggle.com/thomasrobertz/blog-post \

Survey data \
https://insights.stackoverflow.com/survey \


Libraries
---------

os and math \
	Standard python libraries for reading in files and doing simple math. 

numpy and pandas \
	Main libraries for doing data analysis.

matplotlib.pyplot and .ticker	 \
	Used for plotting, and formatting the plot.


Summary
-------

The most relevant results of the analysis are: 

Most used language:				Javascript \
Languages by job type:				Java is used in most job types \
Language with high satisfaction and pay:	Go \
Language developers use that look for new jobs:	Assembly


Acknowledgements
----------------

Survey data in "stackoverflow_survey.csv" taken from stackoverflow.com \
Pictures in the blog post: \
Coffe beans \
https://www.tentamus.com/taiwanese-market-survey-adulteration-coffee-beans/?cn-reloaded=1 \
Python \
https://academy.vertabelo.com/blog/5-best-python-ide-data-science/ \
Rust \
https://pixabay.com/photos/rust-texture-metal-rusty-metal-4669828/ \
Ruby \
https://www.nationaljeweler.com/diamonds-gems/grading/6467-gia-finds-synthetic-ruby-layer-on-natural-gemstones \
Photo credit: Hollie McBride/GIA \
Elixir \
https://commons.wikimedia.org/wiki/File:Glass_bottle_containing_Kola_Compound_elixir,_England,_1920-_Wellcome_L0059017.jpg

