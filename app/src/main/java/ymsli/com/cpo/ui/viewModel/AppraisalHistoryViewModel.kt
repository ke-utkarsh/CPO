package ymsli.com.cpo.ui.viewModel

import android.app.Application
import android.app.DownloadManager.Request
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.AppraisalHistoryResponse
import ymsli.com.cpo.data.model.GetMaintenanceItems
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.Event
import ymsli.com.cpo.utils.MyApplication
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.hasInternetConnection
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AppraisalHistoryViewModel@Inject constructor(app: Application, private val appRepository: AppRepository): AndroidViewModel(app){
    private val _appraisalHistory = MutableLiveData<Event<Resource<AppraisalHistoryResponse>>>()
    val appraisalHistory: LiveData<Event<Resource<AppraisalHistoryResponse>>> = _appraisalHistory

    fun getAppraisalHistory(body: RequestBodies.VehicleInformationBody)= viewModelScope.launch {
        getAppraisalData(body)
    }

    private suspend fun getAppraisalData(body: RequestBodies.VehicleInformationBody) {
        _appraisalHistory.value=Event(Resource.Loading())
        try{
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.AppraisalHistoryRequest(body)
                handleAppraisalHistoryResponse(response)
            } else {
                _appraisalHistory.postValue(
                    Event(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }

        }catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _appraisalHistory.postValue(
                        Event(
                            Resource.Error(
                                getApplication<MyApplication>().getString(
                                    R.string.backend_is_under_mainteanace
                                )
                            )
                        )
                    )
                }
                else -> {
                    _appraisalHistory.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleAppraisalHistoryResponse(response: Response<AppraisalHistoryResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _appraisalHistory.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _appraisalHistory.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _appraisalHistory.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return _appraisalHistory.postValue(Event(Resource.Error(response.errorBody().toString())))
    }
}