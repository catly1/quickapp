package com.example.quickapp.ui.RepositoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quickapp.R
import com.example.quickapp.adapter.RepositoryListAdapter
import com.example.quickapp.ui.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL


class RepositoryListFragment : Fragment() {
    private lateinit var viewModel: RepositoryListViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(RepositoryListViewModel::class.java)

        val view = inflater.inflate(R.layout.repository_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRepoList)
        val adapter = RepositoryListAdapter(requireContext(), findNavController())
        recyclerView.adapter = adapter
        viewModel.repoList.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//            val stuff = URL("https://api.github.com/users/QuickenLoans/repos").readText()
//        CoroutineScope(Dispatchers.IO).launch {
//            println(viewModel.rawRepoList)
//        }
    }
}