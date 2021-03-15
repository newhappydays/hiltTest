package com.caru.hilttest.repository

import com.caru.hilttest.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MyService {

    @GET("/movieList")
    fun movieList() : Call<List<Movie>>


}