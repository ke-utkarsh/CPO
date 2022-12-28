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
class ActiveWarrantiesViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _activeWarrantiesResponse = MutableLiveData<Event<Resource<ActiveWarrantiesResponse>>>()
    val activeWarrantiesResponse: LiveData<Event<Resource<ActiveWarrantiesResponse>>> = _activeWarrantiesResponse
    
  
    
    
    fun activeWarrantiesRequest() = viewModelScope.launch {
        getActiveWarrantiesRequest()
    } 
    
    
   
    /**
     * Active Warranties Request API request
    * */

    private suspend fun getActiveWarrantiesRequest() {
        _activeWarrantiesResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.activeWarrantiesRepository()
                handleActiveWarrantiesResponse( response)
            } else {
                _activeWarrantiesResponse.postValue(
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
                    _activeWarrantiesResponse.postValue(
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
                    _activeWarrantiesResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleActiveWarrantiesResponse(response: Response<ActiveWarrantiesResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _activeWarrantiesResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _activeWarrantiesResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _activeWarrantiesResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _activeWarrantiesResponse.postValue(Event(Resource.Error(response.message())))
    }


 
}