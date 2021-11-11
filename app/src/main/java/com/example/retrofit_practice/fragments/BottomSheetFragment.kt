package com.example.retrofit_practice.fragments

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofit_practice.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet)).apply {
            peekHeight = (200 * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
            state = BottomSheetBehavior.STATE_EXPANDED

        }
        return view
    }

}