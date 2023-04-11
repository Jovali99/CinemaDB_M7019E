package com.ltu.m7019e.v23.themoviedb.model

data class Movie(
        var title: String,
        var posterPath: String,
        var releaseDate: String,
        var overview: String,
        // var genres: List<String>,
        var genres: List<Pair<String, Int>>,
        var imdb_link: String,
        var id: Int = 0,
        var voteAverage: Float = 0f
)