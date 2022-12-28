package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ymsli.com.cpo.R
import ymsli.com.cpo.databinding.ActivityLoginBinding
import ymsli.com.cpo.ui.viewModel.LoginViewModel
import java.util.ArrayList
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import es.dmoral.toasty.Toasty
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.RoleModel
import ymsli.com.cpo.data.model.UserModel
import ymsli.com.cpo.data.sharedpref.SharedPref
import ymsli.com.cpo.utils.*


@AndroidEntryPoint
class LoginActivity : AppCompatActivity(){
    private lateinit var mBinding : ActivityLoginBinding
    private var activity : Activity = this@LoginActivity
    private val vm: LoginViewModel by viewModels()
    private val tag = "Login"
    private  var selectedUser : String? = null
    private  var selectedRole :String? = null
    private lateinit var userList : ArrayList<UserModel>
    private lateinit var roleList : ArrayList<RoleModel>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        init()
        clickListener()
        responseObserver()
    }

    private fun init() {
        vm.roleRequest()
    }

    override fun onResume() {
        super.onResume()
        init()
        clickListener()
        responseObserver()
    }

    private fun clickListener() {

        mBinding.spRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedRole = roleList[position].Id
                vm.userRequest(RequestBodies.UserRequestBody(selectedRole!!))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        mBinding.spUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedUser = userList[position].UserName
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        mBinding.btnLogin.setOnClickListener{

           if(hasActiveInternetConnection(activity))
           {
               if(selectedUser.isNullOrEmpty())
               {
                   vm.roleRequest()
               }
               else
               {
                   vm.loginRequest(RequestBodies.LoginRequestBody(selectedUser!!,selectedRole!!))
               }

           }
            else
           {
               Toasty.warning(activity,"No Internet Connection").show()
           }

        }

    }

    private fun responseObserver() {

        vm.roleResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                       // mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Utils.logThis(tag, "Role Api Response  is :   ${it.Result}")
                            roleList = it.Result as ArrayList<RoleModel>
                            val roleAdapter = ArrayAdapter(activity, R.layout.spinner_text, roleList)
                            mBinding.spRole.adapter = roleAdapter
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                            } else {
                                CancelableToast(activity,message).error()

                            }
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })

        vm.userResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Utils.logThis(tag, "User Api Response  is :   ${it.Result}")
                            userList = it.Result as ArrayList<UserModel>
                            val roleAdapter = ArrayAdapter(activity, R.layout.spinner_text, userList)
                            mBinding.spUser.adapter = roleAdapter
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                            } else {
                                CancelableToast(activity,message).error()

                            }
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })


        vm.loginResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {

                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Utils.logThis(tag, "Login Api Response  is :   ${it.Result}")
                            SharedPref.putString(SharedPreferenceConstant.TOKEN, it.Result)
                            if(selectedRole == "6d2ace46-decf-4c4d-b4f3-1859c1c49838")
                            {
                                val intent = Intent(applicationContext, WarrantiesActivity::class.java)
                                startActivity(intent)
                            }
                            else
                            {
                                val intent = Intent(applicationContext, CustomerNftDetailActivity::class.java)
                                startActivity(intent)
                            }

                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                            } else {
                                CancelableToast(activity,message).error()
                            }
                        }
                    }

                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })

    }





}