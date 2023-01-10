package com.example.c1_pokemoncards.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.c1_pokemoncards.R
import com.example.c1_pokemoncards.data.models.Pokemon

class PokemonListAdapter(

    private val dataset: List<Pokemon>

) : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(){

    class PokemonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_pokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return PokemonViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val IMG_BASE = "https://images.pokemontcg.io/"

        Glide.with(holder.view.context)
            .load(IMG_BASE + dataset[position].setCode + "/" + dataset[position].number + ".png")
            .into(holder.image)
    }

    override fun getItemCount() = dataset.size
}