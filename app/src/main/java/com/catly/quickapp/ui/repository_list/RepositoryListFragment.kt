package com.catly.quickapp.ui.repository_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quickapp.R
import com.catly.quickapp.adapter.RepositoryListAdapter
import com.catly.quickapp.ui.ViewModelFactory
import com.catly.quickapp.ui.login.UserViewModel
import com.example.quickapp.databinding.RepositoryFragmentBinding

class RepositoryListFragment : Fragment() {
    private lateinit var repoViewModel: RepositoryListViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: RepositoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        repoViewModel =
            ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(
                RepositoryListViewModel::class.java
            )
        binding = RepositoryFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        val adapter = RepositoryListAdapter(findNavController())
        binding.recyclerViewRepoList.adapter = adapter
        repoViewModel.repoList.observe(viewLifecycleOwner, {
            adapter.update(it)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RepositoryFragmentBinding.bind(view).logout.setOnClickListener {
            userViewModel.logout()
            findNavController().navigate(R.id.action_repositoryListFragment_to_loginFragment)
        }

        isLoggedIn()
    }

    private fun isLoggedIn() {
        userViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application))
            .get(UserViewModel::class.java)

        userViewModel.user.observe(viewLifecycleOwner, { user ->
            if (user === null) {
                findNavController().navigate(R.id.action_repositoryListFragment_to_loginFragment)
            }
        })
    }
}