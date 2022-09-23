package com.dag.homerent.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

abstract class HomeRentActivity<VM : HomeRentViewModel, VB : ViewDataBinding> :
    AppCompatActivity(),
    CanDropSession {

    lateinit var binding: VB
    lateinit var viewModel: VM

    abstract fun getHomeViewModel(): VM

    abstract fun getLayout():Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayout()?.let {
            binding = DataBindingUtil.inflate(layoutInflater,it, null, false)
        }
        viewModel = getHomeViewModel()
        if(!viewModel.viewState.hasActiveObservers()){
            viewModel.viewState.observe(this, Observer {
                handleState(it)
            })
        }
    }

    open fun handleState(viewState: HomeRentViewState){}

    override fun dropSession() {
        // TODO: Navigate Splash
    }

    fun hideProgress(){

    }


    }