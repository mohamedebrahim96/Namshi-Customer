package com.namshi.customer.ui.main.adapter


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.namshi.customer.R
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding
import com.skydoves.pokedex.R
import com.skydoves.pokedex.databinding.ItemPokemonBinding
import com.skydoves.pokedex.model.Pokemon
import com.skydoves.pokedex.ui.details.DetailActivity

class HomeAdapter : BindingListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(diffUtil) {

    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        parent.binding<ItemPokemonBinding>(R.layout.item_pokemon).let(::PokemonViewHolder)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) =
        holder.bindPokemon(getItem(position))

    inner class PokemonViewHolder constructor(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != NO_POSITION }
                    ?: return@setOnClickListener
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
                    DetailActivity.startActivity(binding.transformationLayout, getItem(position))
                    onClickedAt = currentClickedAt
                }
            }
        }

        fun bindPokemon(pokemon: Pokemon) {
            binding.pokemon = pokemon
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {

            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}
