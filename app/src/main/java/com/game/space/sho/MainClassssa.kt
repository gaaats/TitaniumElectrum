package com.game.space.sho

import android.app.Application
import com.game.space.sho.Unded.oneSignalCode
import com.onesignal.OneSignal

class MainClassssa:Application() {

    override fun onCreate() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(oneSignalCode)
        super.onCreate()
    }

}