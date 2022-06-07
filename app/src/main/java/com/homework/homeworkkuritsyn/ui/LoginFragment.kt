package com.homework.homeworkkuritsyn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.homework.homeworkkuritsyn.databinding.FragmentLoginBinding
import com.homework.homeworkkuritsyn.presenters.LoginViewModel

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private val binding get() = _binding!!
    private var _binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}