package com.dag.homerent.ui.home.add.ui

import com.dag.homerent.network.commonresponse.TextField
import com.dag.homerent.ui.home.add.data.AddHomeModel
import com.dag.homerent.ui.home.add.data.AddHomeModelKeys
import com.dag.homerent.ui.home.add.data.CreateFacilityRequests
import javax.inject.Inject

class AddHomeWatcher @Inject constructor() {

    companion object {
        var addHomeRequestModel: AddHomeModel = AddHomeModel()
    }

    fun observeModelText(textField: TextField, text: String) {
        addHomeRequestModel.apply {
            if (textField.key == AddHomeModelKeys.WalletNumber.key) {
                landlordAccount.walletNumber = text
            } else if (textField.key == AddHomeModelKeys.Price.key) {
                price = text.toDouble()
            } else if (textField.key == AddHomeModelKeys.Message.key) {
                message = text
            } else if (textField.key == AddHomeModelKeys.BankName.key) {
                landlordAccount.bankName = text
            } else if (textField.key == AddHomeModelKeys.AccountNumber.key) {
                landlordAccount.accountNumber = text
            } else if (textField.key == AddHomeModelKeys.Bedroom.key) {
                addHomeRequestModel.bedroomCount = text.toInt()
            }
        }
    }

    fun observeModelBoolean(textField: TextField, flag: Boolean) {
        addHomeRequestModel.apply {
            if (textField.key == AddHomeModelKeys.ElectricityBill.key) {
                electricityBillIncluded = flag
            } else if (textField.key == AddHomeModelKeys.AutoPay.key) {
                autoPay = flag
            } else if (textField.key == AddHomeModelKeys.WaterBill.key) {
                waterBillIncluded = false
            }
        }
    }

    fun observeModelList(key: String, listObject: String) {
        addHomeRequestModel.apply {
            if (key == AddHomeModelKeys.Facilities.key) {
                createFacilityRequests.add(CreateFacilityRequests(listObject))
            } else if (key == AddHomeModelKeys.Properties.key) {
                propertyType = listObject
            }
        }
    }
}