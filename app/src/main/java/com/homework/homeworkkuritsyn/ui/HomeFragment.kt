package com.homework.homeworkkuritsyn.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homework.homeworkkuritsyn.databinding.FragmentHomeBinding
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.UserRoleEntity
import java.math.BigDecimal
import java.math.BigInteger

class HomeFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loanList.apply {
            val loanAdapter = LoanAdapter(onClickLoan = {

            })
            loanAdapter.loans = listOf(
                LoanEntity(
                    BigInteger.TEN,
                    "fdf434",
                    "Bob",
                    1,
                    "Marley",
                    BigDecimal.ONE,
                    30,
                    "48445",
                    EnumStateEntity.REGISTERED
                )
            )
            adapter = loanAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}