package com.homework.homeworkkuritsyn.ui.historyloans

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.homework.homeworkkuritsyn.databinding.ItemLoanBinding
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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
            binding.amountItemTxt.text = String.format(Locale.getDefault(), loanEntity.amount.toString())
            binding.dateItemTxt.text = loanEntity.date.split("T")[0]
            binding.statusItemTxt.text = when(loanEntity.state) {
                EnumStateEntity.REGISTERED -> {"Зарегистрирован"}
                EnumStateEntity.REJECTED -> {"Отклоненный"}
                EnumStateEntity.APPROVED -> {"Одобренный"}
            }
        }
    }
}