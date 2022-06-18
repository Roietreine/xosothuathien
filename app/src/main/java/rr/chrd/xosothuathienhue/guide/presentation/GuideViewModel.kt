package rr.chrd.xosothuathienhue.guide.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rr.chrd.xosothuathienhue.guide.model.GuideModel
import rr.chrd.xosothuathienhue.utilities.StaticData
import rr.chrd.xosothuathienhue.utilities.StaticData.Companion.guideDesc
import rr.chrd.xosothuathienhue.utilities.StaticData.Companion.guideTitle

class GuideViewModel : ViewModel() {

    private var guideData = ArrayList<GuideModel>()

    private var guideDetails = MutableLiveData<List<GuideModel>>()
    val gddtls : LiveData<List<GuideModel>>get() = guideDetails
    val guideError = CoroutineExceptionHandler { _, _ ->
        guideDetails.postValue(null)
    }

    fun guideFun() : MutableLiveData<List<GuideModel>>{
        viewModelScope.launch(guideError + Dispatchers.IO){
            for (n in guideTitle.indices){
                guideData.add(GuideModel(guideTitle[n],guideDesc[n]))
            }
                guideDetails.postValue(guideData)
        }
        return guideDetails
    }
}