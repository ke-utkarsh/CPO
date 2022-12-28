package ymsli.com.cpo.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.GetMaintenanceItems
import ymsli.com.cpo.data.model.GetNewUidResponse
import ymsli.com.cpo.data.model.MaintenanceResult
import ymsli.com.cpo.data.model.UserResponse
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.Event
import ymsli.com.cpo.utils.MyApplication
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.hasInternetConnection
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ModifyItemsViewModel@Inject constructor(app: Application, private val appRepository: AppRepository): AndroidViewModel(app){

    private val _maintenanceDetail = MutableLiveData<Event<Resource<GetMaintenanceItems>>>()
    val maintenanceDetail: LiveData<Event<Resource<GetMaintenanceItems>>> = _maintenanceDetail

    private val _saveMaintenanceDetail = MutableLiveData<Event<Resource<GetNewUidResponse>>>()
    val saveMaintenanceDetail: LiveData<Event<Resource<GetNewUidResponse>>> = _saveMaintenanceDetail

    fun saveNxtMaintenanceApi(body:RequestBodies.SaveMaintenanceRequestBody)= viewModelScope.launch {
        saveNextMaintenance(body)
    }

    private suspend fun saveNextMaintenance(body: RequestBodies.SaveMaintenanceRequestBody) {
        _saveMaintenanceDetail.value=Event(Resource.Loading())
        try{
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.saveMaintenanceApi(body)
                handleSaveMaintenanceResponse(response)
            } else {
                _saveMaintenanceDetail.postValue(
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
                    _saveMaintenanceDetail.postValue(
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
                    _saveMaintenanceDetail.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleSaveMaintenanceResponse(response: Response<GetNewUidResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _saveMaintenanceDetail.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _saveMaintenanceDetail.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _saveMaintenanceDetail.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return _saveMaintenanceDetail.postValue(Event(Resource.Error(response.errorBody().toString())))
    }

    fun maintenanceDetailApi(body: RequestBodies.GetMaintenanceRequestBody)= viewModelScope.launch {
        getMaintenanceDetail(body)
    }

    private suspend fun getMaintenanceDetail(body: RequestBodies.GetMaintenanceRequestBody) {
        _maintenanceDetail.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getMaintenanceIDApi(body)
                handleMaintenanceResponse( response)
            } else {
                _maintenanceDetail.postValue(
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
                    _maintenanceDetail.postValue(
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
                    _maintenanceDetail.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }

    }

    private fun handleMaintenanceResponse(response: Response<GetMaintenanceItems>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _maintenanceDetail.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _maintenanceDetail.postValue(Event(Resource.Error(message as String)))
                    return
                }
                return
            }
        }
        else {
            response.errorBody()?.let {
                try {
                    val errorBody = response.errorBody()!!.string()
                    val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                    val errorMessage = jsonObject.getString("ErrorMessage")
                    return _maintenanceDetail.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return _maintenanceDetail.postValue(Event(Resource.Error(response.errorBody().toString())))
    }
}