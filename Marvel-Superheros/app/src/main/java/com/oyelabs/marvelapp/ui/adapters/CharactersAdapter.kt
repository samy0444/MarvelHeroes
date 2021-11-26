package com.oyelabs.marvelapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oyelabs.marvelapp.R
import com.oyelabs.marvelapp.models.characters.Result
import com.oyelabs.marvelapp.ui.DisplayIformation



class CharactersAdapter(val data: List<Result>, val context: Context) :
    RecyclerView.Adapter<CharactersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.character_grid_item,
            parent, false
        )
        return CharactersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        Log.d("datafromserver", data.toString())
        val characters = data[position]
        holder.name.text = characters.name
//        holder.desc.text = characters.description
        val image = "${characters.thumbnail.path}/standard_medium.jpg"
        Glide.with(context).load(image).into(holder.image)
        holder.image.setOnClickListener {
            val intent = Intent(context, DisplayIformation::class.java).apply {
                putExtra("image",  image)
                putExtra("name",characters.name)
                putExtra("description",characters.description)
            }
            context.startActivity(intent)
        }


    }



}


class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    //val image: CircleImageView = itemView.findViewById(R.id.character_Image)
    val image: ImageView = itemView.findViewById(R.id.character_Image)
    val name: TextView = itemView.findViewById(R.id.characterName)
    val card: CardView = itemView.findViewById(R.id.cardView)
//    val desc: TextView = itemView.findViewById(R.id.characterDescription)
}