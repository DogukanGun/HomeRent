package com.dag.homerent.base

import android.content.Intent
import com.dag.homerent.R
import com.dag.homerent.dailogbox.KeyValueModel
import com.dag.homerent.ui.home.activity.HomeActivity
import javax.inject.Inject

class HomerentNavigator @Inject constructor(private val activityListener: HomeRentActivityListener) {

    companion object{
        const val ARGUMENTS = "ARGUMENTS"
    }

    fun openActivityByDeepLink(
        deepLinkActivityType:DeepLinkActivityType?,
        arguments:Array<KeyValueModel<String, Any?>>? = null,
        requestCode:RequestCodeType? = null,
        anim:TransitionAnimationType = TransitionAnimationType.LEFT_LEFT,
        flags:Int? = null,
    ){
        activityListener.currentActivity?.run {
            deepLinkActivityType?.let {
                val intent = Intent(this,it.clazz)
                if (arguments!=null){
                    intent.putExtra(HomerentNavigator.ARGUMENTS,arguments)
                }
                if (flags!=null){
                    intent.addFlags(flags)
                }
                if (requestCode!=null){
                    startActivityForResult(intent,requestCode.code)
                }else{
                    startActivity(intent)
                }
                overridePendingTransition(anim.enterAnim,anim.exitAnim)
            }
        }
    }
}

enum class DeepLinkActivityType(val functionCode:Int,val functionName:String,val clazz: Class<*>){
    MAIN_ACTIVITY(11000,"Main",HomeActivity::class.java);
    companion object {
        fun findActivity(searchKey:Any) = values().firstOrNull {
            if (searchKey is Int?) it.functionCode == searchKey else if (searchKey is String?) it.functionName == searchKey else false
        }
    }
}

enum class RequestCodeType(val code:Int){
    REFRESH(1900)
}

enum class TransitionAnimationType(val enterAnim:Int,val exitAnim:Int){
    LEFT_LEFT(R.anim.slide_in_left,R.anim.slide_out_left),
    RIGHT_RIGHT(R.anim.slide_in_right,R.anim.slide_out_right)
}