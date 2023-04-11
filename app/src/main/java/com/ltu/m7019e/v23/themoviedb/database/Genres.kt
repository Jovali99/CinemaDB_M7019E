package com.ltu.m7019e.v23.themoviedb.database

import com.ltu.m7019e.v23.themoviedb.model.Genre
import com.ltu.m7019e.v23.themoviedb.model.Movie

class Genres {
    //todo enter genres into list instead of converting from obj
    //val list = mutableListOf<Genre>()
    var list = mutableListOf<Pair<String, Int>>()

    fun addGenres(genres: List<Genre>): MutableList<Pair<String, Int>> {
        val tempList = mutableListOf<Pair<String, Int>>()
        genres.forEach { apiGenre ->
            val pair = Pair(apiGenre.name ?: "", apiGenre.id ?: 0)
            tempList.add(pair)
        }
        return tempList
    }


    //val list = mutableListOf<String>()



    init {          //todo api for genres
        /*
        list.add(Pair("Comedy", 35))
        list.add(Pair("Action", 28))
        list.add(Pair("Romance", 10749))
        list.add(Pair("Thriller", 53))
        list.add(Pair("Horror", 27))
        list.add(Pair("Drama", 18))
        list.add(Pair("Fantasy", 14))
        list.add(Pair("Adventure", 12))
        */

        /*
        list.add("Comedy")      // 35
        list.add("Action")      // 28
        list.add("Romance")     // 10749
        list.add("Thriller")    // 53
        list.add("Horror")      // 27
        list.add("Drama")       // 18
        list.add("Fantasy")     // 14
        list.add("Adventure")   // 12*/
    }
}