package com.caru.hilttest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.caru.hilttest.R
import com.caru.hilttest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = setContentView(this, R.layout.activity_main)
        _binding.lifecycleOwner = this

        NavigationUI.setupWithNavController(_binding.bottomNav,findNavController(R.id.nav_host))
    }
}