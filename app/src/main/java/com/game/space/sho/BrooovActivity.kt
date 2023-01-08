package com.game.space.sho

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.MediaStore
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.appsflyer.AppsFlyerLib
import com.game.space.sho.Unded.pacaaakageNameMain
import com.game.space.sho.databinding.ActivityBrooovBinding
import com.game.space.sho.room.DevilDataBase
import com.game.space.sho.room.DevilEntity
import com.google.android.material.snackbar.Snackbar
import com.onesignal.OneSignal
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException

class BrooovActivity : AppCompatActivity() {
    private val hyyhjuuu = 1

    private val devilDAO by lazy {
        DevilDataBase.getDataBase(application).getDevilDAO()
    }

    private lateinit var devilData:DevilEntity

    override fun onBackPressed() {
        if (cdccdcd.canGoBack()) {
            if (gttggtgt) {
                cdccdcd.stopLoading()
                cdccdcd.loadUrl(ccdcdcdcdf)
            }
            this.gttggtgt = true
            cdccdcd.goBack()
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                gttggtgt = false
            }, 2000)

        } else {
            super.onBackPressed()
        }
    }


    var jjkkjk: ValueCallback<Array<Uri>>? = null
    lateinit var cdccdcd: WebView

    var ccdcdcdcdf = ""
    fun saaaveпеепепеп(lurlurlurlurlur: String?) {
        if (!lurlurlurlurlur!!.contains("t.me")) {

            if (ccdcdcdcdf == "") {
                ccdcdcdcdf = getSharedPreferences(
                    "SP_WEBVIEW_PREFS",
                    AppCompatActivity.MODE_PRIVATE
                ).getString(
                    "SAVED_URL",
                    lurlurlurlurlur
                ).toString()

                val preferences =
                    getSharedPreferences("SP_WEBVIEW_PREFS", AppCompatActivity.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putString("SAVED_URL", lurlurlurlurlur)
                editor.apply()
            }
        }
    }

    lateinit var kiikiki: ActivityBrooovBinding
    var bvbbbb: String? = null

    private fun kiklooooпееппееп() {
        val webSettingsggttggt = cdccdcd.settings
        webSettingsggttggt.javaScriptEnabled = true

        webSettingsggttggt.useWideViewPort = true

        webSettingsggttggt.loadWithOverviewMode = true
        webSettingsggttggt.allowFileAccess = true
        webSettingsggttggt.domStorageEnabled = true
        webSettingsggttggt.userAgentString = webSettingsggttggt.userAgentString.replace("; wv", "")

        webSettingsggttggt.javaScriptCanOpenWindowsAutomatically = true
        webSettingsggttggt.setSupportMultipleWindows(false)

        webSettingsggttggt.displayZoomControls = false
        webSettingsggttggt.builtInZoomControls = true
        webSettingsggttggt.setSupportZoom(true)

        webSettingsggttggt.pluginState = WebSettings.PluginState.ON
        webSettingsggttggt.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettingsggttggt.setAppCacheEnabled(true)

        webSettingsggttggt.allowContentAccess = true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            devilData = devilDAO.getAllDevilData().first()
        }

        kiikiki = ActivityBrooovBinding.inflate(layoutInflater)
        setContentView(kiikiki.root)



        cdccdcd = kiikiki.vebViiiVegg
        Snackbar.make(
            kiikiki.root, "Loading...",
            Snackbar.LENGTH_LONG
        ).show()


        val jujujujujju = CookieManager.getInstance()
        jujujujujju.setAcceptCookie(true)
        jujujujujju.setAcceptThirdPartyCookies(cdccdcd, true)
        kiklooooпееппееп()
        val vbvvvvvvv: Activity = this
        cdccdcd.webViewClient = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                try {
                    if (URLUtil.isNetworkUrl(url)) {
                        return false
                    }
                    if (gtgtgtgt(url)) {

                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } else {

                        Toast.makeText(
                            applicationContext,
                            "Application is not installed",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")
                            )
                        )
                    }
                    return true
                } catch (e: Exception) {
                    return false
                }
                view.loadUrl(url)
            }


            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                saaaveпеепепеп(url)
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Toast.makeText(vbvvvvvvv, description, Toast.LENGTH_SHORT).show()
            }


        }
        cdccdcd.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                jjkkjk?.onReceiveValue(null)
                jjkkjk = filePathCallback
                var ggtgtgttg: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (ggtgtgttg!!.resolveActivity(packageManager) != null) {

                    var vvvv: File? = null
                    try {
                        vvvv = jujujiii()
                        ggtgtgttg.putExtra("PhotoPath", bvbbbb)
                    } catch (ex: IOException) {
                    }

                    if (vvvv != null) {
                        bvbbbb = "file:" + vvvv.absolutePath
                        ggtgtgttg.putExtra(
                            MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(vvvv)
                        )
                    } else {
                        ggtgtgttg = null
                    }
                }
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                val arrayOfIntents: Array<Intent?> =
                    ggtgtgttg?.let { arrayOf(it) } ?: arrayOfNulls(0)
                val juujujujuj = Intent(Intent.ACTION_CHOOSER)
                juujujujuj.putExtra(Intent.EXTRA_INTENT, intent)
                juujujujuj.putExtra(Intent.EXTRA_TITLE, getString(R.string.takeeeee))
                juujujujuj.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOfIntents)
                startActivityForResult(
                    juujujujuj, hyyhjuuu
                )
                return true
            }

            @Throws(IOException::class)
            private fun jujujiii(): File {
                var juukiiii = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "DirectoryNameHere"
                )
                if (!juukiiii.exists()) {
                    juukiiii.mkdirs()
                }

                juukiiii =
                    File(juukiiii.toString() + File.separator + "IMG_" + System.currentTimeMillis() + ".jpg")
                return juukiiii
            }

        }

        lifecycleScope.launch {
            cdccdcd.loadUrl(saaacfff())
        }

    }


    private fun jukiikikkiik(string: String) {
        OneSignal.setExternalUserId(
            string,
            object : OneSignal.OSExternalUserIdUpdateCompletionHandler {
                override fun onSuccess(results: JSONObject) {
                    try {
                        if (results.has("push") && results.getJSONObject("push").has("success")) {
                            val frrfrfrrf = results.getJSONObject("push").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for push status: $frrfrfrrf"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("email") && results.getJSONObject("email").has("success")) {
                            val vfnkvfnfvvfn =
                                results.getJSONObject("email").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for email status: $vfnkvfnfvvfn"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("sms") && results.getJSONObject("sms").has("success")) {
                            val kiikikfrrfrfrrr = results.getJSONObject("sms").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for sms status: $kiikikfrrfrfrrr"
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


    private suspend fun saaacfff(): String {

        delay(1500)

        val bbgbgbg = "deviceID="
        val kjjkjk = "sub_id_1="
        val adidddddd = "ad_id="
        val sub4frfrr = "sub_id_4="
        val bgbggbbg = "sub_id_5="
        val sub6frfr = "sub_id_6="

        val sharedPreferences =
            getSharedPreferences("SP_WEBVIEW_PREFS", AppCompatActivity.MODE_PRIVATE)

        val hyhyyygfdddd = pacaaakageNameMain



        val maaainID = devilData.advertid
        val deeeepLinka = devilData.deeplink

        val naaaming = devilData.naming

        val jukiiiii = AppsFlyerLib.getInstance().getAppsFlyerUID(this)

        val liinka = devilData.link


        AppsFlyerLib.getInstance().setCollectAndroidID(true)

        val mmnnhnh = "naming"
        val deppfrfrf = "deeporg"


        lifecycleScope.launch {
            delay(1500)
            Snackbar.make(
                kiikiki.root, "deeeepLinka in VEBVIEV is ${deeeepLinka}",
                Snackbar.LENGTH_SHORT
            ).show()
            delay(1500)
            Snackbar.make(
                kiikiki.root, "naaaming in VEBVIEV ${naaaming}",
                Snackbar.LENGTH_SHORT
            ).show()

            delay(1500)
            Snackbar.make(
                kiikiki.root, "link is ${liinka}",
                Snackbar.LENGTH_SHORT
            ).show()

            delay(1500)
            Snackbar.make(
                kiikiki.root, "adverId is ${maaainID}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        val jhjhjhj = Build.VERSION.RELEASE

        var kikikik = ""
        if (naaaming != "null") {
            kikikik =
                "$liinka$kjjkjk$naaaming&$bbgbgbg$jukiiiii&$adidddddd$maaainID&$sub4frfrr$hyhyyygfdddd&$bgbggbbg$jhjhjhj&$sub6frfr$mmnnhnh"
            jukiikikkiik(jukiiiii.toString())
        } else {
            kikikik =
                "$liinka$kjjkjk$deeeepLinka&$bbgbgbg$jukiiiii&$adidddddd$maaainID&$sub4frfrr$hyhyyygfdddd&$bgbggbbg$jhjhjhj&$sub6frfr$deppfrfrf"
            jukiikikkiik(jukiiiii.toString())
        }
        Log.d("lolo", "link is $kikikik")
        return sharedPreferences.getString("SAVED_URL", kikikik).toString()
    }


    private fun gtgtgtgt(uri: String): Boolean {

        val hhnhnhhn = packageManager
        try {

            hhnhnhhn.getPackageInfo("org.telegram.messenger", PackageManager.GET_ACTIVITIES)

            return true
        } catch (e: PackageManager.NameNotFoundException) {

        }
        return false
    }

    private var gttggtgt = false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != hyyhjuuu || jjkkjk == null) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        var arrayOfUris: Array<Uri>? = null

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (data == null || data.data == null) {
                arrayOfUris = arrayOf(Uri.parse(bvbbbb))
            } else {
                val uriString = data.dataString
                if (uriString != null) {
                    arrayOfUris = arrayOf(Uri.parse(uriString))
                }
            }
        }
        jjkkjk?.onReceiveValue(arrayOfUris)
        jjkkjk = null
    }
}