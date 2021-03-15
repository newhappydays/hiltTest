package com.caru.hilttest.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.*
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caru.hilttest.R
import com.caru.hilttest.databinding.BoardFragmentBinding
import com.caru.hilttest.ui.main.adapter.MainListAdapter
import com.caru.hilttest.ui.main.dialog.UserAddDialog
import com.caru.hilttest.ui.main.viewmodel.BoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment : Fragment() {

    private val viewModel: BoardViewModel by navGraphViewModels(R.id.nav_graph){
        defaultViewModelProviderFactory
    }
    private lateinit var _binding: BoardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, R.layout.board_fragment, container, false)

        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _binding.viewmodel = viewModel
        _binding.lifecycleOwner = this

        val listAdapter = MainListAdapter()
        listAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        _binding.recyclerView.apply {
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = listAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val llm = recyclerView.layoutManager as LinearLayoutManager
                    val listPosition = llm.findFirstCompletelyVisibleItemPosition()
                    Log.d("log", "onScrolled: $listPosition")
                    viewModel.scrollLiveData.postValue(listPosition)
                }
            })

            viewModel.scrollLiveData.value?.let { scrollToPosition(it) }
        }

        viewModel.userList.observe(viewLifecycleOwner) { users ->
            users.let { listAdapter.updateItems(it) }
        }


        _binding.fab.setOnClickListener {
            val display = activity?.windowManager?.defaultDisplay
            UserAddDialog(it.context).callFunction(display, viewModel)
        }
    }

}