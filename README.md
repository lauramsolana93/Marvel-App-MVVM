# Marvel-App-MVVM
Marvel App using Marvel Api

# About the project
I've used MVVM Architecture to develop this app.
I've used Marvel Api


This app has 4 different Activities

0. Splash Activity: The app just checks the connectivity in this activity

1. Characters activity: The user will a list of characters with an image and the name
of each character. On click one character we are going to navigate to the following activity

2. Details activity: This activity contains a view pager with two tabs. Comics and Series.
In these fragments are going to be show comics and series filtered by the character selected.
This activity can work without internet connection showing the favourites comics and series
selected by the user

3. Info Activity: In this activity the user can see the title, the thumbnail and a description
about the series or comic selected in Details activity. In this activity to the user can save the
item as favourite.

I've added test for the database. And a little instrumented test for DetailsActivity

