package com.kiwi.dailyoffer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.JsonObject
import com.kiwi.dailyoffer.repository.FlightsDatasource
import com.kiwi.dailyoffer.ui.main.MainFragment
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        flightsDatasource.flights("BTS","MAD","08/09/2019","18/09/2019").enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                //Utils.insertLogActionIntoDB("sendAppVersion()","Failed","", t.localizedMessage,databaseHelper)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                //Utils.insertLogActionIntoDB("sendAppVersion()","onResponse","", response.message() + response.body(),databaseHelper)
            }
        })

    }

}
