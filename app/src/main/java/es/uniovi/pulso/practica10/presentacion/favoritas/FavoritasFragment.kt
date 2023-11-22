package es.uniovi.pulso.practica10.presentacion.favoritas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.uniovi.pulso.practica10.R
import es.uniovi.pulso.practica10.datos.database.MovieEntity
import es.uniovi.pulso.practica10.datos.database.MoviesDataBase
import es.uniovi.pulso.practica10.datos.network.Movie
import es.uniovi.pulso.practica10.datos.network.MovieListResult
import es.uniovi.pulso.practica10.datos.network.RetrofitServiceFactory
import es.uniovi.pulso.practica10.presentacion.ShowMovieActivity
import es.uniovi.pulso.practica10.presentacion.adapters.MovieAdapter
import es.uniovi.pulso.practica10.presentacion.adapters.MovieFavAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoritasFragment : Fragment() {

    private lateinit var recyclerViewMovies : RecyclerView

    private lateinit var movieFavAdapter : MovieFavAdapter

    private lateinit var moviesDatabase : MoviesDataBase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root = inflater.inflate(R.layout.fragment_favoritas, container, false)

        recyclerViewMovies = root.findViewById(R.id.recyclerFavMovies)
        recyclerViewMovies.layoutManager = LinearLayoutManager(context)
        recyclerViewMovies.setHasFixedSize(true)
        //movieFavAdapter = MovieFavAdapter{mostrarMovie(it)}
        recyclerViewMovies.adapter = movieFavAdapter

        moviesDatabase = MoviesDataBase.getDB(this.context)

        //Cargamos las películas de la base de datos
        val listMovieEntity = moviesDatabase.movieDao().getAll()
        movieFavAdapter.update(listMovieEntity)

        return root
    }

    /*private fun mostrarMovie(movie : MovieEntity) {
        val intent = Intent(context, ShowMovieActivity::class.java)
        intent.putExtra("MOVIE_ID", movie.id);
        startActivity(intent)
    }*/
}