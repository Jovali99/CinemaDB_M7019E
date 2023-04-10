package com.ltu.m7019e.v23.themoviedb

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.ltu.m7019e.v23.themoviedb.database.Genres
import com.ltu.m7019e.v23.themoviedb.database.Movies
import com.ltu.m7019e.v23.themoviedb.databinding.GenreMovieItemBinding


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

        val genreLayoutContainer = view.findViewById<LinearLayout>(R.id.genre_layout)

        genres.list.forEach { genre ->
            val genreItem = LayoutInflater.from(requireContext()).inflate(R.layout.genre_item_list, genreLayoutContainer, false)
            // Filters out the movies for the current genre
            val genreMovies = movies.list.filter { it.genres.contains(genre)}
            if (genreMovies.isEmpty()) {
                // If no movies of the genre exist we remove the genre view
                genreLayoutContainer.removeView(genreItem)
            } else {
                genreItem.findViewById<TextView>(R.id.genre_name)?.text = genre
                genreLayoutContainer.addView(genreItem)
            }
            // find the current genre layout
            val movieListLayoutContainer = genreItem.findViewById<LinearLayout>(R.id.list_of_movies)
            // Adds all the movies with the corresponding genre to the list_of_movies view
            genreMovies.forEach { movie ->
                val movieItem = DataBindingUtil.inflate<GenreMovieItemBinding>(LayoutInflater.from(requireContext()), R.layout.genre_movie_item, movieListLayoutContainer, false)
                movieItem.movie = movie
                var imdb_link = movie.imdb_link
                // Creates a popup dialogue which will display information as well as a link to imbd
                create_movie_info_popup(movieItem.root, imdb_link)

                // On click action
                movieListLayoutContainer.addView(movieItem.root)
            }
        }
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    fun create_movie_info_popup(movieView: View, imdb_link: String) {
        movieView.findViewById<ImageView>(R.id.movie_poster_genre)?.setOnClickListener {
            // inflate the popup dialog layout
            val builder = AlertDialog.Builder(requireContext())
            val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.movie_info_popup, null)
            builder.setView(popupView)
            val dialog = builder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //needed for background

            dialog.show()

            on_imdb_click(dialog, imdb_link)

        }
    }

    fun on_imdb_click(popUp: AlertDialog, imdb_link: String) {
        popUp.findViewById<FrameLayout>(R.id.imdb_logo_link)?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdb_link))
        requireContext().startActivity(intent)
        }
    }


}