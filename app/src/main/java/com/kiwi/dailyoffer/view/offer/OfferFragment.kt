package com.kiwi.dailyoffer.view.offer

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kiwi.dailyoffer.R
import kotlinx.android.synthetic.main.offer_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel





class OfferFragment : Fragment() {

    companion object {
        fun newInstance(position : Int) : OfferFragment {
            val offerFragment = OfferFragment()
            val args = Bundle()
            args.putInt("position",position)
            offerFragment.arguments = args

            return offerFragment
        }
    }

    var position: Int? = null
    private val viewModel by sharedViewModel<OfferViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt("position")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.offer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(OfferViewModel::class.java)

        Glide.with(this).load("https://images.kiwi.com/photos/600x330/london_gb.jpg").into(image);

    }


}
