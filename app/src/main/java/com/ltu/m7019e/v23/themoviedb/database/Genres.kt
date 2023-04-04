package com.ltu.m7019e.v23.themoviedb.database

import com.ltu.m7019e.v23.themoviedb.model.Movie

class Genres {
    val genres = mutableListOf<String>()

    val comedy_movies = mutableListOf<Movie>()
    val action_movies = mutableListOf<Movie>()
    val romance_movies = mutableListOf<Movie>()
    val thriller_movies = mutableListOf<Movie>()
    val drama_movies = mutableListOf<Movie>()
    val horror_movies = mutableListOf<Movie>()
    val fantasy_movies = mutableListOf<Movie>()
    val adventure_movies = mutableListOf<Movie>()


    init {
        genres.add("Comedy")
        genres.add("Action")
        genres.add("Romance")
        genres.add("Thriller")
        genres.add("Horror")
        genres.add("Drama")
        genres.add("Fantasy")
        genres.add("Adventure")


        val movies = Movies()
        for (movie in movies.list) {
            when (movie.genres) {
                "Comedy" -> comedy_movies.add(movie)
                "Action" -> action_movies.add(movie)
                "Romance" -> romance_movies.add(movie)
                "Thriller" -> thriller_movies.add(movie)
                "Drama" -> drama_movies.add(movie)
                "Horror" -> horror_movies.add(movie)
                "Fantasy" -> fantasy_movies.add(movie)
                "Adventure" -> adventure_movies.add(movie)
            }
        }
    }
}