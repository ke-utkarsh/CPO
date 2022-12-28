package ymsli.com.cpo.ui.viewModel

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
import ymsli.com.cpo.data.model.AcceptApiResponse
import ymsli.com.cpo.data.model.GetNewUidResponse
import ymsli.com.cpo.data.model.OfferListResponse
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.Event
import ymsli.com.cpo.utils.MyApplication
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.hasInternetConnection
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class OfferListViewModel@Inject constructor(app: Application, private val appRepository: AppRepository) : AndroidViewModel(app)  {
    private val _offerListResponse=
        MutableLiveData<Event<Resource<OfferListResponse>>>()
    val offerListResponse: LiveData<Event<Resource<OfferListResponse>>> =_offerListResponse

    private val _rejectOfferResponse=
        MutableLiveData<Event<Resource<GetNewUidResponse>>>()
    val rejectOfferResponse: LiveData<Event<Resource<GetNewUidResponse>>> =_rejectOfferResponse

    private val _acceptOfferResponse=
        MutableLiveData<Event<Resource<AcceptApiResponse>>>()
    val acceptOfferResponse: LiveData<Event<Resource<AcceptApiResponse>>> =_acceptOfferResponse




    fun offersRequest(body: RequestBodies.VehicleInformationBody)=viewModelScope.launch {
        getOfferList(body)
    }

    fun rejectOffer(body: RequestBodies.OfferRejectRequest)=viewModelScope.launch {
        rejectOfferApi(body)
    }

    private suspend fun rejectOfferApi(body: RequestBodies.OfferRejectRequest) {
        _rejectOfferResponse.value= Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())){
                val response=appRepository.OfferRejectRequest(body)
                handleReject(response)
            } else {
                _rejectOfferResponse.postValue(
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
                    _rejectOfferResponse.postValue(
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
                    _rejectOfferResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleReject(response: Response<GetNewUidResponse>) {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                val status=resultResponse.Status
                if (status) {
                    _rejectOfferResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _rejectOfferResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _rejectOfferResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _rejectOfferResponse.postValue(Event(Resource.Error(response.message())))
    }

    private suspend fun getOfferList(body: RequestBodies.VehicleInformationBody) {
        _offerListResponse.value= Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())){
                val response=appRepository.OfferListRequest(body)
                handleOfferList(response)
            }else {
                _offerListResponse.postValue(
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
                    _offerListResponse.postValue(
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
                    _offerListResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleOfferList(response: Response<OfferListResponse>) {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                val status=resultResponse.Status
                if (status) {
                    _offerListResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _offerListResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _offerListResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _offerListResponse.postValue(Event(Resource.Error(response.message())))
    }
    fun acceptRequest(body: RequestBodies.OfferAcceptedRequest)=viewModelScope.launch {
        acceptRequestApi(body)
    }

    private suspend fun acceptRequestApi(body: RequestBodies.OfferAcceptedRequest) {
        _acceptOfferResponse.value= Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())){
                val response=appRepository.OfferAcceptRequest(body)
                Log.e("Accept api response",response.toString())
                handleAcceptResponse(response)
            }else {
                _acceptOfferResponse.postValue(
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
                    _acceptOfferResponse.postValue(
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
                    _acceptOfferResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleAcceptResponse(response: Response<AcceptApiResponse>) {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                val status=resultResponse.Status
                if (status) {
                    _acceptOfferResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _acceptOfferResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _acceptOfferResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _acceptOfferResponse.postValue(Event(Resource.Error(response.message())))
    }
}