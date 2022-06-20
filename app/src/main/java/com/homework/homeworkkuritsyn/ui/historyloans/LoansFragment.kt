package com.homework.homeworkkuritsyn.ui.historyloans

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.homework.homeworkkuritsyn.R
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentLoansBinding
import com.homework.homeworkkuritsyn.presenters.historyloans.LoansViewModel
import com.homework.homeworkkuritsyn.presenters.historyloans.LoansViewModelUiState
import com.homework.homeworkkuritsyn.ui.MainActivity
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
        setHasOptionsMenu(true)
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoansViewModelUiState.Idle -> {
                    binding.loanList.visibility = View.VISIBLE
                    binding.loansProgressBar.visibility = View.GONE
                    binding.emptyListTxt.visibility = View.GONE
                }
                is LoansViewModelUiState.Loading -> {
                    binding.loanList.visibility = View.GONE
                    binding.emptyListTxt.visibility = View.GONE
                    binding.loansProgressBar.visibility = View.VISIBLE
                }
                is LoansViewModelUiState.Success -> {
                    binding.loanList.apply {
                        val loanAdapter = LoanAdapter(onClickLoan = { id ->
                            findNavController().navigate(
                                LoansFragmentDirections.actionLoansFragmentToLoanFragment(
                                    id
                                )
                            )
                        })
                        if (state.loans.isNotEmpty()) {
                            loanAdapter.submitList(state.loans)
                            binding.loanList.visibility = View.VISIBLE
                            binding.loansProgressBar.visibility = View.GONE
                            binding.emptyListTxt.visibility = View.GONE
                        } else {
                            binding.loanList.visibility = View.GONE
                            binding.loansProgressBar.visibility = View.GONE
                            binding.emptyListTxt.visibility = View.VISIBLE
                        }
                        adapter = loanAdapter
                    }
                }
                is LoansViewModelUiState.Error -> {
                    binding.loanList.visibility = View.GONE
                    binding.loansProgressBar.visibility = View.GONE
                    binding.emptyListTxt.text = state.response
                    binding.emptyListTxt.visibility = View.VISIBLE
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.updateItem -> {
                viewModel.updateLoans()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}