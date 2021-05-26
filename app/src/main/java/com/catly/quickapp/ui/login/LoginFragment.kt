package com.catly.quickapp.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quickapp.R
import com.catly.quickapp.ui.ViewModelFactory
import com.example.quickapp.databinding.LoginFragmentBinding

class LoginFragment: Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var userViewModel: UserViewModel
    private var currentDialog : MessageDialogFragment? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = binding.email
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        userViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application))
            .get(UserViewModel::class.java)

        userViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both email / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.emailError != null) {
                email.error = getString(loginState.emailError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        userViewModel.loginResult.observe(viewLifecycleOwner, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginResult(getString(loginResult.error))
            }
            if (loginResult.success != null) {
                val welcome = getString(R.string.welcome)
                val resultEmail = loginResult.success.displayName
                showLoginResult("$welcome $resultEmail")
            }
        })

        userViewModel.user.observe(viewLifecycleOwner, {user ->
            if (user !==null){
                println(user.email)
                requireView().findNavController().navigate(R.id.action_loginFragment_to_repositoryListFragment)
            }
        })

        email.afterTextChanged {
            userViewModel.loginDataChanged(
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                userViewModel.loginDataChanged(
                    email.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        userViewModel.login(
                            email.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            binding.login.setOnClickListener {
                loading.visibility = View.VISIBLE
                userViewModel.login(email.text.toString(), password.text.toString())
            }


            binding.help.setOnClickListener {
                currentDialog = MessageDialogFragment(getString(R.string.invalid_password),getString(R.string.close))
                    currentDialog?.show(parentFragmentManager, MessageDialogFragment.TAG)
            }
        }
    }

    private fun showLoginResult(result: String) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onPause() {
        super.onPause()
        currentDialog?.dismiss()
    }
}

