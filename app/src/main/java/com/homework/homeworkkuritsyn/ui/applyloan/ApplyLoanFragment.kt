package com.homework.homeworkkuritsyn.ui.applyloan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.homework.homeworkkuritsyn.appComponent
import com.homework.homeworkkuritsyn.databinding.FragmentApplyBinding
import com.homework.homeworkkuritsyn.presenters.applyloan.ApplyLoanViewModel
import java.math.BigInteger
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
        viewModel.selectSum.observe(viewLifecycleOwner) { selectSum ->
            binding.sumLoanTextField.editText?.setText(selectSum.toString())
        }
        viewModel.loanConditions.observe(viewLifecycleOwner) { conditions ->
            with(binding) {
                amountConditionsValue.text = conditions.maxAmount.toString()
                percentConditionsValue.text = conditions.percent.toString()
                periodConditionsValue.text = conditions.period.toString()
                viewModel.setSum(START_PERCENT_SUM)
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
                val firstName = firstNameLoanTextField.editText?.text.toString()
                val lastName = lastNameLoanTextField.editText?.text.toString()
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
                    Toast.makeText(context, "Empty fields", Toast.LENGTH_LONG).show()
                }
            }
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

}