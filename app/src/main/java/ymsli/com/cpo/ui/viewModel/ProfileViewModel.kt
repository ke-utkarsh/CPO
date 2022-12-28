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
class ProfileViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _profileResponse = MutableLiveData<Event<Resource<ProfileResponse>>>()
    private val _walletlinkResponse = MutableLiveData<Event<Resource<WalletLinkResponse>>>()
    val profileResponse: LiveData<Event<Resource<ProfileResponse>>> = _profileResponse
    val walletlinkResponse: LiveData<Event<Resource<WalletLinkResponse>>> = _walletlinkResponse
    
  
    
    
    fun profileRequest() = viewModelScope.launch {
        getProfileRequest()
    }


    fun walletLinkRequestCall(walletLinkRequest : RequestBodies.WalletLinkRequestBody) = viewModelScope.launch {
        walletLinkRequest(walletLinkRequest)
    }
    
    
   
    /**
     * Active Warranties Request API request
    * */

    private suspend fun getProfileRequest() {
        _profileResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.profileRepository()
                handleProfileResponse( response)
            } else {
                _profileResponse.postValue(
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
                    _profileResponse.postValue(
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
                    _profileResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleProfileResponse(response: Response<ProfileResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _profileResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _profileResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _profileResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _profileResponse.postValue(Event(Resource.Error(response.message())))
    }


    /**
     * Wallet link Request API request
     * */

    private suspend fun walletLinkRequest(walletLinkRequestBody: RequestBodies.WalletLinkRequestBody) {
        _walletlinkResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.walletRepository(walletLinkRequestBody)
                handleWalletResponse(response)
            } else {
                _walletlinkResponse.postValue(
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
                    _walletlinkResponse.postValue(
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
                    _walletlinkResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleWalletResponse(response: Response<WalletLinkResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _walletlinkResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _walletlinkResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _walletlinkResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _walletlinkResponse.postValue(Event(Resource.Error(response.message())))
    }


}