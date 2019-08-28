package com.kiwi.dailyoffer.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.kiwi.dailyoffer.R
import com.kiwi.dailyoffer.view.offer.OfferFragment
import com.kiwi.dailyoffer.view.offer.OfferFragmentPagerAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapterViewPager = OfferFragmentPagerAdapter(fragmentManager)
        viewPager.adapter = adapterViewPager

        spring_dots_indicator.setViewPager(viewPager)

        viewModel.searchEvent.observe(this, Observer { searchEvent ->
            if (searchEvent != null) {
                if (searchEvent.isLoading) {
                    displayProgress()
                } else {
                    hideProgress()
                    if (searchEvent.isSuccess) {

                        val currentTab = viewPager.currentItem
                        val currentTabFragment = adapterViewPager.getRegisteredFragment(currentTab) as OfferFragment?
                        val previousTabFragment = adapterViewPager.getRegisteredFragment(currentTab-1) as OfferFragment?
                        val nextTabFragment = adapterViewPager.getRegisteredFragment(currentTab+1) as OfferFragment?
                        currentTabFragment?.refreshData()
                        previousTabFragment?.refreshData()
                        nextTabFragment?.refreshData()

                        Toast.makeText(context,"Data loaded successfully",Toast.LENGTH_LONG).show()
                    } else {
                        noDataText.text = "Data loading failed! Try again later."
                        Toast.makeText(context,"Data loading failed!",Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

//        main.setOnRefreshListener {
//            viewModel.searchFlights()
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        if (viewModel.areFlightsNullOrEmpty()) {
            viewModel.searchFlights()
        } else {
            hideProgress()
        }
    }

    fun displayProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
        noDataBox.visibility = View.GONE
        //main.isRefreshing = false
    }

}
