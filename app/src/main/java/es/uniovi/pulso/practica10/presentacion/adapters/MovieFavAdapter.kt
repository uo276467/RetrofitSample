package es.uniovi.pulso.practica10.presentacion.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import es.uniovi.pulso.practica10.R
import es.uniovi.pulso.practica10.datos.database.MovieEntity
import es.uniovi.pulso.practica10.datos.network.Movie

class MovieFavAdapter(

    private var listaMovies: List<MovieEntity> = emptyList(),
    private val onItemSelected: (MovieEntity) -> Unit,

    ) : RecyclerView.Adapter<MovieFavAdapter.FavMovieViewHolder>() {


    private lateinit var  context : Context


    class FavMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgView = itemView.findViewById<ImageView>(R.id.ivPortada)
        private val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo);
        private val rootView = itemView.rootView;


        fun bindView(movie: MovieEntity, onItemSelected : (MovieEntity) -> Unit ) {

            /* Coil : https://github.com/coil-kt/coil
             * Carga de imágenes apoyada en corrutinas.
            */
            val baseURL = "https://image.tmdb.org/t/p/original/"
            imgView.load("${baseURL}${movie.urlFondo}") {
                crossfade(true)
                crossfade(500)
            }
            tvTitulo.text = movie.titulo

            //Listener
            rootView.setOnClickListener { onItemSelected(movie) };
        }
    }


    /*
     *  Notificación de cambios.
     */
    fun update(listaMovies : List<MovieEntity>) {
        this.listaMovies = listaMovies
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {

        context = parent.context

        return FavMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.reycler_item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = listaMovies.size

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) =
        holder.bindView(listaMovies[position],onItemSelected)

}