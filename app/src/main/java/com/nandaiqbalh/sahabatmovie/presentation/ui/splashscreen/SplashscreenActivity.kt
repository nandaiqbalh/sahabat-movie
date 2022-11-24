package com.nandaiqbalh.sahabatmovie.presentation.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nandaiqbalh.sahabatmovie.R
import com.nandaiqbalh.sahabatmovie.presentation.ui.user.MainActivity

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val splashViewModel = ViewModelProviders.of(this).get(SplashscreenViewModel::class.java)
        splashViewModel.liveData.observe(this, Observer {
            when (it) {
                is SplashState.MainActivity -> {
                    goToMainActivity()
                }
            }
        })
    }

    private fun goToMainActivity() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}