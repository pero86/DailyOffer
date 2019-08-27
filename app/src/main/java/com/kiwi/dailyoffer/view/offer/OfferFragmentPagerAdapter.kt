package com.kiwi.dailyoffer.view.offer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kiwi.dailyoffer.utils.SmartFragmentStatePagerAdapter

class OfferFragmentPagerAdapter(fm: FragmentManager?) : SmartFragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 // Fragment # 0 - This will show FirstFragment
            -> OfferFragment.newInstance(position)
            1 // Fragment # 0 - This will show FirstFragment different title
            -> OfferFragment.newInstance(position)
            2 // Fragment # 1 - This will show SecondFragment
            -> OfferFragment.newInstance(position)
            else -> OfferFragment.newInstance(position)
        }
    }

    override fun getCount(): Int {
        return 3
    }

}