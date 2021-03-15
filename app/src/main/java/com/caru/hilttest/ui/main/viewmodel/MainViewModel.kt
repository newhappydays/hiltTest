package com.caru.hilttest.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel @ViewModelInject constructor() : ViewModel() {

    private val textLiveData = MutableLiveData<String>()
    val text: LiveData<String>
        get() = textLiveData

    init {
        textLiveData.value = "Hello"
    }

    fun hello(text : String){
        textLiveData.value = text

    }

}