package com.homework.homeworkkuritsyn.ui.historyloans

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentLoanBinding
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.presenters.historyloans.LoanVieModelUiState
import com.homework.homeworkkuritsyn.presenters.historyloans.LoanViewModel
import java.util.*
import javax.inject.Inject

class LoanFragment : Fragment() {

    private var _binding: FragmentLoanBinding? = null
    private val binding get() = _binding!!
    private val args: LoanFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoanViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoan(args.idLoan)
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoanVieModelUiState.Loading -> {
                    binding.loanGroup.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                    binding.loanProgressBar.visibility = View.VISIBLE
                }
                is LoanVieModelUiState.Success -> {
                    with(binding) {
                        loanGroup.visibility = View.VISIBLE
                        loanProgressBar.visibility = View.GONE
                        loanFirstNameValue.text = state.loan.firstName
                        loanLastNameValue.text = state.loan.lastName
                        loanDataValue.text = state.loan.date.split("T")[0]
                        loanPhoneValue.text = state.loan.phoneNumber
                        loanSumValue.text =
                            String.format(Locale.getDefault(), state.loan.amount.toString())
                        loanPercentValue.text =
                            String.format(Locale.getDefault(), state.loan.percent.toString())
                        loanPeriodValue.text = state.loan.period.toString()
                        loanStateValue.text = when (state.loan.state) {
                            EnumStateEntity.REGISTERED -> {
                                "Зарегистрирован"
                            }
                            EnumStateEntity.REJECTED -> {
                                "Отклоненный"
                            }
                            EnumStateEntity.APPROVED -> {
                                "Одобренный"
                            }
                        }
                        errorTextView.visibility = View.GONE
                    }
                }
                is LoanVieModelUiState.Error -> {
                    binding.loanGroup.visibility = View.GONE
                    binding.loanProgressBar.visibility = View.GONE
                    binding.errorTextView.text = state.response
                    binding.errorTextView.visibility = View.VISIBLE
                }
            }
        }
        binding.loanToBankBtn.setOnClickListener {
            findNavController().navigate(LoanFragmentDirections.actionLoanFragmentToManualBankFragment())
        }
        binding.loanToCardBtn.setOnClickListener {
            findNavController().navigate(LoanFragmentDirections.actionLoanFragmentToManualCardFragment())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.loansComponent().create().inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}