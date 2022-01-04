package ru.harrier55.project.filmography.ui.filmlist

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.domain.entities.CardFilmEntity

class NowPlayingListAdapter(private var onClickListenerCardFilm: OnClickListenerCardFilm) :
    RecyclerView.Adapter<NowPlayingListAdapter.NowPlayingViewHolder>() {

    private val TAG: String = "@@@"
    private var cardFilms: List<CardFilmEntity> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun refreshListFilm(myFilm: List<CardFilmEntity>) {
        this.cardFilms = myFilm
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cardFilms.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_film, parent, false)
        return NowPlayingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        onBind(holder, position)
    }

    private fun onBind(holder: NowPlayingViewHolder, position: Int) {
        val cardFilmEntity:CardFilmEntity = cardFilms[position]

        Glide.with(holder.itemView.context)
            .load(cardFilms[position].filmPoster)
            .placeholder(R.drawable.fox)
            .into(holder.filmPoster)
        holder.filmName.text = (
                (if (cardFilms[position].filmName != null) {
                    cardFilms[position].filmName
                } else {
                    cardFilms[position].alternativeName
                }).toString())
        holder.filmYearPremiere.text = cardFilms[position].filmYear_premiere.toString()
        holder.filmRating.text = cardFilms[position].filmRating.toString()

        holder.itemView.setOnClickListener {
            Log.d(TAG, "NowPlayingListAdapter onBind: "+ cardFilmEntity.filmName)
            onClickListenerCardFilm.onClickItemCardFilm(cardFilmEntity)
        }
    }

    class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmPoster: ImageView = itemView.findViewById(R.id.film_poster_image_view)
        val filmName: TextView = itemView.findViewById(R.id.film_name_text_view)
        val filmYearPremiere: TextView = itemView.findViewById(R.id.film_year_premier_text_view)
        val filmRating: TextView = itemView.findViewById(R.id.film_rating_text_view)
    }

}




