package com.example.retrofit_practice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofit_practice.R
import com.example.retrofit_practice.util.Status

class HistoryTabFragment : Fragment() {

    private lateinit var status: Status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history_tab, container, false)

        return view
    }

    companion object {
        fun create(status: Status) = HistoryTabFragment().also { it.status = status }
    }
}