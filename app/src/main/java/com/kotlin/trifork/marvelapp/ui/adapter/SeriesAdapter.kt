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
import com.kotlin.trifork.marvelapp.common.data.dto.Serie
import com.kotlin.trifork.marvelapp.common.data.dto.SerieDB

class SeriesAdapter(
    val seriesList: List<Serie?>?,
    val serieDbList: List<SerieDB?>?,
    val context: Context,
    val listener: OnItemClickListener<Any>
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.details_item_view, parent, false)
        return SeriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (seriesList != null) return seriesList?.size
        else if (serieDbList != null) return serieDbList?.size
        return 0
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        if (seriesList != null) {
            if (seriesList[position] != null) {
                val serie = seriesList[position]
                holder.bind(serie!!)

                holder.cardView.setOnClickListener {
                    listener.onSerieClicked(serie)
                }
            }
        } else if (serieDbList != null) {
            if (serieDbList[position] != null) {
                val serie = serieDbList[position]
                holder.bind(serie!!)

                holder.cardView.setOnClickListener {
                    listener.onSerieClicked(serie!!)
                }
            }
        }


    }

    class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.detailsImage)
        var title: TextView = view.findViewById(R.id.detailsTitle)
        var cardView: CardView = view.findViewById(R.id.detailsCardView)
        var loadUrl = ""
        fun bind(serie: Any) {
            when (serie) {
                is Serie -> {
                    title.text = serie.title

                    val imageUrl =
                        "${serie.thumbnail?.path ?: ""}.${serie.thumbnail?.extension ?: ""}"
                    val start = imageUrl.subSequence(0, 4)
                    loadUrl = "${start}s${imageUrl.subSequence(4, imageUrl.length)}"
                    Glide
                        .with(itemView)
                        .asBitmap()
                        .load(loadUrl)
                        .optionalCenterCrop()
                        .placeholder(R.drawable.ic_placeholder)
                        .into(image)
                }
                is SerieDB -> {
                    title.text = serie.title

                    Glide
                        .with(itemView)
                        .asBitmap()
                        .load(loadUrl)
                        .optionalCenterCrop()
                        .placeholder(R.drawable.ic_placeholder)
                        .into(image)
                }
            }

        }

    }

    interface OnItemClickListener<H> {
        fun onSerieClicked(item: H)
    }


}