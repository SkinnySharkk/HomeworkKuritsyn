package com.homework.homeworkkuritsyn.ui.manualgetloan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.homework.homeworkkuritsyn.R
import com.homework.homeworkkuritsyn.presenters.manualgetloan.ManualViewModel

class ManualFragment : Fragment() {
    private lateinit var viewModel: ManualViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_manual, container, false)
    }
}