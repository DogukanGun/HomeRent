package com.dag.homerent.dailogbox.authentication

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.dag.homerent.R
import com.dag.homerent.base.HomeRentViewState
import com.dag.homerent.base.HomerentSessionUtil
import com.dag.homerent.base.ext.openActivity
import com.dag.homerent.dailogbox.DefaultModelDialog
import com.dag.homerent.dailogbox.ModelDialog
import com.dag.homerent.dailogbox.ModelDialogButton
import com.dag.homerent.dailogbox.ModelDialogHandler
import com.dag.homerent.ui.home.HomeActivity
import javax.inject.Inject

object SessionTimeoutViewEffect: HomeRentViewState

class SessionTimeoutHandler @Inject constructor(
    private val modelDialogHandler: ModelDialogHandler,
){

    fun handle(activity: FragmentActivity){
        HomerentSessionUtil.isSessionEndingProcessStarted = true
        modelDialogHandler.showDialog(activity,createUnAuthenticateDialog(activity))
    }

    private fun createUnAuthenticateDialog(activity: FragmentActivity): ModelDialog {
        return DefaultModelDialog(
            titleRes = R.string.app_name,
            messageRes = R.string.app_name,
            positiveButton = ModelDialogButton(
                textRes = R.string.app_name,
                onClick = {
                    HomerentSessionUtil.endSession()
                    activity.openActivity(
                        Intent(activity, HomeActivity::class.java)
                    )
                }
            ),
            isCancelable = false
        )
    }

}