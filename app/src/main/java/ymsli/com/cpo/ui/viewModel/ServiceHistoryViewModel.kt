package ymsli.com.cpo.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.MyApplication
import ymsli.com.cpo.utils.Utils
import ymsli.com.cpo.utils.hasInternetConnection
import java.io.IOException
import javax.inject.Inject
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Event

@HiltViewModel
class ServiceHistoryViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _scheduledAppointResponse = MutableLiveData<Event<Resource<GetAppointmentResponse>>>()
    val scheduledAppointResponse: LiveData<Event<Resource<GetAppointmentResponse>>> = _scheduledAppointResponse

    private val _serviceHistoryResponse = MutableLiveData<Event<Resource<GetMaintenanceListResponse>>>()
    val serviceHistoryResponse: LiveData<Event<Resource<GetMaintenanceListResponse>>> = _serviceHistoryResponse
    

    fun scheduledAppointmentRequest(body:RequestBodies.GetAppointmentRequestBody) = viewModelScope.launch {
        getScheduledAppointmentRequest(body)
    }

    fun serviceHistoryResquest(body: RequestBodies.GetAppointmentRequestBody)=viewModelScope.launch {
        getServiceHistory(body)
    }

    private suspend fun getServiceHistory(body: RequestBodies.GetAppointmentRequestBody) {
        _serviceHistoryResponse.value=Event(Resource.Loading())
        try{
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.maintenanceListApi(body)
                handleServiceHistoryResponse(response)
            } else {
                _serviceHistoryResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }
        }catch (t: Throwable){
            when (t) {
                is IOException -> {
                    _serviceHistoryResponse.postValue(
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
                    _serviceHistoryResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleServiceHistoryResponse(response: Response<GetMaintenanceListResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _serviceHistoryResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _serviceHistoryResponse.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }
        else
        {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _serviceHistoryResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _serviceHistoryResponse.postValue(Event(Resource.Error(response.message())))
    }


    /**
     * Get Scheduled Appointment Request API request
    * */

    private suspend fun getScheduledAppointmentRequest(body:RequestBodies.GetAppointmentRequestBody) {
        _scheduledAppointResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getAppointmentApi(body)
                handleActiveWarrantiesResponse(response)
            } else {
                _scheduledAppointResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _scheduledAppointResponse.postValue(
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
                    _scheduledAppointResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleActiveWarrantiesResponse(response: Response<GetAppointmentResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _scheduledAppointResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _scheduledAppointResponse.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }
        else
        {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _scheduledAppointResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _scheduledAppointResponse.postValue(Event(Resource.Error(response.message())))
    }
}

