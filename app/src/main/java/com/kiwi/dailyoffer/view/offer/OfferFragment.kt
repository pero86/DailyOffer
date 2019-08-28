package com.kiwi.dailyoffer.view.offer

import android.graphics.Color
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
import com.kiwi.dailyoffer.utils.Utils.Companion.getColorWithAlpha
import com.kiwi.dailyoffer.view.main.MainViewModel
import com.squareup.picasso.Picasso
import khronos.Dates
import khronos.toString
import kotlinx.android.synthetic.main.offer_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ViewModelStoreOwnerDefinition
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*


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
    //private val viewModel: OfferViewModel by sharedViewModel()
    //private val viewModel by sharedViewModel<OfferViewModel> { parametersOf(position) }
    private val viewModel: OfferViewModel by viewModel(){ parametersOf(position) }
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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFlightData()

        viewModel.uiData.observe(this, Observer<ResultUIModel> {
            if (it?.flightInfo != null) {
                showData()

                val url = getString(R.string.IMAGE_SERVER_URL) +  viewModel.uiData.value?.flightInfo?.mapIdto + ".jpg"
                picasso.load(url).placeholder(R.drawable.placeholder).into(image)
                cityNameText.text = viewModel.uiData.value?.flightInfo?.cityTo
                countryNameText.text = viewModel.uiData.value?.flightInfo?.countryTo?.name

                val timestampDep = viewModel.uiData.value?.flightInfo?.dTime ?: 0
                val dateDep = Date(timestampDep*1000L)
                dateFromText.text = dateDep.toString(getString(R.string.date_format))

                val timestampArr = viewModel.uiData.value?.flightInfo?.aTime ?: 0
                val dateArr = Date(timestampArr*1000L)
                timeFromText.text = dateDep.toString("HH:mm") + " - " + dateArr.toString("HH:mm")

                priceText.text = (viewModel.uiData.value?.flightInfo?.price ?: 0).toString() + "€"

            } else {
                showNoData()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.printPosition()
    }

    fun refreshData() {
        viewModel.getFlightData()
    }

    fun showNoData() {
        //noDataBox.visibility = View.VISIBLE
    }

    fun showData() {
        //noDataBox.visibility = View.GONE
    }


}
