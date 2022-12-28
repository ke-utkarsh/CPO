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
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.data.repository.AppRepository
import ymsli.com.cpo.utils.*
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    private val _roleResponse = MutableLiveData<Event<Resource<RoleResponse>>>()
    val roleResponse: LiveData<Event<Resource<RoleResponse>>> = _roleResponse
    
    private val _userResponse = MutableLiveData<Event<Resource<UserResponse>>>()
    val userResponse: LiveData<Event<Resource<UserResponse>>> = _userResponse
    
    
    private val _loginResponse = MutableLiveData<Event<Resource<LoginResponse>>>()
    val loginResponse: LiveData<Event<Resource<LoginResponse>>> = _loginResponse
    
    
    fun roleRequest() = viewModelScope.launch {
        getRoleRequest()
    } 
    
    
    fun userRequest(body : RequestBodies.UserRequestBody) = viewModelScope.launch {
        getUserRequest(body)
    }



    fun loginRequest(body: RequestBodies.LoginRequestBody) = viewModelScope.launch {
        loginStart(body)
    }

    /**
     * Login API request
    * */

    private suspend fun loginStart(body: RequestBodies.LoginRequestBody) {
        _loginResponse.value = Event(Resource.Loading())
        try {
            Utils.logThis("login param", body.toString())
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.loginRepository(body)
                handleLoginResponse( response)
            } else {
                _loginResponse.postValue(
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
                    _loginResponse.postValue(
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
                    _loginResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleLoginResponse(response: Response<LoginResponse>
    ) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _loginResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _loginResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _userResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return _loginResponse.postValue(Event(Resource.Error(response.message())))
    }


    /**
     * Role Request
    * */
    private suspend fun getRoleRequest() {
        _roleResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.roleRepository()
                handleRoleResponse( response)
            } else {
                _roleResponse.postValue(
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
                    _roleResponse.postValue(
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
                    _roleResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleRoleResponse(response: Response<RoleResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _roleResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _roleResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _userResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return _roleResponse.postValue(Event(Resource.Error(response.message())))
    }   
    
    
    /**
     * Role Request
    * */
    private suspend fun getUserRequest(body: RequestBodies.UserRequestBody) {
        _userResponse.value = Event(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.userRepository(body)
                handleUserResponse( response)
            } else {
                _userResponse.postValue(
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
                    _userResponse.postValue(
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
                    _userResponse.postValue(Event(Resource.Error(" ${t.localizedMessage}")))
                }
            }
        }
    }

    private fun handleUserResponse(response: Response<UserResponse>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val status = resultResponse.Status
                if (status) {
                    _userResponse.postValue(Event(Resource.Success(resultResponse)))
                } else {
                    val message = resultResponse.ErrorMessage
                    _userResponse.postValue(Event(Resource.Error(message as String)))
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
                    return _userResponse.postValue(Event(Resource.Error(errorMessage)))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return _userResponse.postValue(Event(Resource.Error(response.errorBody().toString())))
    }
    
}