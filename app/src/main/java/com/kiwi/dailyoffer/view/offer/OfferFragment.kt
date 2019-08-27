package com.kiwi.dailyoffer.view.offer

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.kiwi.dailyoffer.R
import com.kiwi.dailyoffer.view.main.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.offer_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ViewModelStoreOwnerDefinition
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


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
    //private val viewModel by sharedViewModel<OfferViewModel>(){ parametersOf(position) }
    private val viewModel: OfferViewModel by sharedViewModel()
    //private val viewModel by sharedViewModel<OfferViewModel> { parametersOf(position) }
    //private val viewModel: OfferViewModel by viewModel(){ parametersOf(position) }
    private val picasso : Picasso by inject()

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

        //Glide.with(this).load("https://images.kiwi.com/photos/600x330/london_gb.jpg").into(image)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiData.observe(this, Observer<ResultUIModel> {
            if (it != null) {
                val url = getString(R.string.IMAGE_SERVER_URL) +  it.flightInfo?.mapIdto + ".jpg"
                //Glide.with(this).load(url).into(image)
                Log.d("*****","loading image: + $url")
                picasso.load(url).into(image)
            }
        })

        viewModel.getFlightData(position ?: 0)
    }

    override fun onResume() {
        super.onResume()
        viewModel.printPosition()
    }


}
