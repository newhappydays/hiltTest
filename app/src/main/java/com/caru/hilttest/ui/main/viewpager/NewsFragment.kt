package com.caru.hilttest.ui.main.viewpager

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.caru.hilttest.R
import com.caru.hilttest.databinding.NewsFragmentBinding
import com.caru.hilttest.ui.main.viewpager.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()

    private lateinit var _binding : NewsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false)
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _binding.viewmodel = viewModel
        _binding.lifecycleOwner = this

        _binding.textView2.setOnClickListener {
            _binding.textView2.text = "시발"
        }

        viewModel.movieList()

        viewModel.apply {
            itemLiveData.observe(viewLifecycleOwner, Observer {
                Log.e("log", "onActivityCreated: g,ag,a", )

                for (movie in it) {
                    Log.e("log", "onActivityCreated: ${movie.movieName}", )
                }
            })
        }
    }

}