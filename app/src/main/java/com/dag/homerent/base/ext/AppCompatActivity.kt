package com.dag.homerent.base.ext

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import com.dag.homerent.R

fun FragmentActivity.openActivity(
    intent: Intent,
    enterAnim:Int = R.anim.slide_in_left,
    exitAnim:Int = R.anim.slide_out_left
){
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
    overridePendingTransition(enterAnim,exitAnim)
}

fun FragmentActivity.openClAppsSetting(){
    startActivity(
        Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package",packageName,null)
        }
    )
}