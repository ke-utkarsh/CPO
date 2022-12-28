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
import ymsli.com.cpo.data.model.CustomerNftDetailResponse
import ymsli.com.cpo.data.model.GetNftStatsResponse
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.Event
import ymsli.com.cpo.utils.MyApplication
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.hasInternetConnection
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NftDetailViewModel@Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app)  {

    private val _nftDetailResponse = MutableLiveData<Event<Resource<CustomerNftDetailResponse>>>()
    val nftDetailResponse: LiveData<Event<Resource<CustomerNftDetailResponse>>> = _nftDetailResponse

    private val _nftStatsResponse = MutableLiveData<Event<Resource<GetNftStatsResponse>>>()
    val nftStatsResponse: LiveData<Event<Resource<GetNftStatsResponse>>> = _nftStatsResponse



    fun activeWarrantiesRequest(body: RequestBodies.CustomerNftDetailRequestBody) = viewModelScope.launch {
        getActiveWarrantiesRequest(body)
    }

    /**
     * Active Warranties Request API request
     * */
    private suspend fun getActiveWarrantiesRequest(body:RequestBodies.CustomerNftDetailRequestBody) {
        _nftDetailResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.customerNftDetailsApi(body)
                handleNftDetailResponse(response)
            } else {
                _nftDetailResponse.postValue(
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
                    _nftDetailResponse.postValue(
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
                    _nftDetailResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleNftDetailResponse(response: Response<CustomerNftDetailResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _nftDetailResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _nftDetailResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _nftDetailResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _nftDetailResponse.postValue(Event(Resource.Error(response.message())))
    }

    fun getNftStatsRequest()=viewModelScope.launch {
        getNftStats()
    }

    private suspend fun getNftStats(){
        _nftStatsResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.GetNftStatsRequest()
                handleNftStatsResponse(response)
            } else {
                _nftStatsResponse.postValue(
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
                    _nftStatsResponse.postValue(
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
                    _nftStatsResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleNftStatsResponse(response: Response<GetNftStatsResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _nftStatsResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _nftStatsResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _nftStatsResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _nftStatsResponse.postValue(Event(Resource.Error(response.message())))
    }


}