package com.game.space.sho

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.game.space.sho.Unded.geoApiLink1
import com.game.space.sho.Unded.geoApiLink2
import com.game.space.sho.Unded.linkaaa1
import com.game.space.sho.Unded.linkaaa2
import com.game.space.sho.Unded.linkaaa3
import com.game.space.sho.Unded.linkaaa4
import com.game.space.sho.Unded.linkaaa5
import com.game.space.sho.api.ResponseDevilHost
import com.game.space.sho.api.ResponseGeo
import com.google.gson.Gson
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class VolleyApiClient(val contteeeext: Context) {

    suspend fun getDevilApi(): ResponseDevilHost = suspendCoroutine { result ->
        val queue = Volley.newRequestQueue(contteeeext)
        val url =
            linkaaa1 + linkaaa2 + linkaaa3 + linkaaa4 + linkaaa5
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val item = Gson().fromJson(response.toString(), ResponseDevilHost::class.java)
                    result.resume(item)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            }
        )
        queue.add(request)
    }

    suspend fun getGeoData(): ResponseGeo = suspendCoroutine { result ->
        val queue = Volley.newRequestQueue(contteeeext)
        val url = geoApiLink1+ geoApiLink2
        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val item = Gson().fromJson(
                        response.toString(),
                        ResponseGeo::class.java
                    )
                    result.resume(item)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            }
        )
        queue.add(request)
    }
}