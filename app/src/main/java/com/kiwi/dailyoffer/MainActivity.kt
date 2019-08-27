package com.kiwi.dailyoffer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiwi.dailyoffer.repository.FlightsDatasource
import com.kiwi.dailyoffer.view.main.MainFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val flightsDatasource : FlightsDatasource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()

/*        flightsDatasource.flightsOldWay("BTS",null,"08/09/2019","18/09/2019").enqueue(object : Callback<FlightSearchResponse> {
            override fun onFailure(call: Call<FlightSearchResponse>, t: Throwable) {
                //Utils.insertLogActionIntoDB("sendAppVersion()","Failed","", t.localizedMessage,databaseHelper)
            }

            override fun onResponse(call: Call<FlightSearchResponse>, response: Response<FlightSearchResponse>) {
                Log.d("Response",response.body().toString())
                //Utils.insertLogActionIntoDB("sendAppVersion()","onResponse","", response.message() + response.body(),databaseHelper)
            }
        })*/



    }

}
