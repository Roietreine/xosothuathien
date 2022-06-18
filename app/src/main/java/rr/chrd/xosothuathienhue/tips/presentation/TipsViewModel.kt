package rr.chrd.xosothuathienhue.tips.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rr.chrd.xosothuathienhue.tips.model.TipsModel
import rr.chrd.xosothuathienhue.utilities.StaticData.Companion.tipsDesc
import rr.chrd.xosothuathienhue.utilities.StaticData.Companion.tipsTitle

class TipsViewModel : ViewModel() {

    private var tipsData = ArrayList<TipsModel>()

    private var tipsDetails = MutableLiveData<List<TipsModel>>()
    val tpsdtls : LiveData<List<TipsModel>>
    get() = tipsDetails
    private var tipsError = CoroutineExceptionHandler { _, _ ->
        tipsDetails.postValue(null)
    }

    fun tipsFun() : MutableLiveData<List<TipsModel>>{
        viewModelScope.launch(tipsError + Dispatchers.IO){
            for(n in tipsTitle.indices){
                tipsData.add(TipsModel(tipsTitle[n],tipsDesc[n]))
            }
            tipsDetails.postValue(tipsData)
        }
        return tipsDetails
    }
}