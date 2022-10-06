package com.dag.homerent.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.dag.homerent.BR
import com.dag.homerent.base.BaseDialogBoxUtil
import com.dag.homerent.base.CanDropSession
import com.dag.homerent.dailogbox.ModelDialog
import com.dag.homerent.dailogbox.ModelDialogHandler
import javax.inject.Inject

abstract class HomeRentActivity<VM : HomeRentViewModel, VB : ViewDataBinding> :
    AppCompatActivity(),
    CanDropSession {

    lateinit var binding: VB
    lateinit var viewModel: VM

    abstract fun getHomeViewModel(): VM
    abstract fun getLayout(): Int?

    @Inject
    lateinit var homerentProcessDialogManager: HomerentProgressDialogManager

    @Inject
    lateinit var dialogBoxUtil: BaseDialogBoxUtil

    @Inject
    lateinit var modelDialogHandler: ModelDialogHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getHomeViewModel()
        getLayout()?.let {
            binding = DataBindingUtil.setContentView(this, it)
            binding.setVariable(BR.viewModel, viewModel)
        }
        if (!viewModel.viewState.hasActiveObservers()) {
            viewModel.viewState.observe(this, Observer {
                handleState(it)
            })
        }
        if (!viewModel.isLoading().hasActiveObservers()) {
            viewModel.isLoading().observe(this, Observer {
                handleLoadingState(it)
            })
        }
    }

    open fun handleState(viewState: HomeRentViewState) {
        when (viewState) {
            is ModelDialog -> modelDialogHandler.showDialog(this, viewState)
        }
    }

    fun handleLoadingState(isLoading: Boolean) {
        if (isLoading) {
            homerentProcessDialogManager.showLoadingDialog(this)
        } else {
            homerentProcessDialogManager.stopDialog()
        }
    }

    override fun dropSession() {
        // TODO: Navigate Splash
    }

    fun hideProgress() {

    }


}