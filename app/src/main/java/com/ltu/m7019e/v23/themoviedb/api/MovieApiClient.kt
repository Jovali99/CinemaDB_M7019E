package com.ltu.m7019e.v23.themoviedb.api

import android.util.Log
import com.ltu.m7019e.v23.themoviedb.api.response.ApiGenreResponse
import com.ltu.m7019e.v23.themoviedb.api.response.ApiMovieResponse
import com.ltu.m7019e.v23.themoviedb.database.Genres
import com.ltu.m7019e.v23.themoviedb.model.Genre
import com.ltu.m7019e.v23.themoviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiClient {

    private val API_KEY = "4fb01808f41695becd422df6b0053141"
    private val API_MOVIE = "https://api.themoviedb.org/3/movie/550?api_key="
    private val API_POP_MOVIES_URL = "https://api.themoviedb.org/3/movie/popular?api_key="
    private val PAGES = 3
    private val LANGUAGE = "en-US"

    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    fun getGenres(language: String = LANGUAGE, callback: (List<Genre>?, Throwable?) -> Unit) {
        apiService.getGenres(language = LANGUAGE, apiKey = API_KEY).enqueue(object : Callback<ApiGenreResponse> {
            override fun onResponse(call: Call<ApiGenreResponse>, response: Response<ApiGenreResponse>) {
                if (response.isSuccessful) {
                    val apiGenreResponse = response.body()
                    Log.d("genre_list", "api response: "+ apiGenreResponse)
                    val genreList = apiGenreResponse?.genres?.map { genreObj ->
                        genreObj?.toGenre()
                    }
                    callback(genreList as List<Genre>?,null)

                } else {
                    callback(null, Throwable(response.message()))
                }
            }
            override fun onFailure(call: Call<ApiGenreResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }

    private fun Genre.toGenre(): Genre? {
        return if (this.id != null) {
            Genre(
                id = this.id,
                name = this.name ?: "",
            )
        } else {
            null
        }
    }

    private fun ApiMovieResponse.toMovie(): Movie? {
        return if (this.id != null) {

            /*
            if genre == i tuple
            index a lista = genre för filmen
             */

            val movie_genres = listOf(Pair("Comedy", 35))
            val imdb_link = "Empty"
            Movie(
                id = this.id,
                title = this.title ?: "",
                overview = this.overview ?: "",
                releaseDate = this.releaseDate ?: "",
                posterPath = this.posterPath ?: "",
                voteAverage = (this.voteAverage ?: 0.0) as Float,
                genres = movie_genres,
                imdb_link = imdb_link
            )
        } else {
            null
        }
    }
}






