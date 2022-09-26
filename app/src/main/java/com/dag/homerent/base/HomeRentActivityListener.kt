package com.dag.homerent.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.dag.homerent.ui.home.HomeActivity
import javax.inject.Inject

class HomeRentActivityListener @Inject constructor() : Application.ActivityLifecycleCallbacks  {

    var currentActivity: Activity? = null
    var context: Context? = null
    private var currentActivityStack: MutableList<Activity> = ArrayList()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity
        context = activity
        currentActivityStack.add(activity)
    }

    fun isActivityRunning(activityClass: Class<out Activity>): Boolean {
        for (activity in currentActivityStack) {
            if (activity.javaClass == activityClass) {
                return true
            }
        }
        return false
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
        context = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
        context = activity
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityDestroyed(activity: Activity) {
        if (activity == currentActivity) {
            currentActivity = null
        }
        if (activity is HomeActivity) {
            activity.dropSession()
        }
        currentActivityStack.remove(activity)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
}