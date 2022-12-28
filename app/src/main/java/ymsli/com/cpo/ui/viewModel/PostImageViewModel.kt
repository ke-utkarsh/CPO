package ymsli.com.cpo.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Part
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.PostImageResponse
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.*

import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PostImageViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) :
    AndroidViewModel(app) {

    private val _afterImageResponse = MutableLiveData<Event<Resource<PostImageResponse>>>()
    val afterImageResponse: LiveData<Event<Resource<PostImageResponse>>> = _afterImageResponse

    private val _postPdiImageResponse = MutableLiveData<Event<Resource<PostImageResponse>>>()
    val postPdiImageResponse: LiveData<Event<Resource<PostImageResponse>>> = _postPdiImageResponse
    
    private val _postVehicleImageResponse = MutableLiveData<Event<Resource<PostImageResponse>>>()
    val postVehicleImageResponse: LiveData<Event<Resource<PostImageResponse>>> = _postVehicleImageResponse

    private val _concernedIssueImageResponse = MutableLiveData<Event<Resource<PostImageResponse>>>()
    val concernedIssueImageResponse: LiveData<Event<Resource<PostImageResponse>>> = _concernedIssueImageResponse
    
    private val _tripMeterImageResponse = MutableLiveData<Event<Resource<PostImageResponse>>>()
    val tripMeterImageResponse: LiveData<Event<Resource<PostImageResponse>>> = _tripMeterImageResponse

    private val _maintenanceImageResponse = MutableLiveData<Event<Resource<PostImageResponse>>>()
    val maintenanceImageResponse: LiveData<Event<Resource<PostImageResponse>>> = _maintenanceImageResponse


    fun afterImageData(@Part appointmentId: RequestBody,@Part lineId: RequestBody,@Part issueDetail: RequestBody, @Part imageBody: MultipartBody.Part) =
        viewModelScope.launch {
            afterImage(appointmentId,lineId,issueDetail, imageBody)
        }

    fun postPdiImageData(@Part vehicleMasterId: RequestBody, @Part imageBody: MultipartBody.Part) =
        viewModelScope.launch {
            postPdiImage(vehicleMasterId, imageBody)
        }


    fun postVehicleImageData(@Part vehicleMasterId: RequestBody, @Part imageBody: MultipartBody.Part) =
        viewModelScope.launch {
        postVehicleImage(vehicleMasterId, imageBody)
    }


    fun postConcernedIssueImageData(@Part appointmentId: RequestBody,@Part lineId: RequestBody,@Part issueDetail: RequestBody, @Part imageBody: MultipartBody.Part) =
        viewModelScope.launch {
            postConcernedIssueImage(appointmentId,lineId,issueDetail, imageBody)
        }
   
   
    fun postTripMeterImageData(@Part appointmentId: RequestBody,@Part lineId: RequestBody,@Part issueDetail: RequestBody, @Part imageBody: MultipartBody.Part) =
        viewModelScope.launch {
            postTripMeterImage(appointmentId,lineId,issueDetail, imageBody)
        }

    fun postMaintenanceImageData(@Part appointmentId: RequestBody,
                                 @Part maintenanceId: RequestBody,
                                 @Part maintenanceItemId: RequestBody,
                                 @Part maintenanceLineId: RequestBody,
                                 @Part description: RequestBody,
                                 @Part vehicleId: RequestBody,
                                 @Part imageBody: MultipartBody.Part) =
        viewModelScope.launch {
            postMaintenanceImage(appointmentId,maintenanceId,maintenanceItemId,maintenanceLineId,description, vehicleId, imageBody)
        }

    
    
    
    /**
     * post Pdi image
     * */
    private suspend fun postPdiImage(
        @Part vehicleMasterId: RequestBody,
        @Part imageBody: MultipartBody.Part
    ) {
        _postPdiImageResponse.value = Event(Resource.Loading())
        try {
            Utils.logThis("Post Pdi Image param", imageBody.toString())
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.postPdiImageData(vehicleMasterId, imageBody)
                handlePostImageResponse(response)
            } else {
                _postPdiImageResponse.postValue(
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
                    _postPdiImageResponse.postValue(
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
                    _postPdiImageResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handlePostImageResponse(
        response: Response<PostImageResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _postPdiImageResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _postPdiImageResponse.postValue(Event(Resource.Error(message)))
                    return
                }
                return
            }

        } else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _postPdiImageResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _postPdiImageResponse.postValue(Event(Resource.Error(response.message())))
    }


    /**
     * post Pdi image
     * */
    private suspend fun postVehicleImage(
        @Part vehicleMasterId: RequestBody,
        @Part imageBody: MultipartBody.Part
    ) {
        _postVehicleImageResponse.value = Event(Resource.Loading())
        try {
            Utils.logThis("Post Vehicle Image param", imageBody.toString())
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.postVehicleImageData(vehicleMasterId, imageBody)
                handlePostVehicleImageResponse(response)
            } else {
                _postVehicleImageResponse.postValue(
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
                    _postVehicleImageResponse.postValue(
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
                    _postVehicleImageResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handlePostVehicleImageResponse(
        response: Response<PostImageResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _postVehicleImageResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _postVehicleImageResponse.postValue(Event(Resource.Error(message)))
                    return
                }
                return
            }
        } else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _postVehicleImageResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _postVehicleImageResponse.postValue(Event(Resource.Error(response.message())))
    }


    /**
     * post Concerned Issue image
     * */
    private suspend fun postConcernedIssueImage(
        @Part appointmentId: RequestBody,
        @Part lineId: RequestBody,
        @Part issueDetail: RequestBody,
        @Part imageBody: MultipartBody.Part
    ) {
        _concernedIssueImageResponse.value = Event(Resource.Loading())
        try {
            Utils.logThis("Concerned Issue Image param", imageBody.toString())
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.postConcernedIssueImageData(appointmentId,lineId,issueDetail, imageBody)
                Log.e("Image uploaded: ",response.toString())
                handleConcernedIssueResponse(response)
            } else {
                _concernedIssueImageResponse.postValue(
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
                    _concernedIssueImageResponse.postValue(
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
                    _concernedIssueImageResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleConcernedIssueResponse(
        response: Response<PostImageResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _concernedIssueImageResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _concernedIssueImageResponse.postValue(Event(Resource.Error(message)))
                    return
                }
                return
            }
        } else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _concernedIssueImageResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _concernedIssueImageResponse.postValue(Event(Resource.Error(response.message())))
    }



    /**
     * post trip meter image
     * */
    private suspend fun postTripMeterImage(
        @Part appointmentId: RequestBody,
        @Part lineId: RequestBody,
        @Part issueDetail: RequestBody,
        @Part imageBody: MultipartBody.Part
    ) {
        _tripMeterImageResponse.value = Event(Resource.Loading())
        try {
            Utils.logThis("Trip Meter Image param", imageBody.toString())
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.postTripMeterImageData(appointmentId,lineId,issueDetail, imageBody)
                handleTripImageResponse(response)
            } else {
                _tripMeterImageResponse.postValue(
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
                    _tripMeterImageResponse.postValue(
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
                    _tripMeterImageResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleTripImageResponse(
        response: Response<PostImageResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _tripMeterImageResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _tripMeterImageResponse.postValue(Event(Resource.Error(message)))
                    return
                }
                return
            }
        } else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _tripMeterImageResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _tripMeterImageResponse.postValue(Event(Resource.Error(response.message())))
    }
    
    
    /**
     * post maintenance  image
     * */
    private suspend fun postMaintenanceImage(
        @Part appointmentId: RequestBody,
        @Part maintenanceId: RequestBody,
        @Part maintenanceItemId: RequestBody,
        @Part maintenanceLineId: RequestBody,
        @Part description: RequestBody,
        @Part vehicleId: RequestBody,
        @Part imageBody: MultipartBody.Part
    ) {
        _maintenanceImageResponse.value = Event(Resource.Loading())
        try {
            Utils.logThis("Maintenance Image param", imageBody.toString())
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.postMaintenanceImageData( appointmentId,
                    maintenanceId,maintenanceItemId,maintenanceLineId, description,vehicleId,imageBody)
                handleMaintenanceImageResponse(response)
            } else {
                _maintenanceImageResponse.postValue(
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
                    _maintenanceImageResponse.postValue(
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
                    _maintenanceImageResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleMaintenanceImageResponse(
        response: Response<PostImageResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _maintenanceImageResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _maintenanceImageResponse.postValue(Event(Resource.Error(message)))
                    return
                }
                return
            }
        } else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _maintenanceImageResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _maintenanceImageResponse.postValue(Event(Resource.Error(response.message())))
    }
    /**
     * After image API
     * */
    private suspend fun afterImage(
        @Part appointmentId: RequestBody,
        @Part lineId: RequestBody,
        @Part issueDetail: RequestBody,
        @Part imageBody: MultipartBody.Part
    ) {
        _afterImageResponse.value = Event(Resource.Loading())
        try {
            Utils.logThis("Concerned Issue Image param", imageBody.toString())
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.afterImageData(appointmentId,lineId,issueDetail, imageBody)
                handleAfterImageResponse(response)
            } else {
                _afterImageResponse.postValue(
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
                    _afterImageResponse.postValue(
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
                    _afterImageResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleAfterImageResponse(response: Response<PostImageResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->            val status = resultResponse.Status
                if (status) {
                    _afterImageResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _afterImageResponse.postValue(Event(Resource.Error(message)))
                    return
                }
                return
            }
        } else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _afterImageResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _afterImageResponse.postValue(Event(Resource.Error(response.message())))
    }

}