package com.homework.homeworkkuritsyn.ui.applyloan

import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.*
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.homework.homeworkkuritsyn.R
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentApplyBinding
import com.homework.homeworkkuritsyn.presenters.applyloan.ApplyLoanState
import com.homework.homeworkkuritsyn.presenters.applyloan.ApplyLoanViewModel
import com.homework.homeworkkuritsyn.presenters.applyloan.ApplyLoanViewModelUiState
import java.math.BigInteger
import java.util.*
import javax.inject.Inject

class ApplyLoanFragment : Fragment() {
    companion object {
        const val START_PERCENT_SUM: Int = 50
    }

    private var _binding: FragmentApplyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ApplyLoanViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.loansComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApplyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.selectSum.observe(viewLifecycleOwner) { selectSum ->
            binding.sumLoanTextField.editText?.setText(selectSum.toString())
        }
        binding.phoneLoanTextField.editText?.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ApplyLoanViewModelUiState.Loading -> {
                    binding.applyGroup.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorTextView.visibility = View.GONE
                }
                is ApplyLoanViewModelUiState.SuccessConditions -> {
                    with(binding) {
                        applyGroup.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        amountConditionsValue.text =
                            String.format(
                                Locale.getDefault(),
                                state.loanConditions.maxAmount.toString()
                            )
                        percentConditionsValue.text =
                            String.format(
                                Locale.getDefault(),
                                state.loanConditions.percent.toString()
                            )
                        periodConditionsValue.text = state.loanConditions.period.toString()
                        viewModel.setSum(START_PERCENT_SUM)
                        errorTextView.visibility = View.GONE
                    }
                }
                is ApplyLoanState.SuccessApply -> {
                    Snackbar.make(
                        binding.applyLoanBtn,
                        getString(R.string.apply_success),
                        Snackbar.LENGTH_LONG
                    ).setAnchorView(binding.applyLoanBtn).show()
                    findNavController().navigate(ApplyLoanFragmentDirections.actionApplyFragmentToLoansFragment())
                }
                is ApplyLoanViewModelUiState.Error -> {
                    binding.applyGroup.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.errorTextView.text = state.response
                    binding.errorTextView.visibility = View.VISIBLE
                }
            }
        }
        binding.seekBarSum.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    viewModel.setSum(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.sumLoanTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
            if (!inputText.isNullOrBlank()) {
                binding.seekBarSum.progress = viewModel.getPercentOfAmount(inputText)
            }
        }

        with(binding) {
            binding.applyLoanBtn.setOnClickListener {
                val sum = binding.sumLoanTextField.editText?.text.toString()
                val firstName = firstNameLoanTextField.editText?.text.toString().trim()
                val lastName = lastNameLoanTextField.editText?.text.toString().trim()
                val phone = phoneLoanTextField.editText?.text.toString()
                if (validData(
                        firstName = firstName,
                        lastName = lastName,
                        phone = phone,
                        sum = sum
                    )
                ) {
                    viewModel.applyLoan(
                        sum = BigInteger(sum),
                        firstName = firstName,
                        lastName = lastName,
                        phone = phone
                    )
                } else {
                    Snackbar.make(
                        binding.applyLoanBtn,
                        getString(R.string.empty_fields),
                        Snackbar.LENGTH_LONG
                    ).setAnchorView(binding.applyLoanBtn).show()
                }
            }
        }
        binding.cancelLoanBtn.setOnClickListener {
            findNavController().navigate(ApplyLoanFragmentDirections.actionApplyFragmentToLoansFragment())
        }
    }

    private fun validData(
        firstName: String,
        lastName: String,
        phone: String,
        sum: String
    ): Boolean {
        return viewModel.validData(firstName, lastName, phone, sum)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.apply_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun showHelp() {
        Snackbar.make(
            binding.applyLoanBtn,
            getString(R.string.applyHelpInfo),
            Snackbar.LENGTH_LONG
        ).setAnchorView(binding.applyLoanBtn).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.applyHelpItem -> {
                showHelp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}