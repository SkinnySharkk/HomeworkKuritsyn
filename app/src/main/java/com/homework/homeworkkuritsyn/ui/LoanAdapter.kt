package com.homework.homeworkkuritsyn.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homework.homeworkkuritsyn.databinding.ItemLoanBinding
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity

class LoanAdapter(
    private val onClickLoan: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var loans: List<LoanEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(binding, onClickLoan)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as LoanViewHolder).bind(loans[position])
    }

    override fun getItemCount(): Int = loans.size

    class LoanViewHolder(
        private val binding: ItemLoanBinding,
        private val onClickLoan: () -> Unit
    ) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(loanEntity: LoanEntity) {
            binding.textView.text = loanEntity.firstName
        }
    }
}