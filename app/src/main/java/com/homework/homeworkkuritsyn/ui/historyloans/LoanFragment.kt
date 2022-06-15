package com.homework.homeworkkuritsyn.ui.historyloans

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentLoanBinding
import com.homework.homeworkkuritsyn.presenters.loans.LoanViewModel
import timber.log.Timber
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
        viewModel.loan.observe(viewLifecycleOwner) { loan ->
            Timber.v(loan.toString())
            Timber.v(args.toString())
            with(binding) {
                loanFirstNameValue.text = loan.firstName
                loanLastNameValue.text = loan.lastName
                loanDataValue.text = loan.date
                loanPhoneValue.text = loan.phoneNumber
                loanSumValue.text = loan.amount.toString()
                loanPercentValue.text = loan.percent.toString()
                loanPeriodValue.text = loan.period.toString()
                loanStateValue.text = loan.state.toString()
            }
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