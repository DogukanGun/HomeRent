package com.dag.homerent.network

import com.dag.homerent.base.BaseDialogBoxUtil
import com.dag.homerent.base.HomeRentActivity
import com.dag.homerent.base.HomeRentActivityListener
import com.dag.homerent.base.ext.tryCatch
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset
import javax.inject.Inject

class DialogBoxInterceptor @Inject constructor(
    private val homeRentActivityListener: HomeRentActivityListener,
    private val gson: Gson,
    private val dialogBoxUtil: BaseDialogBoxUtil
) : Interceptor {
    companion object{
        private val UTF8 = Charset.forName("UTF-8")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)

        tryCatch({
            val responseBody = response.body

            val source = responseBody?.source()
            source?.request(Long.MAX_VALUE)

            val responseCharset = responseBody?.contentType()?.run {
                charset(UTF8)
            } ?: UTF8

            if (responseBody?.contentLength() != 0L) {

                val body = source?.buffer?.clone()?.readString(responseCharset)

                val baseResponseList = ArrayList<BaseResponse>()
                body?.let {
                    if (it.startsWith("[") && it.contains("{")) {
                        val arrayResponseList = gson.fromJson(it, Array<BaseResponse>::class.java)
                        baseResponseList.addAll(arrayResponseList)
                    } else if (it.startsWith("{")) {
                        baseResponseList.add(gson.fromJson(it, BaseResponse::class.java))
                    } else {
                        return response
                    }
                }

                tryCatch({
                    baseResponseList.forEach { baseResponse ->
                        baseResponse.dialogBox?.let {
                            homeRentActivityListener.currentActivity?.run {
                                runOnUiThread {
                                    if (this is HomeRentActivity<*, *>) {
                                        hideProgress()
                                    }

                                    if (it.showManuel != true) {
                                        dialogBoxUtil.showGenericDialog(it, this)
                                    }
                                }
                                return@forEach
                            }
                        }
                    }
                })
            }
        })

        return response
    }
}
