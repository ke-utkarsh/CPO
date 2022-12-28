package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.item_nft.view.*
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.NftDetail

import ymsli.com.cpo.databinding.ActivityCustomerTitleBinding
import ymsli.com.cpo.ui.view.adapter.CustomerNftDetailAdapter

import ymsli.com.cpo.ui.viewModel.NftDetailViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils

@AndroidEntryPoint
class CustomerNftDetailActivity: AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mBinding : ActivityCustomerTitleBinding
    private var activity:Activity=this@CustomerNftDetailActivity
    private val vm: NftDetailViewModel by viewModels()
    private val tag="CustomerTitle"
    private var nftList=ArrayList<NftDetail>()
    private var tabType="NFT"
    private lateinit var navigationView:NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCustomerTitleBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        mBinding.rvNft.layoutManager= LinearLayoutManager(this)
        initView()
        onClickListener()
        responseObserver()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent=Intent(activity,LoginActivity::class.java)
        startActivity(intent)
    }

    private fun initView() {
        navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        vm.getNftStatsRequest()
        vm.activeWarrantiesRequest(RequestBodies.CustomerNftDetailRequestBody(3))
        handleTabClick()
    }

    private fun onClickListener() {
        mBinding.clNft.setOnClickListener{
            vm.getNftStatsRequest()
            vm.activeWarrantiesRequest(RequestBodies.CustomerNftDetailRequestBody(3))
            tabType = "NFT"
            handleTabClick()

        }
        mBinding.clAppNft.setOnClickListener{
            vm.getNftStatsRequest()
            vm.activeWarrantiesRequest(RequestBodies.CustomerNftDetailRequestBody(2))
            tabType = "A_NFT"
            handleTabClick()
        }
        mBinding.ivWindow.setOnClickListener {
            mBinding.drawerlayout.openDrawer(Gravity.START)
        }
        mBinding.ivLogout.setOnClickListener {
            var intent=Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.offer -> {
                val intent=Intent(activity,QrScanActivity::class.java)
                mBinding.drawerlayout.closeDrawer(GravityCompat.START)
                startActivity(intent)
            }
            R.id.profile -> {
                val intent = Intent(activity, ProfileActivity::class.java)
                mBinding.drawerlayout.closeDrawer(GravityCompat.START)
                startActivity(intent)
                //Toasty.info(activity,"Profile Clicked").show()
            }
        }
        return true
    }

    private fun handleTabClick()
    {
        if(tabType=="NFT")
        {
            mBinding.clAppNft.setBackgroundColor(Color.TRANSPARENT)
            mBinding.clNft.setBackgroundResource(R.drawable.create_nft_button_background)
            mBinding.tvNFT.setTextColor(Color.WHITE)
            mBinding.tvApproveNFT.setTextColor(Color.parseColor("#141414"))
            mBinding.tvNumberApproveNfts.setTextColor(Color.parseColor("#141414"))

        }
        else
        {
            mBinding.clNft.setBackgroundColor(Color.TRANSPARENT)
            mBinding.clAppNft.setBackgroundResource(R.drawable.create_nft_button_background)
            mBinding.tvApproveNFT.setTextColor(Color.WHITE)
            mBinding.tvNFT.setTextColor(Color.parseColor("#141414"))
            mBinding.tvNumberNfts.setTextColor(Color.parseColor("#141414"))
        }
    }


    private fun responseObserver() {
        vm.nftStatsResponse.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let{response->
                when(response){
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = View.GONE
                        response.data?.let {
                            Log.e("Nft stats response",it.Result.toString())
                            mBinding.tvNumberNfts.text=it.Result.MintedNFTCount.toString()
                            mBinding.tvNumberApproveNfts.text=it.Result.MintableNFTCount.toString()
                        }
                    }
                }
            }
        })
        vm.nftDetailResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = View.GONE
                        response.data?.let {
                            Utils.logThis(tag, "Customer nft/anft Response  is :   ${it.Result}")
                            nftList = it.Result as ArrayList<NftDetail>
                            val adapter=CustomerNftDetailAdapter(activity,nftList ,{
                                val intent= Intent(applicationContext,VehicleInformationCustomer::class.java)
                                intent.putExtra("vehicleId",nftList[it].VehicleId)
                                intent.putExtra("NFT_TYPE",tabType);
                                intent.putExtra("NFT_ID",nftList[it].NFTId)
                                intent.putExtra("viewOnly","N")
                                startActivity(intent)
                            },{it->
                                val intent= Intent(applicationContext,WebViewActivity::class.java)
                                intent.putExtra(Constants.WEBVIEW_TITLE,"NFT Metadata")
                                intent.putExtra(Constants.URL,nftList[it].NFTMetadataUrl)
                                startActivity(intent)
                            })
                            mBinding.rvNft.adapter=adapter

                            if(nftList.size == 0)
                            {
                                mBinding.tvNoData.visibility = View.VISIBLE
                                mBinding.rvNft.visibility = View.GONE
                            }
                            else
                            {
                                mBinding.rvNft.visibility = View.VISIBLE
                                mBinding.tvNoData.visibility = View.GONE

                            }

                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = View.GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                                Utils.sessionTimeOutStartLoginScreen(activity)
                            } else {
                                CancelableToast(activity,message).error()
                            }
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })


    }
}