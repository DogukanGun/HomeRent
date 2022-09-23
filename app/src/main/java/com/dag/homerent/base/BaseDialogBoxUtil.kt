package com.dag.homerent.base

import javax.inject.Inject
import android.app.Activity
import android.app.AlertDialog
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.core.content.ContextCompat
import com.dag.homerent.R
import com.dag.homerent.base.ext.tryCatch
import com.dag.homerent.dailogbox.*
import com.dag.homerent.network.BaseResult
import com.dag.homerent.network.HomerentService
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.alert_dialog_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class BaseDialogBoxUtil @Inject constructor(
    private val clNavigator: HomerentNavigator,
    private val service: HomerentService,
    private val homeRentActivityListener: HomeRentActivityListener
){
    var baseDialogBoxCustomActionListener:BaseDialogBoxCustomActionListener? = null
    interface BaseDialogBoxCustomActionListener{
        fun baseDialogBoxCustomAction(buttonModel: ButtonModel? = null)
    }

    fun showGenericDialog(dialogBox: DialogBoxModel, activity:Activity? = homeRentActivityListener.currentActivity){
        activity?.run {
            if (activity.isFinishing){
                return
            }
            val builder = AlertDialog.Builder(activity)
            val dialogView = activity.layoutInflater.inflate(R.layout.alert_dialog_layout,null)
            val isIconVisible = dialogBox.isIconVisible == true
            val isCancelable = dialogBox.cancelable == true
            if (isIconVisible){
                dialogView.alertDialogErrorIcon.setImageResource(R.drawable.ic_xmark_solid)
            }else{
                dialogView.alertDialogErrorIcon.visibility = View.GONE
            }
            when(dialogBox.color){
                DailogBoxColorType.ORANGE ->{
                    dialogView.alertDialogTitle.setTextColor(R.color.yellow)
                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_orange)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.black)

                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_orange)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.black)
                }
                DailogBoxColorType.YELLOW ->{
                    dialogView.alertDialogTitle.setTextColor(R.color.yellow)
                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_yellow)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.black)

                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_yellow)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.black)
                }
                DailogBoxColorType.CYAN ->{
                    dialogView.alertDialogTitle.setTextColor(R.color.cyan)
                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_cyan)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.black)

                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_cyan)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.black)
                }
                DailogBoxColorType.PURPLE ->{
                    dialogView.alertDialogTitle.setTextColor(R.color.purple)
                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_purple)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.white)

                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_purple)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.white)
                }
                DailogBoxColorType.GREEN ->{
                    dialogView.alertDialogTitle.setTextColor(R.color.green)
                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_green)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.black)

                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_green)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.black)
                }
                DailogBoxColorType.RED ->{
                    dialogView.alertDialogTitle.setTextColor(R.color.red)
                    dialogView.alertDialogTitle.setTextColor(R.color.green)
                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_red)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.white)

                    dialogView.alertDialogNegativeButton.setBackgroundResource(R.drawable.dialog_box_button_background_red)
                    dialogView.alertDialogNegativeButton.setTextColor(R.color.white)
                }
                else -> {}
            }
            builder.setView(dialogView)
            builder.setCancelable(isCancelable)
            dialogView.setBackgroundColor(ContextCompat.getColor(activity.applicationContext,R.color.white))
            val alertDialog = builder.create()
            dialogView.alertDialogTitle.text = dialogBox.title
            dialogView.alertDialogMessage.text = dialogBox.message

            dialogBox.buttonList?.getOrNull(0)?.let { buttonModel->
                dialogView.alertDialogNegativeButton.text = buttonModel.text.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.US
                    ) else it.toString()
                }
                dialogView.alertDialogNegativeButton.setOnClickListener {
                    buttonAction(buttonModel,activity)
                    alertDialog.dismiss()
                }
            } ?: run {
                dialogView.alertDialogNegativeButton.visibility = View.GONE
            }

            dialogBox.buttonList?.getOrNull(1)?.let { buttonModel ->
                dialogView.alertDialogPositiveButton.text = buttonModel.text.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.US
                    ) else it.toString()
                }
                dialogView.alertDialogPositiveButton.setOnClickListener {
                    buttonAction(buttonModel,activity)
                    alertDialog.dismiss()
                }
            } ?: run {
                dialogView.alertDialogPositiveButton.visibility = View.GONE
            }

            alertDialog.window?.setBackgroundDrawable(AppCompatResources.getDrawable(activity,
                androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha))

            alertDialog.show()
        }
    }

    private fun buttonAction(buttonModel: ButtonModel?, activity: Activity?){
        when(buttonModel?.actionType){
            ButtonActionType.CUSTOM -> baseDialogBoxCustomActionListener?.baseDialogBoxCustomAction()
            ButtonActionType.GO_BACK -> tryCatch({activity?.onBackPressed()})
            ButtonActionType.DISMISS -> Unit
            ButtonActionType.REQUEST ->{
                if (buttonModel.onPressed != null){
                    buttonModel.onPressed?.invoke()
                }else{
                    sendGenericRequest(activity,buttonModel.requestModel)
                }
            }
            else -> {buttonModel?.onPressed?.invoke()}
        }
    }

    fun sendGenericRequest(activity: Activity?, requestModel: RequestModel?, onSuccess:()->Unit = {}){
        if (activity is HomeRentActivity<*,*>){
            var body:Any? = null
            if (!requestModel?.body.isNullOrEmpty()){
                body = hashMapOf<String,Any>().apply {
                    requestModel?.body?.forEach{
                        if (it.key is String && it.value != null){
                            put(it.key,it.value)
                        }
                    }
                }
            }else if(requestModel?.bodyAsJsonString?.isEmpty() == false){
                body = JsonParser.parseString(requestModel.bodyAsJsonString).asJsonObject
            }

            val requestFunction = when(requestModel?.method){
                RequestMethodType.POST -> service.genericPostRequest(requestModel.url,body)
                RequestMethodType.GET -> service.genericGetRequest(requestModel.url)
                RequestMethodType.DELETE -> service.genericDeleteRequest(requestModel.url)
                RequestMethodType.PUT -> service.genericPutRequest(requestModel.url,body)
                else -> {null}
            }
            tryCatch(
                tryBlock ={
                    (requestFunction as? BaseResult<*>)?.run {
                        CoroutineScope(Dispatchers.IO).launch {
                            onSuccess.invoke()
                        }
                    }
                }
            )
        }
    }

}