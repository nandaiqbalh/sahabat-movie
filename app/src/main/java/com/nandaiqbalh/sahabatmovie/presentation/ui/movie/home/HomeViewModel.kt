package com.nandaiqbalh.sahabatmovie.presentation.ui.movie.home

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.sahabatmovie.data.local.model.user.UserEntity
import com.nandaiqbalh.sahabatmovie.data.network.model.HomeMovie
import com.nandaiqbalh.sahabatmovie.data.network.model.search.Search
import com.nandaiqbalh.sahabatmovie.data.repository.MovieRepository
import com.nandaiqbalh.sahabatmovie.data.repository.UserRepository
import com.nandaiqbalh.sahabatmovie.wrapper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository: MovieRepository, private val userRepository: UserRepository): ViewModel() {

    private val _homeMovieListResult = MutableLiveData<Resource<List<HomeMovie>>>()
    val homeMovieListResult: LiveData<Resource<List<HomeMovie>>> get() = _homeMovieListResult

    private val _userByIdResult = MutableLiveData<UserEntity>()
    val userByIdResult: LiveData<UserEntity> get() = _userByIdResult

    init {
        getHomeMovieList()
    }

    fun getHomeMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            _homeMovieListResult.postValue(Resource.Loading())
            //delay(1000)
            val popular = movieRepository.getPopular()
            val topRated = movieRepository.getTopRated()
            val upcoming = movieRepository.getUpcoming()

            val homeMovieList = mutableListOf<HomeMovie>()
            homeMovieList.add(HomeMovie(title = "Popular", results = popular.payload))
            homeMovieList.add(HomeMovie(title = "Top Rated", results = topRated.payload))
            homeMovieList.add(HomeMovie(title = "Upcoming", results = upcoming.payload))
            viewModelScope.launch(Dispatchers.Main) {
                _homeMovieListResult.postValue(Resource.Success(homeMovieList))
            }
        }
    }

    fun getUserId(): Long {
        return userRepository.getUserId()
    }

    fun getUserById(id: Long) {
        viewModelScope.launch {
            _userByIdResult.postValue(userRepository.getUserById(id))
        }
    }
}