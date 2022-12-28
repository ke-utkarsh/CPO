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
class ServiceItemsViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _serviceItemResponse = MutableLiveData<Event<Resource<ServiceItemResponse>>>()
    val serviceItemResponse: LiveData<Event<Resource<ServiceItemResponse>>> = _serviceItemResponse

    
    fun serviceItemRequest() = viewModelScope.launch {
        getServiceItemRequest()
    } 
    
    
   
    /**
     * Service Item Request API request
     * */

    private suspend fun getServiceItemRequest() {
        _serviceItemResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getServiceItemApi()
                handleActiveWarrantiesResponse( response)
            } else {
                _serviceItemResponse.postValue(
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
                    _serviceItemResponse.postValue(
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
                    _serviceItemResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleActiveWarrantiesResponse(response: Response<ServiceItemResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _serviceItemResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _serviceItemResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _serviceItemResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _serviceItemResponse.postValue(Event(Resource.Error(response.message())))
    }


 
}