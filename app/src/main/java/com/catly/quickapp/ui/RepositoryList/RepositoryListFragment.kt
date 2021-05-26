package com.catly.quickapp.ui.RepositoryList

import android.net.Uri
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.quickapp.R
import com.catly.quickapp.adapter.RepositoryListAdapter
import com.catly.quickapp.ui.ViewModelFactory
import com.catly.quickapp.ui.login.LoginFragment
import com.catly.quickapp.ui.login.LoginFragmentDirections
import com.catly.quickapp.ui.login.UserViewModel
import kotlinx.android.synthetic.main.repository_fragment.*


class RepositoryListFragment : Fragment() {
    private lateinit var repoViewModel: RepositoryListViewModel
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        repoViewModel =
            ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(
                RepositoryListViewModel::class.java
            )

        val view = inflater.inflate(R.layout.repository_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRepoList)
        val adapter = RepositoryListAdapter(findNavController())
        recyclerView.adapter = adapter
        repoViewModel.repoList.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logout.setOnClickListener {
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