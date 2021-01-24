package com.kotlin.trifork.marvelapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.trifork.marvelapp.R
import com.kotlin.trifork.marvelapp.common.utils.CONNECTION
import com.kotlin.trifork.marvelapp.common.utils.helper.subscribe
import com.kotlin.trifork.marvelapp.di.injectModule
import com.kotlin.trifork.marvelapp.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        injectModule()
        checkConnectivity()
        setUpObservers()
    }

    private fun checkConnectivity() {
        loading.playAnimation()
        splashViewModel.checkNetworkStatus(this)
    }

    private fun setUpObservers() {
        splashViewModel.isConnected.subscribe(this, this::navigateToNextScreen)
    }

    private fun navigateToNextScreen(isConnected: Boolean?) {
        loading.cancelAnimation()
        if (isConnected == true) {
            var intent = Intent(this, CharactersActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            var intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(CONNECTION, false)
            startActivity(intent)
            finish()
        }
    }
}