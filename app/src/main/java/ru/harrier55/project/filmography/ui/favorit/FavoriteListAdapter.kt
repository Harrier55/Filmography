package ru.harrier55.project.filmography.ui.favorit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.harrier55.project.filmography.R
import ru.harrier55.project.filmography.data.room.CardFilmEntityFavoriteDb


class FavoriteListAdapter(private var onClickItemFavoriteFragment: OnClickItemFavoriteFragment) :
    RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>() {

    private val TAG: String = "@@@"
    private var cardFilmsDb: List<CardFilmEntityFavoriteDb> = mutableListOf()

    fun refreshList(myFilms: List<CardFilmEntityFavoriteDb>) {
        this.cardFilmsDb = myFilms
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cardFilmsDb.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_favorite, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val cardFilmEntityFavoriteDb:CardFilmEntityFavoriteDb = cardFilmsDb[position]

        Glide.with(holder.itemView.context)
            .load(cardFilmsDb[position].filmPoster)
            .placeholder(R.drawable.star_wars)
            .into(holder.favoriteFilmPoster)
        holder.favoriteFilmName.text = cardFilmsDb[position].filmName
        holder.favoriteAlternativeName.text = cardFilmsDb[position].alternativeName
        holder.favoriteDescription.text = cardFilmsDb[position].description
        holder.deleteFromFavorite.setOnClickListener {
            onClickItemFavoriteFragment.onClickDeleteButton(cardFilmEntityFavoriteDb)
        }
    }


    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var favoriteFilmPoster: ImageView = itemView.findViewById(R.id.favorite_poster_image_view)
        var favoriteFilmName: TextView = itemView.findViewById(R.id.favorite_film_name_text_view)
        var favoriteAlternativeName: TextView =
            itemView.findViewById(R.id.favorite_alternative_film_name_text_view)
        var favoriteDescription: TextView =
            itemView.findViewById(R.id.favorite_description_text_view)

        val deleteFromFavorite:Button = itemView.findViewById(R.id.delede_from_favorite_list_button)
    }
}