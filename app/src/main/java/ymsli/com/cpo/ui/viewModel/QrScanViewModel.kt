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
import ymsli.com.cpo.data.model.VehicleInformationResponse
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.Event
import ymsli.com.cpo.utils.MyApplication
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.hasInternetConnection
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class QrScanViewModel@Inject constructor(
    app: Application,
    private val appRepository: AppRepository
): AndroidViewModel(app) {
    private val _vehicleInformationResponse= MutableLiveData<Event<Resource<VehicleInformationResponse>>>()
    val vehicleInformationResponse: LiveData<Event<Resource<VehicleInformationResponse>>> =_vehicleInformationResponse

    fun vehicleInformationRequest(body: RequestBodies.VehicleInformationBody)=viewModelScope.launch {
        getVehicleInformation(body)
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