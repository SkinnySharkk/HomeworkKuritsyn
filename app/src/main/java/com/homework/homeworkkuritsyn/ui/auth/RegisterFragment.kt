package com.homework.homeworkkuritsyn.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentRegisterBinding
import com.homework.homeworkkuritsyn.presenters.RegisterViewModel
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
        binding.registerButton.setOnClickListener{
            val name = binding.registerTextFieldUserName.editText?.text.toString()
            val password = binding.registerTextFieldUserPassword.editText?.text.toString()
            viewModel.register(name, password)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}