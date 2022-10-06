package com.dag.homerent.ui.home.add.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dag.homerent.R
import com.dag.homerent.base.ext.toBase64
import com.dag.homerent.base.ext.tryCatch
import com.dag.homerent.base.location.ILocationHelper
import com.dag.homerent.base.location.LocationHelperBuilder
import com.dag.homerent.base.location.LocationHelperGms
import com.dag.homerent.base.ui.HomeRentActivity
import com.dag.homerent.base.ui.HomeRentViewState
import com.dag.homerent.component.homerentinput.HomeRentInput
import com.dag.homerent.component.homerentlocationpicker.HomerentLocationPicker
import com.dag.homerent.component.homerentswitch.HomeRentSwitch
import com.dag.homerent.component.photopicker.HomerentPhotoPicker
import com.dag.homerent.component.verticalrow.VerticalRow
import com.dag.homerent.component.verticalrow.VerticalRowItem
import com.dag.homerent.dailogbox.createGenericDialog
import com.dag.homerent.data.common.ContentPages
import com.dag.homerent.data.common.TextFieldType
import com.dag.homerent.databinding.ActivityAddhomeBinding
import com.dag.homerent.network.commonresponse.ActivityBodyResponse
import com.dag.homerent.ui.home.add.data.HomeCoordinate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.component_homerent_photo_picker.view.*
import javax.inject.Inject


@AndroidEntryPoint
class AddHomeActivity : HomeRentActivity<AddHomeVM, ActivityAddhomeBinding>(),
    LocationHelperGms.LocationHelperCallback {
    override fun getHomeViewModel(): AddHomeVM = addHomeVM

    override fun getLayout(): Int? = R.layout.activity_addhome

    @Inject
    lateinit var addHomeVM: AddHomeVM

    @Inject
    lateinit var addHomeWatcher: AddHomeWatcher

    lateinit var locationHelper: ILocationHelper
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var openDocumentActivityResultLauncher: ActivityResultLauncher<Array<String>>
    private var _imageUri = MutableLiveData<Uri>()
    private val imagePath = "image/*"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.backButtonBTN.setOnClickListener(backButtonOnClickListener)
        binding.nextButtonBTN.setOnClickListener(nextButtonClickListener)
        registerActivityResultLauncher()
        requestPermissionLauncher()
        locationHelper = LocationHelperBuilder(this).getLocationHelper()
    }

    private fun registerActivityResultLauncher() {
        openDocumentActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.OpenDocument()) {
                _imageUri.postValue(it)
            }
    }

    private val backButtonOnClickListener = View.OnClickListener {
        if (viewModel.step == 1) {
            this.finish()
        } else {
            ContentPages.values().find { item ->
                item.id == viewModel.step - 1
            }?.let {
                viewModel.decrementStep()
                binding.textFieldWrapperLL.removeAllViews()
                viewModel.getActivityBody(it)
            }
        }
    }

    private val nextButtonClickListener = View.OnClickListener {
        val contentPage = ContentPages.values().find { item ->
            item.id == viewModel.step + 1
        }?.let {
            viewModel.incrementStep()
            binding.textFieldWrapperLL.removeAllViews()
            viewModel.getActivityBody(it)
        }
        if (contentPage == null)
            addHomeVM.createNewHome()
    }

    override fun handleState(viewState: HomeRentViewState) {
        super.handleState(viewState)
        when (viewState) {
            is AddHomeVS.PageContent -> {
                handleUI(viewState.pageResponse)
            }
            AddHomeVS.HomeCreated -> {
                Toast.makeText(this, "Home is created", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isAccessFineLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun isAccessCoarseLocationPermissionGranted() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun isPermissionGranted() =
        isAccessCoarseLocationPermissionGranted() && isAccessFineLocationPermissionGranted()

    private fun requestPermissionLauncher() {
        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                val locationFinePermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION]
                    ?: false
                val locationCoarsePermission =
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION]
                        ?: false
                if (locationCoarsePermission && locationFinePermission) {
                    locationHelper.requestLocationUpdatesBySettingsCheck()
                } else {
                    handleState(
                        createGenericDialog(
                            titleRes = R.string.location_error_dialog_title,
                            messageRes = R.string.location_error_dialog_text,
                            isCancelable = false
                        )
                    )
                }
            }
    }

    private fun handleUI(pageResponse: ActivityBodyResponse) {
        binding.titleTV.text = pageResponse.title
        binding.pageTitleTV.text = pageResponse.pageName
        for (textField in pageResponse.textFields) {
            when (textField.type) {
                TextFieldType.Switch -> {
                    val switchComponent = HomeRentSwitch(binding.textFieldWrapperLL.context)
                    switchComponent.refresh(textField)
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    switchComponent.layoutParams = layoutParams
                    if (!switchComponent.switchState.hasActiveObservers()) {
                        switchComponent.switchState.observe(this, Observer {
                            addHomeWatcher.observeModelBoolean(textField, it)
                        })
                    }
                    binding.textFieldWrapperLL.addView(switchComponent)
                }
                TextFieldType.Text, TextFieldType.Numeric -> {
                    val textFieldComponent = HomeRentInput(binding.textFieldWrapperLL.context)
                    textFieldComponent.refreshLayout(textField)
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (!textFieldComponent.text.hasActiveObservers()) {
                        textFieldComponent.text.observe(this, Observer {
                            if (!it.isNullOrEmpty())
                                addHomeWatcher.observeModelText(textField, it)
                        })
                    }
                    textFieldComponent.layoutParams = layoutParams
                    binding.textFieldWrapperLL.addView(textFieldComponent)
                }
                TextFieldType.List -> {
                    val verticalRow = VerticalRow(this)
                    verticalRow.setRecyclerViewAdapter(textField) { item, key ->
                        handleTextViewListListener(item, key)
                    }
                    binding.textFieldWrapperLL.addView(verticalRow)
                }
                TextFieldType.Image -> {
                    hideBackButton()
                    val homerentPhotoPicker = HomerentPhotoPicker(this)
                    homerentPhotoPicker.layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    )
                    homerentPhotoPicker.homerentPhotoPickerListener = handleImageClickListener
                    if (!_imageUri.hasActiveObservers()) {
                        _imageUri.observe(this, Observer {
                            tryCatch(
                                tryBlock = {
                                    val options: RequestOptions = RequestOptions()
                                        .placeholder(R.drawable.example_image)
                                        .error(R.drawable.example_image)
                                    Glide
                                        .with(this)
                                        .asBitmap()
                                        .load(it)
                                        .apply(options)
                                        .into(object : CustomTarget<Bitmap>() {
                                            override fun onResourceReady(
                                                resource: Bitmap,
                                                transition: Transition<in Bitmap>?
                                            ) {
                                                homerentPhotoPicker.imageView.setImageURI(it)
                                                addHomeWatcher.observeModelText(
                                                    textField,
                                                    resource.toBase64()
                                                )
                                            }

                                            override fun onLoadCleared(placeholder: Drawable?) {}
                                        })
                                }
                            )
                        })
                    }
                    binding.textFieldWrapperLL.addView(homerentPhotoPicker)
                }
                TextFieldType.Location -> {
                    val homerentLocationPicker = HomerentLocationPicker(this)
                    homerentLocationPicker.layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    )
                    homerentLocationPicker.buttonClickListener = {
                        if (it) {
                            if (isPermissionGranted())
                                locationHelper.requestLocationUpdatesBySettingsCheck()
                            else
                                askPermission()
                        } else {
                            addHomeWatcher.observeLocation(HomeCoordinate())
                        }
                    }
                    binding.textFieldWrapperLL.addView(homerentLocationPicker)
                }
            }
        }
    }

    private fun hideBackButton() {
        binding.backButtonBTN.visibility = View.INVISIBLE
    }

    private fun askPermission() {
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )
    }

    private var handleImageClickListener = object : HomerentPhotoPicker.HomrentPhotoPickerListener {
        override fun imageClicked() {
            openDocumentActivityResultLauncher.launch(arrayOf(imagePath))
        }
    }

    private fun handleTextViewListListener(item: VerticalRowItem, key: String) {
        addHomeWatcher.observeModelList(
            listObject = item.text,
            key = key
        )
    }

    override fun onLocationResult(location: Location) {
        val homeCoordinate = HomeCoordinate(location.longitude, location.latitude)
        addHomeWatcher.observeLocation(homeCoordinate)
    }

    override fun onLocationFailed() {
    }

    override fun onLocationRequest() {
    }

    override fun onResolutionRequired(ex: Exception) {
    }
}