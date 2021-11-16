package com.example.retrofit_practice.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofit_practice.fragments.HistoryTabFragment
import com.example.retrofit_practice.network.entity.history.HistoryByCountry
import com.example.retrofit_practice.util.Status
import java.lang.Exception

class HistoryViewPagerAdapter(
    val fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var confirmedData: Map<String, HistoryByCountry>,
    var deathData: Map<String, HistoryByCountry>
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val status = arrayOf(Status.Confirmed, Status.Deaths)

    override fun getItemCount(): Int = status.size

    override fun createFragment(position: Int): Fragment {
        return if (status[position] == Status.Confirmed){
            HistoryTabFragment.create(status[position], confirmedData)
        }else{
            HistoryTabFragment.create(status[position], deathData)
        }
    }

    fun updateConfirmed(newConfirmed: Map<String, HistoryByCountry>){
        confirmedData = newConfirmed
        try {
            (fragmentManager.fragments[0] as HistoryTabFragment).updateData(confirmedData)
        }catch (e: Exception){}

    }

    fun updateDeaths(newDeaths: Map<String, HistoryByCountry>){
        deathData = newDeaths
        try {
            (fragmentManager.fragments[1] as HistoryTabFragment).updateData(deathData)
        }catch (e: Exception){}
    }

}