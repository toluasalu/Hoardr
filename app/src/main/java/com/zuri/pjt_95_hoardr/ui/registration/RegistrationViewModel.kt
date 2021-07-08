package com.zuri.pjt_95_hoardr.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName

    private val _emailAddress = MutableLiveData<String>()
    val emailAddress: LiveData<String> = _emailAddress

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    private val _country = MutableLiveData<String>()
    val country: LiveData<String> = _country

    private val _state = MutableLiveData<String>()
    val state: LiveData<String> = _state

    private val _lga = MutableLiveData<String>()
    val lga: LiveData<String> = _lga

    private  val _uid = MutableLiveData<String>()
    val uid: LiveData<String> = _uid


    fun setUID(userId: String){
        _uid.value = userId
    }


    fun setFirstName(name: String) {
        _firstName.value = name
    }

    fun setLastName(surname: String){
        _lastName.value = surname
    }

    fun setEmailAddress(email: String) {
        _emailAddress.value = email
    }

    fun setPhoneNumber(phoneNo: String){
        _phoneNumber.value = phoneNo
    }

    fun setCountry(countryName:String){
        _country.value = countryName
    }

    fun setState(stateName:String){
        _state.value = stateName
    }

    fun setLga(localGovernmentArea:String){
        _lga.value = localGovernmentArea
    }


}