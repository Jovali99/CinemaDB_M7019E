package com.ltu.m7019e.v23.themoviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.database.Genres
import com.ltu.m7019e.v23.themoviedb.database.Movies
import com.ltu.m7019e.v23.themoviedb.databinding.GenreMovieItemBinding
import com.ltu.m7019e.v23.themoviedb.databinding.MovieListItemBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val genres = Genres()
        val movies = Movies()

        val genreListLayoutContainer = view.findViewById<LinearLayout>(R.id.genre_layout)


        genres.list.forEach { genre ->
            val genreItem = LayoutInflater.from(requireContext()).inflate(R.layout.genre_item_list, genreListLayoutContainer, false)
            genreItem.findViewById<TextView>(R.id.genre_name)?.text = genre
            genreListLayoutContainer.addView(genreItem)

            val movieListLayoutContainer = view.findViewById<LinearLayout>(R.id.list_of_movies)

            val genreMovies = movies.list.filter { it.genres.contains(genre) }
            genreMovies.forEach { movie ->
                val movieItem = DataBindingUtil.inflate<GenreMovieItemBinding>(LayoutInflater.from(requireContext()), R.layout.genre_movie_item, movieListLayoutContainer, false)
                movieItem.movie = movie
                movieListLayoutContainer.addView(movieItem.root)
            }
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}