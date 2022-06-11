package com.homework.homeworkkuritsyn.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentLoginBinding
import com.homework.homeworkkuritsyn.presenters.auth.LoginUiState
import com.homework.homeworkkuritsyn.presenters.auth.LoginViewModel
import javax.inject.Inject

class LoginFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }
    private val binding get() = _binding!!
    private var _binding: FragmentLoginBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.authorizedComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val name = binding.loginTextFieldUserName.editText?.text.toString()
            val password = binding.loginTextFieldUserPassword.editText?.text.toString()
            if (validData(name = name, password = password)) {
                viewModel.login(name, password)
            } else {
                Toast.makeText(context, "Empty fields", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.loginUiState.observe(viewLifecycleOwner) { loginUiState ->
            when(loginUiState) {
                is LoginUiState.Idle -> {}
                is LoginUiState.Success -> {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }
                is LoginUiState.Error -> {
                    Toast.makeText(context, loginUiState.reason, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    private fun validData(name: String, password: String): Boolean {
        return viewModel.validData(name = name, password = password)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}