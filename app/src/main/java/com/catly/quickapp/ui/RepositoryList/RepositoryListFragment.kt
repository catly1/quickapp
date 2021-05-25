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
import com.catly.quickapp.ui.login.UserViewModel


class RepositoryListFragment : Fragment() {
    private lateinit var repoViewModel: RepositoryListViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val navController = findNavController()
//        userViewModel.user

//        val spec = KeyGenParameterSpec.Builder(
//            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
//            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
//        )
//            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
//            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
//            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
//            .build()
//
//        val masterKey: MasterKey = MasterKey.Builder(requireContext())
//            .setKeyGenParameterSpec(spec)
//            .build()
//
//        val sharedPreferences = EncryptedSharedPreferences.create(
//            requireContext(),
//            "secret_shared_prefs",
//            masterKey, // masterKey created above
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);

//        sharedPreferences.edit().putString("password", )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application))
            .get(UserViewModel::class.java)

//        userViewModel.user.observe(viewLifecycleOwner, { user ->
//            if (user === null){
//                findNavController().navigate(R.id.action_repositoryListFragment_to_loginFragment)
//            }
//        })

        repoViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(RepositoryListViewModel::class.java)

        val view = inflater.inflate(R.layout.repository_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRepoList)
        val adapter = RepositoryListAdapter(requireContext(), findNavController())
        recyclerView.adapter = adapter
        repoViewModel.repoList.observe(viewLifecycleOwner, Observer {
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