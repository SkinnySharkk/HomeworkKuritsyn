package com.homework.homeworkkuritsyn.ui.authorization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            val login = binding.registerTextFieldLogin.editText?.text.toString().trim()
            val password = binding.registerTextFieldUserPassword.editText?.text.toString().trim()
            registerButton.isClickable = false
            viewModel.register(
                login = login,
                password = password
            )
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
                    findNavController().navigate(
                        RegisterFragmentDirections.actionRegisterFragmentToLoansFragment()
                    )
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
                is LoginUiState.ErrorLogin -> {
                    binding.registerProgressBar.visibility = View.GONE
                    binding.registerTextFieldLogin.isErrorEnabled = true
                    binding.registerTextFieldLogin.error = getString(R.string.login_not_correct)
                    binding.registerButton.isClickable = true
                }
                is LoginUiState.ErrorPassword -> {
                    binding.registerProgressBar.visibility = View.GONE
                    binding.registerTextFieldUserPassword.isErrorEnabled = true
                    binding.registerTextFieldUserPassword.error =
                        getString(R.string.password_not_correct)
                    binding.registerButton.isClickable = true
                }
            }
        }

        binding.registerTextFieldLogin.editText?.doOnTextChanged { _, _, _, _ ->
                viewModel.dropError()
        }
        binding.registerTextFieldUserPassword.editText?.doOnTextChanged { _, _, _, _ ->
                viewModel.dropError()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}