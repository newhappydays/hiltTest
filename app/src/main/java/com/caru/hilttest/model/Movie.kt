package com.caru.hilttest.model

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("id") var id : Int,
    @SerializedName("movieName") var movieName : String,
    @SerializedName("imageUrl") var imageUrl : String
){
}