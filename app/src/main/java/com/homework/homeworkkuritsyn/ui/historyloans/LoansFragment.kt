package com.homework.homeworkkuritsyn.ui.historyloans

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentLoansBinding
import com.homework.homeworkkuritsyn.presenters.loans.LoansViewModel
import com.homework.homeworkkuritsyn.ui.MainActivity
import timber.log.Timber
import javax.inject.Inject

class LoansFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentLoansBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoansViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoansBinding.inflate(inflater, container, false)
        (activity as MainActivity).unLockDrawer()
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
                val loanAdapter = LoanAdapter(onClickLoan = { id->
                    Timber.v("id = $id")
                    findNavController().navigate(LoansFragmentDirections.actionLoansFragmentToLoanFragment(id))
                })
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