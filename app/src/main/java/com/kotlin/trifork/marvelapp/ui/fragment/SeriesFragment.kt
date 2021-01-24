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
import com.kotlin.trifork.marvelapp.common.data.dto.*
import com.kotlin.trifork.marvelapp.common.utils.SERIE_ID
import com.kotlin.trifork.marvelapp.common.utils.helper.subscribe
import com.kotlin.trifork.marvelapp.di.injectModule
import com.kotlin.trifork.marvelapp.ui.activity.DetailsActivity
import com.kotlin.trifork.marvelapp.ui.activity.FavouriteActivity
import com.kotlin.trifork.marvelapp.ui.activity.InfoActivity
import com.kotlin.trifork.marvelapp.ui.adapter.SeriesAdapter
import com.kotlin.trifork.marvelapp.viewmodel.SeriesViewModel
import kotlinx.android.synthetic.main.fragment_series.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesFragment : Fragment(), SeriesAdapter.OnItemClickListener<Any>{

    private val seriesViewModel: SeriesViewModel by viewModel()
    private lateinit var seriesAdapter: SeriesAdapter
    private lateinit var seriesRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModule()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_series, container, false)
        bindViews(layout)
        setUpRecyclerView()
        getSeries()
        setUpObserveComic()
        return layout
    }

    private fun bindViews(layout: View) {
        seriesRv = layout.findViewById(R.id.seriesRv)
    }
    private fun setUpRecyclerView(){
        seriesRv.setHasFixedSize(true)
        seriesRv.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun getSeries(){
        (activity!! as DetailsActivity).showLoading()
        if(!(activity!! as DetailsActivity).connection){
            seriesViewModel.getSeriesFromDB()
        } else {
            seriesViewModel.getSerieByCharacterId((activity!! as DetailsActivity).characterID)
        }

    }


    private fun setUpObserveComic(){
        seriesViewModel.serie.subscribe(viewLifecycleOwner, this::fillAdapter)
        seriesViewModel.error.subscribe(viewLifecycleOwner, this::showError)
        seriesViewModel.serieDB.subscribe(viewLifecycleOwner, this::fillAdapterFromDB)
    }



    private fun fillAdapter(serieWrapper: SerieWrapper){
        (activity!! as DetailsActivity).hideLoading()
        if(serieWrapper != null && serieWrapper.data?.results!!.isNotEmpty()){
            emptyTextSeries.visibility = GONE
            seriesRv.visibility = VISIBLE
            seriesAdapter = SeriesAdapter(serieWrapper.data?.results ?: emptyList(),null, activity!!, this)
            seriesRv.adapter = seriesAdapter
        } else {
            emptyTextSeries.visibility = VISIBLE
            seriesRv.visibility = GONE
            emptyTextSeries.text = getString(R.string.empty_data)
        }

    }

    private fun fillAdapterFromDB(serieDB: List<SerieDB>){
        (activity!! as DetailsActivity).hideLoading()
        if(serieDB.isNotEmpty()){
            emptyTextSeries.visibility = GONE
            seriesRv.visibility = VISIBLE
            seriesAdapter = SeriesAdapter(null,serieDB, activity!!, this)
            seriesRv.adapter = seriesAdapter
        } else {
            emptyTextSeries.visibility = VISIBLE
            seriesRv.visibility = GONE
            emptyTextSeries.text = getString(R.string.empty_data)
        }

    }

    override fun onDestroy() {
        seriesRv.removeAllViewsInLayout()
        seriesRv.removeAllViews()
        super.onDestroy()

    }

    private fun showError(error: ErrorDto){
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(getString(R.string.error_title))
        builder.setMessage(getString(R.string.try_again))
        builder.setPositiveButton(getString(R.string.cancel_button)
        ) { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.try_again_button)
        ) { _, _ ->
            getSeries()
        }
        builder.create()
        builder.show()
    }

    override fun onSerieClicked(item: Any) {

        when(item){
            is SerieDB -> {
                var errorDto = ErrorDto(getString(R.string.no_connection))
                (activity!! as DetailsActivity).noConnection(errorDto)
            }
            is Serie -> {
                val intent = Intent(requireActivity(), InfoActivity::class.java)
                intent.putExtra(SERIE_ID, item.id)
                startActivity(intent)
                startActivity(intent)
            }
        }

    }

}