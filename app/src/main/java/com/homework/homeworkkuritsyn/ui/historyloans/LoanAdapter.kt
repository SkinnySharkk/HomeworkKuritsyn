package com.homework.homeworkkuritsyn.ui.historyloans

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.homework.homeworkkuritsyn.databinding.ItemLoanBinding
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import java.util.*

class LoanAdapter(
    private val onClickLoan: (id: Int) -> Unit
) : ListAdapter<LoanEntity, LoanAdapter.LoanViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val binding = ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(binding)
    }
    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        val loan = getItem(position)
        holder.bind(loan)
        holder.itemView.setOnClickListener {
            onClickLoan(loan.id)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<LoanEntity>() {
        override fun areItemsTheSame(oldItem: LoanEntity, newItem: LoanEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LoanEntity, newItem: LoanEntity) =
            oldItem == newItem
    }



    class LoanViewHolder(
        private val binding: ItemLoanBinding,
    ) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(loanEntity: LoanEntity) {
            binding.amountItemTxt.text =
                String.format(Locale.getDefault(), loanEntity.amount.toString())
            binding.dateItemTxt.text = loanEntity.date.split("T")[0]
            binding.statusItemTxt.text = when (loanEntity.state) {
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
        }
    }
}