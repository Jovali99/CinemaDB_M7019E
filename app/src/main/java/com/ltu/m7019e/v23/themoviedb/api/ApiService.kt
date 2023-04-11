package com.ltu.m7019e.v23.themoviedb.api

import com.ltu.m7019e.v23.themoviedb.api.response.ApiGenreResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiMovieResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiPopularMoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: String,
        @Query("api_key") apiKey: String
    ): Call<ApiMovieResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Path("page") pages: Int,
        @Query("api_key") apiKey: String
    ): Call<ApiPopularMoviesListResponse>

    @GET("genre/movie/list")
    fun getGenres(
        @Query("language") language: String,
        @Query("api_key") apiKey: String
    ): Call<ApiGenreResponse>
}