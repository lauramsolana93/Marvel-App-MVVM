package com.kotlin.trifork.marvelapp.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kotlin.trifork.marvelapp.R
import com.kotlin.trifork.marvelapp.common.data.dto.*
import com.kotlin.trifork.marvelapp.common.utils.COMIC_ID
import com.kotlin.trifork.marvelapp.common.utils.SERIE_ID
import com.kotlin.trifork.marvelapp.common.utils.helper.subscribe
import com.kotlin.trifork.marvelapp.di.injectModule
import com.kotlin.trifork.marvelapp.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URL

class InfoActivity : AppCompatActivity() {

    private val infoViewModel: InfoViewModel by viewModel()

    private var serieID: Int? = null
    private var comicID: Int? = null
    var loadUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar?.title = getString(R.string.info)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        injectModule()
        getDataFromExtras()
        setUpObservers()
    }

    private fun getDataFromExtras() {
        if (intent.extras != null) {
            serieID = intent.extras?.getInt(SERIE_ID) ?: 0
            comicID = intent.extras?.getInt(COMIC_ID) ?: 0
        }
        getInfo()
    }

    private fun setUpObservers() {
        infoViewModel.comicInfo.subscribe(this, this::setTypeOfInfo)
        infoViewModel.serieInfo.subscribe(this, this::setTypeOfInfo)
        infoViewModel.errorDto.subscribe(this, this::showError)
        infoViewModel.complete.subscribe(this, this::showHideLoading)
    }

    private fun getInfo() {
        if (serieID != 0) {
            infoViewModel.getSerieById(serieID ?: 0)
        } else if (comicID != 0) {
            infoViewModel.getComicById(comicID ?: 0)
        }
    }

    private fun showHideLoading(complete: Boolean) {
        if (complete) progressLoading.visibility = GONE
        else progressLoading.visibility = VISIBLE
    }

    private fun setTypeOfInfo(info: Any?) {
        showHideLoading(true)
        when (info) {
            is ComicWrapper -> {
                titleInfo.text = info.data?.results?.get(0)?.title ?: ""
                descriptionInfo.text = info.data?.results?.get(0)?.description ?: ""
                val imageUrl = "${info.data?.results?.get(0)?.thumbnail?.path ?: ""}." +
                        "${info.data?.results?.get(0)?.thumbnail?.extension ?: ""}"
                val start = imageUrl.subSequence(0, 4)
                loadUrl = "${start}s${imageUrl.subSequence(4, imageUrl.length)}"

            }
            is SerieWrapper -> {
                titleInfo.text = info.data?.results?.get(0)?.title ?: ""
                descriptionInfo.text = info.data?.results?.get(0)?.description ?: ""
                val imageUrl = "${info.data?.results?.get(0)?.thumbnail?.path ?: ""}." +
                        "${info.data?.results?.get(0)?.thumbnail?.extension ?: ""}"
                val start = imageUrl.subSequence(0, 4)
                loadUrl = "${start}s${imageUrl.subSequence(4, imageUrl.length)}"
            }
        }

        Glide
            .with(this)
            .asBitmap()
            .load(loadUrl)
            .optionalCenterCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(imageInfo)

        setUpListeners()

    }


    private fun setUpListeners() {
        favButton.setOnClickListener {
            showHideLoading(false)
            if (serieID != 0 && serieID != null) {
                GlobalScope.async {
                    val imageUrl = URL(loadUrl)
                    val inputStream = imageUrl.openStream()
                    val image = inputStream.readBytes()

                    val serieDB = SerieDB(
                        id = serieID!!,
                        title = titleInfo.text.toString(),
                        description = descriptionInfo.text.toString(),
                        thumbnail = image
                    )
                    infoViewModel.addSerieInfoToDb(serieDB)
                }

            }
            if (comicID != 0 && comicID != null) {
                GlobalScope.async {
                    val imageUrl = URL(loadUrl)
                    val inputStream = imageUrl.openStream()
                    val image = inputStream.readBytes()

                    val comicDB = ComicDB(
                        id = comicID!!,
                        title = titleInfo.text.toString(),
                        description = descriptionInfo.text.toString(),
                        thumbnail = image
                    )
                    infoViewModel.addComicInfoToDb(comicDB)
                }
            }
        }
    }


    private fun showError(error: ErrorDto) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.error_title))
        builder.setMessage(getString(R.string.try_again))
        builder.setPositiveButton(
            getString(R.string.cancel_button)
        ) { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNegativeButton(
            getString(R.string.try_again_button)
        ) { _, _ ->
            getInfo()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}