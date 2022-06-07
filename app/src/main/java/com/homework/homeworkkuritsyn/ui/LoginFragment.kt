package com.homework.homeworkkuritsyn.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentLoginBinding
import com.homework.homeworkkuritsyn.presenters.LoginViewModel
import com.homework.homeworkkuritsyn.presenters.MultiViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class LoginFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels{viewModelFactory}
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
//        with(binding) {

            binding. loginButton.setOnClickListener {
                val name = binding.loginTextFieldUserName.editText?.text.toString()
                val password = binding.loginTextFieldUserPassword.editText?.text.toString()
                viewModel.login(name, password)
            }
//        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}