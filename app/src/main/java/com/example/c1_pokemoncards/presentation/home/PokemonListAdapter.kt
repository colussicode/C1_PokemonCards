package com.example.c1_pokemoncards.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c1_pokemoncards.R
import com.example.c1_pokemoncards.data.models.Pokemon

class PokemonListAdapter(

    private val dataset: List<Pokemon>

) : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(){

    class PokemonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textView_pokemon_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return PokemonViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.name.text = dataset[position].name
    }

    override fun getItemCount() = dataset.size
}