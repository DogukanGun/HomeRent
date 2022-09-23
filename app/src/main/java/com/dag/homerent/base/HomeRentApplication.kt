package com.dag.homerent.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.dag.homerent.base.ext.lifeCycle
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject

@HiltAndroidApp
class HomeRentApplication : MultiDexApplication(), Observer {

    @Inject
    lateinit var homeRentActivityListener: HomeRentActivityListener
    private var currentActivity: CanDropSession? = null

    private var sessionShouldEnd = false

    companion object {

        lateinit var appInstance: HomeRentApplication
        var baseUrl = "https://homerent-dag.herokuapp.com/"
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate() {
        super.onCreate()
        HomeRentApplication.appInstance = this
        ApplicationSessionManager.addObserver(this)
        val homeRentAppLifeCycle = this.lifeCycle(
            activityCreated = {
                // All Activities Portrait
                it?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            },
            activityResumed = {
                if (it is CanDropSession) {
                    currentActivity = it
                    if (sessionShouldEnd) {
                        currentActivity?.dropSession()
                        sessionShouldEnd = false
                    }
                }
            },
            activityPaused = {
                if (it == currentActivity) {
                    currentActivity = null
                }
            }
        )
        registerActivityLifecycleCallbacks(homeRentActivityListener)
        registerActivityLifecycleCallbacks(homeRentAppLifeCycle)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ApplicationSessionManager.deleteObserver(this)
    }

    override fun update(p0: Observable?, p1: Any?) {
        sessionShouldEnd = true
        currentActivity?.let {
            it.dropSession()
            sessionShouldEnd = false
        }
    }
}