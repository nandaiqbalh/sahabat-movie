package com.nandaiqbalh.sahabatmovie.presentation.ui.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashscreenViewModel : ViewModel() {
    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()
    init {
        GlobalScope.launch {
            delay(5000)
            mutableLiveData.postValue(SplashState.MainActivity())
        }
    }
}
sealed class SplashState {
    class MainActivity : SplashState()
}