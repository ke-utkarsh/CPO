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
import ymsli.com.cpo.data.model.GetNewUidResponse
import ymsli.com.cpo.data.model.MintNftResponse
import ymsli.com.cpo.data.model.SubmitNFTDataResponse
import ymsli.com.cpo.data.model.VehicleInformationResponse
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class VehicleInformationViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app)  {

    private val _vehicleInformationResponse=MutableLiveData<Event<Resource<VehicleInformationResponse>>>()
    val vehicleInformationResponse:LiveData<Event<Resource<VehicleInformationResponse>>> =_vehicleInformationResponse

    private val _submitNFTResponse=MutableLiveData<Event<Resource<SubmitNFTDataResponse>>>()
    val submitNFTResponse:LiveData<Event<Resource<SubmitNFTDataResponse>>> =_submitNFTResponse

    private val _mintNftResponse=MutableLiveData<Event<Resource<MintNftResponse>>>()
    val mintNftResponse:LiveData<Event<Resource<MintNftResponse>>> =_mintNftResponse

    private val _submitOfferResponse=MutableLiveData<Event<Resource<GetNewUidResponse>>>()
    val submitOfferResponse:LiveData<Event<Resource<GetNewUidResponse>>> = _submitOfferResponse


    val nextEventClick=MutableLiveData<Boolean>()
    val _nextEventClick:LiveData<Boolean> =nextEventClick



    fun vehicleInformationRequest(body:RequestBodies.VehicleInformationBody)=viewModelScope.launch {
        getVehicleInformation(body)
    }

    fun submitNFTRequest(body:RequestBodies.SubmitNftDataBody)=viewModelScope.launch {
        postSubmitNFTData(body)
    }

    fun mintNftRequest(body:RequestBodies.MintNftRequestBody)=viewModelScope.launch {
        postMintNftRequest(body)
    }

    fun submitOfferRequest(body: RequestBodies.SubmitOfferPriceRequest)=viewModelScope.launch {
        postOfferprice(body)
    }

    private suspend fun postOfferprice(body: RequestBodies.SubmitOfferPriceRequest) {
        _submitOfferResponse.value=Event(Resource.Loading())
        try{
            if (hasInternetConnection(getApplication<MyApplication>())){
                val response=appRepository.PostOfferRequest(body)
                handleSubmitOffer(response)
            }else{
                _submitOfferResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }
        }catch (t:Throwable){
            when (t) {
                is IOException -> {
                    _submitOfferResponse.postValue(
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
                    _submitOfferResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleSubmitOffer(response: Response<GetNewUidResponse>) {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                val status=resultResponse.Status
                if(status){
                    _submitOfferResponse.postValue(Event(Resource.Success(resultResponse)))
                }else{
                    val message = resultResponse.ErrorMessage
                    _submitOfferResponse.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }else{
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _submitOfferResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _submitOfferResponse.postValue(Event(Resource.Error(response.message())))
    }

    private suspend fun postMintNftRequest(body: RequestBodies.MintNftRequestBody) {
        _mintNftResponse.value=Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())){
                val response=appRepository.mintNftSubmitApi(body)
                handleMintNFT(response)
            }else{
                _mintNftResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }
        }catch (t:Throwable){
            when (t) {
                is IOException -> {
                    _mintNftResponse.postValue(
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
                    _mintNftResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleMintNFT(response: Response<MintNftResponse>) {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                val status=resultResponse.Status
                if(status){
                    _mintNftResponse.postValue(Event(Resource.Success(resultResponse)))
                }else{
                    val message = resultResponse.ErrorMessage
                    _mintNftResponse.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }else{
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _mintNftResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _mintNftResponse.postValue(Event(Resource.Error(response.message())))
    }

    //==============================================================================================================
    private suspend fun postSubmitNFTData(body: RequestBodies.SubmitNftDataBody) {
        Utils.logThis("TAg",body.toString())
        _submitNFTResponse.value=Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())){
                val response=appRepository.postSubmitNFTData(body)
                handleSubmitNFT(response)
            }else{
                _submitNFTResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<MyApplication>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }
        }catch (t:Throwable){
            when (t) {
                is IOException -> {
                    _submitNFTResponse.postValue(
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
                    _submitNFTResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleSubmitNFT(response: Response<SubmitNFTDataResponse>) {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                val status=resultResponse.Status
                if(status){
                    _submitNFTResponse.postValue(Event(Resource.Success(resultResponse)))
                }else{
                    val message = resultResponse.ErrorMessage
                    _submitNFTResponse.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }else{
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _submitNFTResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _submitNFTResponse.postValue(Event(Resource.Error(response.message())))
    }

    private suspend fun getVehicleInformation(body:RequestBodies.VehicleInformationBody) {
        _vehicleInformationResponse.value= Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())){
                val response=appRepository.vehicleInformationRepository(body)
                handleVechicleInformation(response)
            }else {
                _vehicleInformationResponse.postValue(
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
                    _vehicleInformationResponse.postValue(
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
                    _vehicleInformationResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleVechicleInformation(response: Response<VehicleInformationResponse>) {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                val status=resultResponse.Status
                if (status) {
                    _vehicleInformationResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _vehicleInformationResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _vehicleInformationResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return _vehicleInformationResponse.postValue(Event(Resource.Error(response.message())))
    }
}