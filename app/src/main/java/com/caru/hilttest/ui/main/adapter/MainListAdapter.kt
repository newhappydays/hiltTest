package com.caru.hilttest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.caru.hilttest.databinding.ListItemBinding
import com.caru.hilttest.model.User
import java.util.*
import kotlin.collections.List as List

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    private var items: List<User> = ArrayList<User>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ListItemBinding.inflate(layoutInflater, viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener {
            Toast.makeText(it.context, "시발", Toast.LENGTH_SHORT).show()
        }
        viewHolder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    fun updateItems(mItems : List<User>){
        items = mItems
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: User) = item.run {
            binding.userName.text = this.name
            binding.userAge.text = this.age.toString()

            binding.root.setOnClickListener(listener)
        }
    }

    override fun getItemCount() = items.size

}
