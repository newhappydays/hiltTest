package com.caru.hilttest.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.widget.ViewPager2
import com.caru.hilttest.R
import com.caru.hilttest.databinding.MoreFragmentBinding
import com.caru.hilttest.ui.main.adapter.ViewPagerAdapter
import com.caru.hilttest.ui.main.viewmodel.MoreViewModel
import com.caru.hilttest.ui.main.viewpager.FunFragment
import com.caru.hilttest.ui.main.viewpager.IssueFragment
import com.caru.hilttest.ui.main.viewpager.NewsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoreFragment : Fragment() {

    private val viewModel: MoreViewModel by navGraphViewModels(R.id.nav_graph)
    private lateinit var _binding : MoreFragmentBinding

    private var fragmentList = ArrayList<Fragment>()
    private val tabElement = arrayOf("뉴스","재미","이슈")

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, R.layout.more_fragment, container, false)
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _binding.viewmodel = viewModel
        _binding.lifecycleOwner = this

        fragmentList.apply {
            add(NewsFragment())
            add(FunFragment())
            add(IssueFragment())
        }
        val viewPagerAdapter = ViewPagerAdapter(requireActivity(), fragmentList)

        _binding.pager.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.e("ViewPagerFragment", "Page ${position + 1}")
                }
            })
        }

        TabLayoutMediator(_binding.tabLayout, _binding.pager) { tab, position ->
            tab.text = tabElement[position]
        }.attach()
    }

}