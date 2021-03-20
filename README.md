#GMovies API Specifications

| URI               | HTTP Method | HTTP Status |      Description     |       Request        |        Response        | 
|-------------------|-------------|-------------|----------------------|----------------------|------------------------|
| /GMovies/Movies   |       GET      |     OK     | Get All Movies |                        | `[{title:"",...},{title:"",...}]` |      
| /GMovies/Movies/{Title} | GET      |     OK     | Get Single Movie |                   | `{title:"abc",...}` |
| /GMovies/Movies   |       POST     |     OK   | Create a Movie       |  `{title:"abc",...}` |  |      
| /GMovies/Movies    | PUT            |     OK     | Update a Movie |        |  |   


# GStories and Acceptance Criteria

    Given the GBDB is empty
    When I visit GMDB movies
    Then I should see no movies 
    [DONE]

    Given a new movie has released
    When I submit this new movie to GMDB movies
    Then I should see that movie in GMDB movies
    [DONE]

    Given the GBDB has a movie
    When I visit GMDB movies
    Then I should see that movie in GMDB movies
    [DONE]

    Given the GBDB has many movies
    When I visit GMDB movies
    Then I should see a list with that movie
    [DONE]
As a user, I can browse each movie so I can learn all the details.

    Rule: Movie details include title, director, actors, release year, description and star rating.
    
    Given the GBDB has many movies
    When I visit GMDB movies with an existing title
    Then I should see that movie's details
    [DONE]

    Given the GBDB has many movies
    When I visit GMDB movies with a non-existing title
    Then I receive a friendly message that it doesn't exist
    [DONE]

As a user, I can give a star rating to a movie so that I can share my experiences with others.
    
    Given an existing movie
    When I submit a 5 star rating
    Then I can see it in the movie details.
    [DONE]

    Given a movie with one 5 star rating and one 3 star rating
    When I view the movie details
    Then I expect the star rating to be 4.
    [DONE]
As a user, I can review a movie so that I can share my thoughts about it.

    Given an existing movie
    When I submit a star rating and text review
    Then I can see my contribution on the movie details.
    [DONE]
    Given an existing movie
    When I submit a text review without a star rating
    Then I receive a friendly message that a star rating is required.
     [DONE]
Here is sample json of movies, but keep in mind this is data to start with and may not represent the models your final solution has. It is NOT REQUIRED to use this data, but feel free to copy this into your project if it is useful to you.

    [
    {
    "title": "The Avengers",
    "director": "Joss Whedon",
    "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
    "release": "2012",
    "description": "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
    "rating": null
    },
    {
    "title": "Superman Returns",
    "director": "Bryan Singer",
    "actors": "Brandon Routh, Kate Bosworth, Kevin Spacey, James Marsden",
    "release": "2006",
    "description": "Superman returns to Earth after spending five years in space examining his homeworld Krypton. But he finds things have changed while he was gone, and he must once again prove himself important to the world.",
    "rating": null
    },
    {
    "title": "Steel",
    "director": "Kenneth Johnson",
    "actors": "Shaquille O'Neal, Annabeth Gish, Judd Nelson, Richard Roundtree",
    "release": "1997",
    "description": "A scientist for the military turns himself into a cartoon-like superhero when a version of one of his own weapons is being used against enemies.",
    "rating": null
    },
    {
    "title": "Unbreakable",
    "director": "M. Night Shyamalan",
    "actors": "Bruce Willis, Samuel L. Jackson, Robin Wright, Spencer Treat Clark",
    "release": "2000",
    "description": "A man learns something extraordinary about himself after a devastating accident.",
    "rating": null
    },
    {
    "title": "Rocketeer",
    "director": "Jay Light",
    "actors": "Christopher Coakley",
    "release": "2012",
    "description": "N/A",
    "rating": null
    },
    {
    "title": "The Incredibles",
    "director": "Brad Bird",
    "actors": "Craig T. Nelson, Holly Hunter, Samuel L. Jackson, Jason Lee",
    "release": "2004",
    "description": "A family of undercover superheroes, while trying to live the quiet suburban life, are forced into action to save the world.",
    "rating": null
    },
    {
    "title": "The Lego Batman Movie",
    "director": "Chris McKay",
    "actors": "Will Arnett, Michael Cera, Rosario Dawson, Ralph Fiennes",
    "release": "2017",
    "description": "A cooler-than-ever Bruce Wayne must deal with the usual suspects as they plan to rule Gotham City, while discovering that he has accidentally adopted a teenage orphan who wishes to become his sidekick.",
    "rating": null
    }
    ]