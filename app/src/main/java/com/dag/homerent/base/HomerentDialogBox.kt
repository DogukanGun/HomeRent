package com.dag.homerent.base

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.dag.homerent.R
import com.dag.homerent.base.ext.makeGone
import com.dag.homerent.base.ext.makeVisible
import com.dag.homerent.base.ext.setBackground
import kotlinx.android.synthetic.main.alert_dialog_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomerentDialogBox {

    companion object {

        private val activeDialogs = mutableListOf<ClDialog>()

        fun showAlertDialog(
            activity: Activity,
            title: String,
            message: String,
            positiveButtonTitle: String?,
            negativeButtonTitle: String?,
            buttonClickListener: ButtonClickListener = object : ButtonClickListener() {},
            isCancelable: Boolean = true,
            isIconVisible: Boolean = true,
            dialogPrimaryColor: DialogPrimaryColor = DialogPrimaryColor.Red,
            create: Boolean = false,
            dialogKey: String?,
            iconNextToText: IconStatus? = null,
            backToPreviousIcon: Boolean? = null
        ): AlertDialog? {
            if (activity.isFinishing) {
                return null
            }
            val builder = AlertDialog.Builder(activity)
            val dialogView = activity.layoutInflater.inflate(R.layout.alert_dialog_layout, null)
            //bu kısımda dialog box içinden renk alabilirsin.
            //sonra bunları when ile kontrol et
            //bütün renk vb atamalar dialogView içinde olmalı
            builder.setView(dialogView)
            builder.setCancelable(isCancelable)
            dialogView.setBackgroundColor(
                ContextCompat.getColor(
                    activity.applicationContext,
                    R.color.purple_200
                )
            )
            val alertDialog = builder.create()
            val clDialog = ClDialog(alertDialog, dialogKey)
            activeDialogs.add(clDialog)
            dialogView.alertDialogTitle.text = "DialogBoxUtil from Static"
            dialogView.alertDialogTitle.alertDialogMessage.text =
                "DialogBoxUtil message from Static"
            if (iconNextToText?.primaryIcon != 0) {
                dialogView.alertDialogErrorIcon.makeVisible()
                dialogView.alertDialogErrorIcon.setBackground(iconNextToText?.primaryIcon)
            } else {
                dialogView.alertDialogErrorIcon.makeGone()
            }
            positiveButtonTitle?.let { title ->
                dialogView.alertDialogPositiveButton.text = title
                dialogView.alertDialogPositiveButton.setOnClickListener {
                    buttonClickListener.onPositiveButtonClicked()
                    alertDialog.dismiss()
                    activeDialogs.remove(clDialog)
                    clearDuplicateDialogs(dialogKey)
                }
            } ?: run {
                dialogView.alertDialogPositiveButton.makeGone()
            }
            negativeButtonTitle?.let { title ->
                dialogView.alertDialogNegativeButton.text = title
                dialogView.alertDialogNegativeButton.setOnClickListener {
                    buttonClickListener.onNegativeButtonClicked()
                    alertDialog.dismiss()
                    activeDialogs.remove(clDialog)
                    clearDuplicateDialogs(dialogKey)
                }
            } ?: run {
                dialogView.alertDialogNegativeButton.makeGone()
            }
            dialogView.alertDialogErrorIcon.setOnClickListener {
                buttonClickListener.onIconNextToTextClicked()
                dialogView.alertDialogErrorIcon.setBackground(iconNextToText?.lastIcon)
                if (backToPreviousIcon == true) {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(2000L)
                        dialogView.alertDialogErrorIcon.setBackground(iconNextToText?.primaryIcon)
                    }
                }
            }
            dialogView.setPadding(0, 0, 0, 0)
            //backend den buttonList gelecek. 0 ve 1 indekslerini teker teker butona atayacaksın
            //gelen butonlara aşağıda yer alan buton özelliğini taşı
            return if (create) {
                alertDialog
            } else {
                alertDialog.show()
                return null
            }
        }

        fun clearDuplicateDialogs(dialogKey: String? = null) {
            dialogKey?.let { dialogKey ->
                activeDialogs.filter {
                    it.dialogKey?.equals(dialogKey) == true
                }.forEach {
                    it.dialog.dismiss()
                    activeDialogs.remove(it)
                }
            }
        }

        fun clearDialogs() {
            activeDialogs.clear()
        }

        fun dismissDialogs() {
            activeDialogs.map {
                it.dialog.dismiss()
            }
        }

        fun hasActiveDialogs() = activeDialogs.size > 0
    }

    data class ClDialog(
        val dialog: AlertDialog,
        val dialogKey: String?
    )

    data class DialogButtonData(
        val positiveButtonTitle: String?,
        val negativeButtonTitle: String?,
        val buttonClickListener: ButtonClickListener
    )

    enum class DialogPrimaryColor {
        Red, Cyan, Purple, Yellow, Green, Turquoise, Orange, RedInfo
    }

    data class IconStatus(
        @DrawableRes val primaryIcon: Int?,
        @DrawableRes val lastIcon: Int?
    )

    abstract class ButtonClickListener {
        open fun onPositiveButtonClicked() {
            clearDuplicateDialogs()
        }

        open fun onNegativeButtonClicked() {
            clearDuplicateDialogs()
        }

        open fun onIconNextToTextClicked(@DrawableRes icon: Int? = null) {
            clearDuplicateDialogs()
        }
    }
}