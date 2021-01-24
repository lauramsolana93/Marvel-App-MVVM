package com.kotlin.trifork.marvelapp.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.trifork.marvelapp.R
import com.kotlin.trifork.marvelapp.common.data.dto.Comic
import com.kotlin.trifork.marvelapp.common.data.dto.ComicDB
import com.kotlin.trifork.marvelapp.common.data.dto.ComicWrapper
import com.kotlin.trifork.marvelapp.common.data.dto.ErrorDto
import com.kotlin.trifork.marvelapp.common.utils.COMIC_ID
import com.kotlin.trifork.marvelapp.common.utils.helper.subscribe
import com.kotlin.trifork.marvelapp.di.injectModule
import com.kotlin.trifork.marvelapp.ui.activity.DetailsActivity
import com.kotlin.trifork.marvelapp.ui.activity.InfoActivity
import com.kotlin.trifork.marvelapp.ui.adapter.ComicsAdapter
import com.kotlin.trifork.marvelapp.viewmodel.ComicsViewModel
import kotlinx.android.synthetic.main.fragment_comics.*
import kotlinx.android.synthetic.main.fragment_series.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicsFragment : Fragment(), ComicsAdapter.OnItemClickListener<Any> {

    private val comicsViewModel: ComicsViewModel by viewModel()
    private lateinit var comicsAdapter: ComicsAdapter
    private lateinit var comicsRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModule()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_comics, container, false)
        bindViews(layout)
        setUpRecyclerView()
        getComics()
        setUpObserveComic()
        return layout
    }

    private fun bindViews(layout: View) {
        comicsRv = layout.findViewById(R.id.comicsRv)
    }

    private fun setUpRecyclerView() {
        comicsRv.setHasFixedSize(true)
        comicsRv.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun getComics() {
        (activity!! as DetailsActivity).showLoading()
        if (!(activity!! as DetailsActivity).connection) {
            comicsViewModel.getComicsFromDb()
        } else {
            comicsViewModel.getComicsByCharacterId((activity!! as DetailsActivity).characterID)
        }

    }


    private fun setUpObserveComic() {
        comicsViewModel.comic.subscribe(viewLifecycleOwner, this::fillAdapter)
        comicsViewModel.error.subscribe(viewLifecycleOwner, this::showError)
        comicsViewModel.comicDB.subscribe(viewLifecycleOwner, this::fillAdapterFromDB)
    }


    private fun fillAdapter(comicWrapper: ComicWrapper?) {
        (activity!! as DetailsActivity).hideLoading()
        if (comicWrapper != null && comicWrapper.data?.results!!.isNotEmpty()) {
            emptyTextComics.visibility = GONE
            comicsRv.visibility = VISIBLE
            comicsAdapter =
                ComicsAdapter(comicWrapper.data?.results ?: emptyList(), null, activity!!, this)
            comicsRv.adapter = comicsAdapter
        } else {
            emptyTextComics.visibility = VISIBLE
            comicsRv.visibility = GONE
            emptyTextComics.text = getString(R.string.empty_data)
        }

    }

    private fun fillAdapterFromDB(comicDB: List<ComicDB>) {
        (activity!! as DetailsActivity).hideLoading()
        if (comicDB.isNotEmpty()) {
            emptyTextComics.visibility = GONE
            comicsRv.visibility = VISIBLE
            comicsAdapter = ComicsAdapter(null, comicDB, activity!!, this)
            comicsRv.adapter = comicsAdapter
        } else {
            emptyTextComics.visibility = VISIBLE
            comicsRv.visibility = GONE
            emptyTextSeries.text = getString(R.string.empty_data)
        }

    }


    override fun onDestroy() {
        comicsRv.removeAllViewsInLayout()
        comicsRv.removeAllViews()
        super.onDestroy()

    }

    private fun showError(error: ErrorDto) {
        val builder = AlertDialog.Builder(activity)
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
            getComics()
        }
        builder.create()
        builder.show()
    }

    override fun onComicClicked(item: Any) {
        when (item) {
            is ComicDB -> {
                var errorDto = ErrorDto(getString(R.string.no_connection))
                (activity!! as DetailsActivity).noConnection(errorDto)
            }
            is Comic -> {
                val intent = Intent(requireActivity(), InfoActivity::class.java)
                intent.putExtra(COMIC_ID, item.id)
                startActivity(intent)
            }
        }

    }


}