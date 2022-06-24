package com.homework.homeworkkuritsyn.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.homework.homeworkkuritsyn.databinding.FragmentStartBinding
import com.homework.homeworkkuritsyn.ui.MainActivity

class StartFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startLoginButton.setOnClickListener {
//            findNavController().navigate(
//                StartFragmentDirections.actionStartFragmentToLoginFragment()
//            )
        }
        binding.startRegisterButton.setOnClickListener {
//            findNavController().navigate(
//                StartFragmentDirections.actionStartFragmentToRegisterFragment()
//            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}