package com.caru.hilttest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.caru.hilttest.databinding.ListItemBinding
import com.caru.hilttest.model.User
import com.caru.hilttest.ui.main.viewpager.FunFragment
import com.caru.hilttest.ui.main.viewpager.IssueFragment
import com.caru.hilttest.ui.main.viewpager.NewsFragment
import java.util.ArrayList

class ViewPagerAdapter(
        fragmentActivity: FragmentActivity,
        private val fragmentList : ArrayList<Fragment>
) : FragmentStateAdapter(fragmentActivity){

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int = fragmentList.size

    private fun getRealPosition(position: Int) : Int{
        return position % 3
    }
}
