package com.kotlin.trifork.marvelapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.trifork.marvelapp.R
import com.kotlin.trifork.marvelapp.common.utils.COMICS
import com.kotlin.trifork.marvelapp.common.utils.CONNECTION
import com.kotlin.trifork.marvelapp.common.utils.SERIES
import com.kotlin.trifork.marvelapp.ui.adapter.ViewPagerAdapter
import com.kotlin.trifork.marvelapp.ui.fragment.ComicsFragment
import com.kotlin.trifork.marvelapp.ui.fragment.SeriesFragment
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.loading_layout.*

class FavouriteActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    var connection: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.title = getString(R.string.favourites)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        init()
    }

    private fun init() {

        getDataFromExtras()
        setUpViewPager()
        setUpTabs()
    }

    private fun getDataFromExtras() {
        if (intent.extras != null) {
            connection = intent.extras?.getBoolean(CONNECTION) ?: false
        }
    }

    private fun setUpViewPager() {
        viewPagerAdapter =
            ViewPagerAdapter(
                listOf(
                    ComicsFragment(),
                    SeriesFragment()
                ), listOf(COMICS, SERIES), supportFragmentManager
            )
        viewPager.adapter = viewPagerAdapter
    }

    private fun setUpTabs() {
        tabLayout.setupWithViewPager(viewPager, true)
    }

    fun showLoading() {
        progressLoading.playAnimation()
        progressLoading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressLoading.cancelAnimation()
        progressLoading.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}