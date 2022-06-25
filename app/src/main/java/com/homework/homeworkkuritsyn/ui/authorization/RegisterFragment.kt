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
import com.homework.homeworkkuritsyn.databinding.FragmentRegisterBinding
import com.homework.homeworkkuritsyn.presenters.authorization.LoginUiState
import com.homework.homeworkkuritsyn.presenters.authorization.RegisterViewModel
import javax.inject.Inject

class RegisterFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RegisterViewModel by viewModels { viewModelFactory }
    private val binding get() = _binding!!
    private var _binding: FragmentRegisterBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.authorizedComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener { registerButton ->
            val login = binding.registerTextFieldLogin.editText?.text.toString()
            val password = binding.registerTextFieldUserPassword.editText?.text.toString()
            if (validData(
                    login = login,
                    password = password
                )
            ) {
                registerButton.isClickable = false
                viewModel.register(
                    login = login,
                    password = password
                )
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
                    binding.registerProgressBar.visibility = View.GONE
                    binding.registerTextFieldLogin.isErrorEnabled = false
                    binding.registerTextFieldUserPassword.isErrorEnabled = false
                }
                is LoginUiState.Success -> {
                    binding.registerProgressBar.visibility = View.GONE
                    Toast.makeText(context, getString(R.string.success_register), Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoansFragment())
                }
                is LoginUiState.Loading -> {
                    binding.registerProgressBar.visibility = View.VISIBLE
                }
                is LoginUiState.Error -> {
                    binding.registerProgressBar.visibility = View.GONE
                    binding.registerTextFieldLogin.isErrorEnabled = true
                    binding.registerTextFieldLogin.error = loginUiState.reason
                    binding.registerTextFieldUserPassword.isErrorEnabled = true
                    binding.registerTextFieldUserPassword.error = loginUiState.reason
                    binding.registerButton.isClickable = true
                }
            }
        }

        binding.registerTextFieldLogin.editText?.doOnTextChanged { inputText, _, _, _ ->
            if (inputText.isNullOrEmpty()) {
                viewModel.dropError()
            }
        }
        binding.registerTextFieldUserPassword.editText?.doOnTextChanged { inputText, _, _, _ ->
            if (inputText.isNullOrEmpty()) {
                viewModel.dropError()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validData(
        login: String,
        password: String
    ): Boolean {
        return viewModel.validData(
            login = login,
            password = password
        )
    }
}