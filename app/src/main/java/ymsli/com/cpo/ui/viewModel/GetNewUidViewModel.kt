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
import ymsli.com.cpo.data.model.ActiveWarrantiesResponse
import ymsli.com.cpo.data.model.GetNewUidResponse
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.Event
import ymsli.com.cpo.utils.MyApplication
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.hasInternetConnection
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GetNewUidViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app)   {

    private val _newUidResponse = MutableLiveData<Event<Resource<GetNewUidResponse>>>()
    val newUidResponse: LiveData<Event<Resource<GetNewUidResponse>>> = _newUidResponse

    private val _lineItemUidResponse = MutableLiveData<Event<Resource<GetNewUidResponse>>>()
    val lineItemUidResponse: LiveData<Event<Resource<GetNewUidResponse>>> = _lineItemUidResponse
    
    private val _tripMeterLineIdResponse = MutableLiveData<Event<Resource<GetNewUidResponse>>>()
    val tripMeterLineIdResponse: LiveData<Event<Resource<GetNewUidResponse>>> = _tripMeterLineIdResponse
    
    fun newUidRequestRequest()=viewModelScope.launch{
        getNewUidRequestRequest()

    }

    fun lineItemRequestRequest()=viewModelScope.launch{
        getLineItemUidRequestRequest() 
    } 
    
    fun tripMeterLineRequest()=viewModelScope.launch{
        getTripMeterLineRequest()

    }

    private suspend fun getNewUidRequestRequest() {
        _newUidResponse.value=Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getNewUidApi()
                handleNewUidResponse(response)
            } else {
                _newUidResponse.postValue(
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
                    _newUidResponse.postValue(
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
                    _newUidResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleNewUidResponse(response: Response<GetNewUidResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _newUidResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _newUidResponse.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }else
        {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _newUidResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _newUidResponse.postValue(Event(Resource.Error(response.message())))
    }


    private suspend fun getLineItemUidRequestRequest() {
        _lineItemUidResponse.value=Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getNewUidApi()
                handleLineItemUidResponse(response)
            } else {
                _lineItemUidResponse.postValue(
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
                    _lineItemUidResponse.postValue(
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
                    _lineItemUidResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleLineItemUidResponse(response: Response<GetNewUidResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _lineItemUidResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _lineItemUidResponse.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }else
        {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _lineItemUidResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _lineItemUidResponse.postValue(Event(Resource.Error(response.message())))
    }



    private suspend fun getTripMeterLineRequest() {
        _tripMeterLineIdResponse.value=Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getNewUidApi()
                handleTripMeterLineIdResponse(response)
            } else {
                _tripMeterLineIdResponse.postValue(
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
                    _tripMeterLineIdResponse.postValue(
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
                    _tripMeterLineIdResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleTripMeterLineIdResponse(response: Response<GetNewUidResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _tripMeterLineIdResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _tripMeterLineIdResponse.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }else
        {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _tripMeterLineIdResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _tripMeterLineIdResponse.postValue(Event(Resource.Error(response.message())))
    }
}