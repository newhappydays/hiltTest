package com.caru.hilttest.ui.main.viewpager

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.caru.hilttest.R
import com.caru.hilttest.databinding.FunFragmentBinding
import com.caru.hilttest.ui.main.viewpager.viewmodel.FunViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FunFragment : Fragment() {

    private val viewModel: FunViewModel by viewModels()

    private lateinit var _binding : FunFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fun_fragment, container, false)
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _binding.viewmodel = viewModel
        _binding.lifecycleOwner = this

    }

}