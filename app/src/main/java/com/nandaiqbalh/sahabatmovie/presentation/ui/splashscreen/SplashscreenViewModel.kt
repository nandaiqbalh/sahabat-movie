package com.nandaiqbalh.sahabatmovie.presentation.ui.splashscreen

import androidx.lifecycle.*
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
            mutableLiveData.postValue(SplashState.UserActivity())
        }
    }
}

sealed class SplashState {
    class UserActivity : SplashState()
}