package com.game.space.sho

import android.app.Application
import android.util.Log
import com.game.space.sho.Unded.oneSignalCode
import com.onesignal.OneSignal
import org.json.JSONException
import org.json.JSONObject

class MainClassssa:Application() {

    override fun onCreate() {
        Log.d("lolo", "MainClassssa:Application() onCreate")
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(oneSignalCode)
        super.onCreate()
    }

    companion object{
        var typeOfPromotion = ""
        var link = ""
    }


}