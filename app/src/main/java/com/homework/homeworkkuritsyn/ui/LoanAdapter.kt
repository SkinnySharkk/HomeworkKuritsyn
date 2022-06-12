package com.homework.homeworkkuritsyn.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homework.homeworkkuritsyn.databinding.ItemLoanBinding
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity

class LoanAdapter(
    private val onClickLoan: (id: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var loans: List<LoanEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val loan = loans[position]
        (holder as LoanViewHolder).bind(loan)
        holder.itemView.setOnClickListener {
            onClickLoan(loan.id)
        }
    }


    override fun getItemCount(): Int = loans.size

    class LoanViewHolder(
        private val binding: ItemLoanBinding,
    ) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(loanEntity: LoanEntity) {
            binding.amountItemTxt.text = loanEntity.amount.toString()
            binding.dateItemTxt.text = loanEntity.date
            binding.statusItemTxt.text = loanEntity.state.toString()
        }
    }
}