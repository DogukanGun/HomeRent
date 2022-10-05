package com.dag.homerent.base.ui

import android.content.Context
import com.dag.homerent.component.processdialog.AnimationType
import com.dag.homerent.component.processdialog.HomerentProcessDialog
import javax.inject.Inject

class HomerentProgressDialogManager @Inject constructor() {

    companion object {
        private var progressDialog: HomerentProcessDialog? = null
    }

    fun showErrorDialog(context: Context) {
        progressDialog = HomerentProcessDialog(context, AnimationType.ERROR)
        progressDialog?.show()
        progressDialog?.listener = object : HomerentProcessDialog.HomerentAnimationListener {
            override fun finishAnimation() {
                progressDialog?.dismiss()
            }
        }
    }

    fun showUploadDialog(context: Context) {
        progressDialog = HomerentProcessDialog(context, AnimationType.UPLOAD)
        progressDialog?.show()
        progressDialog?.listener = object : HomerentProcessDialog.HomerentAnimationListener {
            override fun finishAnimation() {
                progressDialog?.dismiss()
            }
        }
    }

    fun showLoadingDialog(context: Context) {
        progressDialog = HomerentProcessDialog(context)
        progressDialog?.show()
    }

    fun stopDialog() {
        progressDialog?.dismiss()
    }
}