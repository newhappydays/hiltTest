package com.caru.hilttest.ui.main.viewpager.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caru.hilttest.model.Movie
import com.caru.hilttest.repository.MyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsViewModel @ViewModelInject constructor(
    private val service: MyService
) : ViewModel() {

    val itemLiveData = MutableLiveData<List<Movie>>()


    fun movieList(){
        Log.e("log", "onActivityCreated: g,ag,a", )
        service.movieList()
            .clone()
            .enqueue(object : Callback<List<Movie>> {
                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                    Log.e("log", "onActivityCreated: g,ag,a", )
                    itemLiveData.value = response.body()
                }

                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {}
            })
    }

}