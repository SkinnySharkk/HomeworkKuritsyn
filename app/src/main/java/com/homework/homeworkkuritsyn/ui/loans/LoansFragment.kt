package com.homework.homeworkkuritsyn.ui.loans

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentHomeBinding
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.presenters.loans.LoansViewModel
import com.homework.homeworkkuritsyn.ui.LoanAdapter
import timber.log.Timber
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class LoansFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoansViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.loansComponent().create().inject(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loans.observe(viewLifecycleOwner) { loans ->
            binding.loanList.apply {
                val loanAdapter = LoanAdapter(onClickLoan = {

                })
//            loanAdapter.loans = listOf(
//                LoanEntity(
//                    BigInteger.TEN,
//                    "fdf434",
//                    "Bob",
//                    1,
//                    "Marley",
//                    BigDecimal.ONE,
//                    30,
//                    "48445",
//                    EnumStateEntity.REGISTERED
//                )
//            )
                Timber.v("loans.toString()")
                Timber.v(loans.toString())
                loanAdapter.loans = loans
                adapter = loanAdapter
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}