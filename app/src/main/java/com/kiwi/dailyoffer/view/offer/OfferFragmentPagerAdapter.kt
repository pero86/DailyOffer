package com.kiwi.dailyoffer.view.offer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kiwi.dailyoffer.utils.NUMBER_OF_OFFERS
import com.kiwi.dailyoffer.utils.SmartFragmentStatePagerAdapter

class OfferFragmentPagerAdapter(fm: FragmentManager?) : SmartFragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return OfferFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return NUMBER_OF_OFFERS
    }

}