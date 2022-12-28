package ymsli.com.cpo.ui.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
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
class BookAppointmentViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _selectDateResponse = MutableLiveData<Event<Resource<SelectDateResponse>>>()
    val selectDateResponse: LiveData<Event<Resource<SelectDateResponse>>> = _selectDateResponse

    private val _selectTimeResponse = MutableLiveData<Event<Resource<SelectTimeResponse>>>()
    val selectTimeResponse: LiveData<Event<Resource<SelectTimeResponse>>> = _selectTimeResponse

    private val _submitAppointmentResponse = MutableLiveData<Event<Resource<SubmitAppointmentResponse>>>()
    val submitAppointmentResponse: LiveData<Event<Resource<SubmitAppointmentResponse>>> = _submitAppointmentResponse
    

    fun dateRequest() = viewModelScope.launch {
        getDateRequest()
    }

    fun timeRequest() = viewModelScope.launch {
        getTimeRequest()
    }


    @SuppressLint("LongLogTag")
    fun appointmentRequest(body: RequestBodies.SubmitAppointRequestBody) = viewModelScope.launch {
        Log.e("book appointment model: ",body.toString())
        submitAppointmentRequest(body)
    }


    /**
     * get Date API request
    * */

    private suspend fun getDateRequest() {
        _selectDateResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getDatesApi()
                handleSelectDatesResponse( response)
            } else {
                _selectDateResponse.postValue(
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
                    _selectDateResponse.postValue(
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
                    _selectDateResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleSelectDatesResponse(response: Response<SelectDateResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _selectDateResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _selectDateResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _selectDateResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _selectDateResponse.postValue(Event(Resource.Error(response.message())))
    }


    /**
     * get Time API request
     * */

    private suspend fun getTimeRequest() {
        _selectTimeResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getTimeApi()
                handleSelectTimeResponse(response)
            } else {
                _selectTimeResponse.postValue(
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
                    _selectTimeResponse.postValue(
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
                    _selectTimeResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleSelectTimeResponse(response: Response<SelectTimeResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _selectTimeResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _selectTimeResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _selectTimeResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _selectTimeResponse.postValue(Event(Resource.Error(response.message())))
    }




    /**
     * Submit appointment requset
     * */

    private suspend fun submitAppointmentRequest(body: RequestBodies.SubmitAppointRequestBody) {
        _submitAppointmentResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.submitAppointmentApi(body)
                Log.e("Submit Appoint:",response.toString())
                handleAppointmentResponse(response)
            } else {
                _submitAppointmentResponse.postValue(
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
                    _submitAppointmentResponse.postValue(
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
                    _submitAppointmentResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleAppointmentResponse(response: Response<SubmitAppointmentResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _submitAppointmentResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _submitAppointmentResponse.postValue(Event(Resource.Error(message.toString())))
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
                    val errorMessage = jsonObject.getString("error")
                    return _submitAppointmentResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _submitAppointmentResponse.postValue(Event(Resource.Error(response.message())))
    }


}