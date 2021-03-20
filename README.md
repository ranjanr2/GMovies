# GStories and Acceptance Criteria

| URI               | HTTP Method | HTTP Status |      Description     |       Request        |        Response        | 
|-------------------|-------------|-------------|----------------------|----------------------|------------------------|
| /GMovies/Movies   |       GET      |     OK     | Get All Movies |                        | `` |      
| /GMovies/Movies/{Title} |       GET      |     OK     | Get Single Movie |                   | `` |      

| /GMovies/Movies   |       POST      |     OK     | Create a Movie       |  [{Movie details}] | `` |      
| /GMovies/Movies/{Title} | PUT      |     OK     | Update a Movie |  [{Movie details}]        | `` |   

Given the GBDB is empty
When I visit GMDB movies
Then I should see no movies 

Given a new movie has released
When I submit this new movie to GMDB movies
Then I should see that movie in GMDB movies

Given the GBDB has a movie
When I visit GMDB movies
Then I should see that movie in GMDB movies

Given the GBDB has many movies
When I visit GMDB movies
Then I should see that movie in GMDB movies

Given the GBDB has many movies
When I visit GMDB movies
Then I should see a list with that movie
As a user, I can browse each movie so I can learn all the details.

Rule: Movie details include title, director, actors, release year, description and star rating.

Given the GBDB has many movies
When I visit GMDB movies with an existing title
Then I should see that movie's details

Given the GBDB has many movies
When I visit GMDB movies with a non-existing title
Then I receive a friendly message that it doesn't exist
As a user, I can give a star rating to a movie so that I can share my experiences with others.

Given an existing movie
When I submit a 5 star rating
Then I can see it in the movie details.

Given a movie with one 5 star rating and one 3 star rating
When I view the movie details
Then I expect the star rating to be 4.
As a user, I can review a movie so that I can share my thoughts about it.

Given an existing movie
When I submit a star rating and text review
Then I can see my contribution on the movie details.

Given an existing movie
When I submit a text review without a star rating
Then I receive a friendly message that a star rating is required.
Here is sample json of movies, but keep in mind this is data to start with and may not represent the models your final solution has. It is NOT REQUIRED to use this data, but feel free to copy this into your project if it is useful to you.

