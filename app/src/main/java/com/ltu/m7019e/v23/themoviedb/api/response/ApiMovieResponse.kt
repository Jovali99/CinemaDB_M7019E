package com.ltu.m7019e.v23.themoviedb.api.response

import com.google.gson.annotations.SerializedName

data class ApiMovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: String
)
