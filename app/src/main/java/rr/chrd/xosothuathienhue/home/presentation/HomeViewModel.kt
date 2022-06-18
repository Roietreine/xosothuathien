package rr.chrd.xosothuathienhue.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rr.chrd.xosothuathienhue.home.model.HomeModel
import rr.chrd.xosothuathienhue.utilities.StaticData
import rr.chrd.xosothuathienhue.utilities.StaticData.Companion.homeDesc
import rr.chrd.xosothuathienhue.utilities.StaticData.Companion.homeTitle


class HomeViewModel : ViewModel() {

    private var homeData = ArrayList<HomeModel>()

    private var homeDetails = MutableLiveData<List<HomeModel>>()
    val hmdtls : LiveData<List<HomeModel>>
    get() = homeDetails
    private var homeError = CoroutineExceptionHandler { _, _ ->
        homeDetails.postValue(null)
    }
    fun homeFun(): MutableLiveData<List<HomeModel>> {
        viewModelScope.launch(homeError + Dispatchers.IO) {
            for(i in homeDesc.indices){
                homeData.add(HomeModel(homeTitle[i],homeDesc[i]))
            }
            homeDetails.postValue(homeData)
        }
        return homeDetails
    }
}