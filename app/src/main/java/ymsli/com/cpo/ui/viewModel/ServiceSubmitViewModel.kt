package ymsli.com.cpo.ui.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.GetBeforeImagesModel
import ymsli.com.cpo.data.model.GetMaintenanceItems
import ymsli.com.cpo.data.model.ServiceItemResponse
import ymsli.com.cpo.data.model.SubmitCompleteResponseModel
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.*
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ServiceSubmitViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app)  {

    private val _serviceSubmitCompleteResponse = MutableLiveData<Event<Resource<SubmitCompleteResponseModel>>>()
    val serviceSubmitCompleteResponse: LiveData<Event<Resource<SubmitCompleteResponseModel>>> = _serviceSubmitCompleteResponse

    private val _serviceSubmitResponse = MutableLiveData<Event<Resource<GetBeforeImagesModel>>>()
    val serviceSubmitResponse: LiveData<Event<Resource<GetBeforeImagesModel>>> = _serviceSubmitResponse

    fun getServiceRequestDetails(body: RequestBodies.GetAppointmentDetail){
        viewModelScope.launch {
            getServiceRequestDetail(body)
        }

    }

    fun ServiceSubmitCompleteRequest(body: RequestBodies.SubmitCompleteRequest){
        viewModelScope.launch {
            submitCompleteRequest(body)
        }

    }

    private suspend fun submitCompleteRequest(submitCompleteRequest: RequestBodies.SubmitCompleteRequest) {
        _serviceSubmitCompleteResponse.value = Event(Resource.Loading())
        try {

            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.ServiceSubmitCompleteRequest(submitCompleteRequest)
                Log.e("ServiceResponse ",response.toString())
                handleServiceSubmitCompleteResponse(response)
            } else {
                _serviceSubmitCompleteResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<MyApplication>().getString(R.string.no_internet_connection)
                        )
                    )
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _serviceSubmitCompleteResponse.postValue(
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
                    _serviceSubmitCompleteResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleServiceSubmitCompleteResponse(response: Response<SubmitCompleteResponseModel>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->            val status = resultResponse.Status
                if (status) {
                    _serviceSubmitCompleteResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _serviceSubmitCompleteResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _serviceSubmitCompleteResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _serviceSubmitCompleteResponse.postValue(Event(Resource.Error(response.message())))
    }

    /*
    get Appointment Details API Call
     */
    private suspend fun getServiceRequestDetail(body: RequestBodies.GetAppointmentDetail) {
        _serviceSubmitResponse.value = Event(Resource.Loading())
        try {
            //Utils.logThis("Get Appointment Id body :", appointmentId)
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getServiceSubmitDetails(body)
                Log.e("ServiceResponse ",response.toString())
                handleServiceSubmitResponse(response)
            } else {
                _serviceSubmitResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<MyApplication>().getString(R.string.no_internet_connection)
                        )
                    )
                )
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    _serviceSubmitResponse.postValue(
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
                    _serviceSubmitResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }


    private fun handleServiceSubmitResponse(response: Response<GetBeforeImagesModel>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _serviceSubmitResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _serviceSubmitResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _serviceSubmitResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _serviceSubmitResponse.postValue(Event(Resource.Error(response.message())))
    }
}