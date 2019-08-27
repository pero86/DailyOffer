package com.kiwi.dailyoffer.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kiwi.dailyoffer.R
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

        viewModel.searchEvent.observe(this, Observer { searchEvent ->
            if (searchEvent != null) {
                if (searchEvent.isLoading) {
                    displayProgress()
                } else {
                    hideProgress()
                    if (searchEvent.isSuccess) {
                        Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        main.setOnRefreshListener {
            viewModel.searchFlights()
        }

        main.spring_dots_indicator.setViewPager(viewPager)

        val adapterViewPager = OfferFragmentPagerAdapter(fragmentManager)
        main.viewPager.adapter = adapterViewPager
    }

    override fun onResume() {
        super.onResume()
        viewModel.searchFlights()

    }

    fun displayProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
        main.isRefreshing = false
    }

}