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
import ymsli.com.cpo.data.model.DealerNearResponse
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.Event
import ymsli.com.cpo.utils.MyApplication
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.hasInternetConnection
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DealerNearViewModel@Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app)   {

    private val _dealersNearResponse = MutableLiveData<Event<Resource<DealerNearResponse>>>()
    val dealersNearResponse: LiveData<Event<Resource<DealerNearResponse>>> = _dealersNearResponse

    fun dealersNearRequest()=viewModelScope.launch{
        getDealersNearMe()
    }

    private suspend fun getDealersNearMe() {
        _dealersNearResponse.value=Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.dealersNearApi()
                handleDealersNearResponse(response)
            } else {
                _dealersNearResponse.postValue(
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
                    _dealersNearResponse.postValue(
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
                    _dealersNearResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleDealersNearResponse(response: Response<DealerNearResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _dealersNearResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _dealersNearResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _dealersNearResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _dealersNearResponse.postValue(Event(Resource.Error(response.message())))
    }
}