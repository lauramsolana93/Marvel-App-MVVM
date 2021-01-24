package com.kotlin.trifork.marvelapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.trifork.marvelapp.R
import com.kotlin.trifork.marvelapp.common.data.dto.CharacterDto

class CharactersAdapter(
    private var characters: List<CharacterDto?>,
    val context: Context,
    val listener: OnItemClickListener<CharacterDto>
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.character_list_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        if (characters[position] != null) {
            val character = characters[position]
            holder.bind(character!!)




            holder.cardView.setOnClickListener {
                listener.onCharacterClicked(character)
            }
        }

    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var characterImage: ImageView = view.findViewById(R.id.characterImage)
        var characterName: TextView = view.findViewById(R.id.characterName)
        var cardView: CardView = view.findViewById(R.id.characterCardView)
        fun bind(character: CharacterDto) {
            characterName.text = character.name
            val imageUrl = "${character.thumbnail?.path}.${character.thumbnail?.extension}"
            val start = imageUrl.subSequence(0, 4)
            val loadUrl = "${start}s${imageUrl.subSequence(4, imageUrl.length)}"
            Glide
                .with(itemView)
                .asBitmap()
                .load(loadUrl)
                .optionalCenterCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(characterImage)
        }
    }

    interface OnItemClickListener<H> {
        fun onCharacterClicked(item: H)
    }
}