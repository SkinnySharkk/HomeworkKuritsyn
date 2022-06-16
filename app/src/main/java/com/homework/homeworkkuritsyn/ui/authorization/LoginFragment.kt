package com.homework.homeworkkuritsyn.ui.authorization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.homework.homeworkkuritsyn.R
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentLoginBinding
import com.homework.homeworkkuritsyn.presenters.authorization.LoginUiState
import com.homework.homeworkkuritsyn.presenters.authorization.LoginViewModel
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
                Toast.makeText(
                    context,
                    resources.getString(R.string.warning_authorization),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        viewModel.loginUiState.observe(viewLifecycleOwner) { loginUiState ->
            when (loginUiState) {
                is LoginUiState.Idle -> {
                    binding.loginProgressBar.visibility = View.GONE
                    binding.loginTextFieldUserPassword.isErrorEnabled = false
                    binding.loginTextFieldUserName.isErrorEnabled = false
                }
                is LoginUiState.Success -> {
                    binding.loginProgressBar.visibility = View.GONE
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToLoansFragment())
                }
                is LoginUiState.Loading -> {
                    binding.loginProgressBar.visibility = View.VISIBLE
                }
                is LoginUiState.Error -> {
                    binding.loginProgressBar.visibility = View.GONE
                    binding.loginTextFieldUserName.isErrorEnabled = true
                    binding.loginTextFieldUserName.error = loginUiState.reason
                    binding.loginTextFieldUserPassword.isErrorEnabled = true
                    binding.loginTextFieldUserPassword.error = loginUiState.reason
                }
            }
        }
        binding.loginTextFieldUserName.editText?.doOnTextChanged { inputText, _, _, _ ->
            if (inputText.isNullOrEmpty()) {
                viewModel.dropError()
            }
        }
        binding.loginTextFieldUserPassword.editText?.doOnTextChanged { inputText, _, _, _ ->
            if (inputText.isNullOrEmpty()) {
                viewModel.dropError()
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