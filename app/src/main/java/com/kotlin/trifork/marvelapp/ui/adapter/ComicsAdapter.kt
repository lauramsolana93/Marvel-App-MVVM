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
import com.kotlin.trifork.marvelapp.common.data.dto.Comic
import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB

class ComicsAdapter(
    val comicsList: List<Comic?>?,
    val comicsDBList: List<ComicDB?>?,
    val context: Context,
    val listener: OnItemClickListener<Any>
) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.details_item_view, parent, false)
        return ComicsViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (comicsList != null) return comicsList?.size
        else if (comicsDBList != null) return comicsDBList?.size
        return 0
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        if (comicsList != null) {
            if (comicsList[position] != null) {
                val comics = comicsList[position]
                holder.bind(comics!!)

                holder.cardView.setOnClickListener {
                    listener.onComicClicked(comics)
                }
            }
        } else if (comicsDBList != null) {
            if (comicsDBList[position] != null) {
                val comics = comicsDBList[position]
                holder.bind(comics!!)

                holder.cardView.setOnClickListener {
                    listener.onComicClicked(comics)
                }
            }
        }


    }

    class ComicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.detailsImage)
        var title: TextView = view.findViewById(R.id.detailsTitle)
        var cardView: CardView = view.findViewById(R.id.detailsCardView)
        fun bind(comic: Any) {
            when (comic) {
                is Comic -> {
                    title.text = comic.title

                    val imageUrl = "${comic.thumbnail?.path}.${comic.thumbnail?.extension}"
                    val start = imageUrl.subSequence(0, 4)
                    val loadUrl = "${start}s${imageUrl.subSequence(4, imageUrl.length)}"
                    Glide
                        .with(itemView)
                        .asBitmap()
                        .load(loadUrl)
                        .optionalCenterCrop()
                        .placeholder(R.drawable.ic_placeholder)
                        .into(image)
                }
                is ComicDB -> {
                    title.text = comic.title

                    Glide
                        .with(itemView)
                        .asBitmap()
                        .load(comic.thumbnail)
                        .optionalCenterCrop()
                        .placeholder(R.drawable.ic_placeholder)
                        .into(image)
                }
            }

        }
    }

    interface OnItemClickListener<H> {
        fun onComicClicked(item: H)
    }
}