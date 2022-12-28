package ymsli.com.cpo.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part
import ymsli.com.cpo.data.api.API
import ymsli.com.cpo.data.api.RequestBodies
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiService: API) {

    suspend fun roleRepository() = apiService.roleApi()

    suspend fun userRepository(body: RequestBodies.UserRequestBody) =
        apiService.userApi(body)

    suspend fun loginRepository(body: RequestBodies.LoginRequestBody) =
        apiService.loginApi(body)

    suspend fun profileRepository() = apiService.loginApi()

    suspend fun activeWarrantiesRepository() = apiService.activeWarrantiesApi()

    suspend fun vehicleInformationRepository(body: RequestBodies.VehicleInformationBody) =
        apiService.vehicleInfoApi(body)

    suspend fun walletRepository(walletLinkRequestBody: RequestBodies.WalletLinkRequestBody) =
        apiService.walletLinkApi(walletLinkRequestBody)

    suspend fun postPdiImageData(
        @Part vehicleMasterId: RequestBody,
        @Part imageBody: MultipartBody.Part) =
        apiService.postPdiImageData(vehicleMasterId,imageBody)

    suspend fun postVehicleImageData(@Part vehicleMasterId: RequestBody, @Part imageBody: MultipartBody.Part) =
        apiService.postVehicleImageData(vehicleMasterId,imageBody)

    suspend fun postSubmitNFTData(body:RequestBodies.SubmitNftDataBody)=
        apiService.submitNFTDataApi(body)

    suspend fun customerNftDetailsApi(body:RequestBodies.CustomerNftDetailRequestBody)=
        apiService.customerNftDetailsApi(body)

    suspend fun mintNftSubmitApi(body:RequestBodies.MintNftRequestBody)=
        apiService.mintNftSubmitApi(body)

    suspend fun getDatesApi() = apiService.getDatesApi()

    suspend fun getTimeApi() = apiService.getTimeApi()

    suspend fun dealersNearApi() = apiService.dealersNearApi()

    suspend fun getNewUidApi() = apiService.getNewUidApi()

    suspend fun postConcernedIssueImageData(@Part appointmentId: RequestBody,@Part lineId: RequestBody,@Part issueDetail: RequestBody, @Part imageBody: MultipartBody.Part) =
        apiService.postConcernedIssueImageData(appointmentId,lineId,issueDetail,imageBody)

    suspend fun postTripMeterImageData(@Part appointmentId: RequestBody,@Part lineId: RequestBody,@Part issueDetail: RequestBody, @Part imageBody: MultipartBody.Part) =
        apiService.postTripMeterImageData(appointmentId,lineId,issueDetail,imageBody)


    suspend fun submitAppointmentApi(body:RequestBodies.SubmitAppointRequestBody)=
        apiService.submitAppointmentApi(body)

    suspend fun getAppointmentApi(body:RequestBodies.GetAppointmentRequestBody)=
        apiService.getAppointmentApi(body)

    suspend fun getServiceItemApi() = apiService.getServiceItemApi()

    suspend fun postMaintenanceImageData(@Part appointmentId: RequestBody,
                                         @Part maintenanceId: RequestBody,
                                         @Part maintenanceItemId: RequestBody,
                                         @Part maintenanceLineId: RequestBody,
                                         @Part description: RequestBody,
                                         @Part vehicleId: RequestBody,
                                         @Part imageBody: MultipartBody.Part) =
        apiService.postMaintenanceImageData(appointmentId,maintenanceId,maintenanceItemId,maintenanceLineId,description,vehicleId,imageBody)

    suspend fun getMaintenanceIDApi(body:RequestBodies.GetMaintenanceRequestBody)=
        apiService.getMaintenanceDetailApi(body)

    suspend fun afterImageData(@Part appointmentId: RequestBody,@Part lineId: RequestBody,@Part issueDetail: RequestBody, @Part imageBody: MultipartBody.Part) =
        apiService.afterImageData(appointmentId,lineId,issueDetail,imageBody)

    suspend fun getServiceSubmitDetails(body:RequestBodies.GetAppointmentDetail) = apiService.getServiceSubmitDetails(body)

    suspend fun saveMaintenanceApi(body:RequestBodies.SaveMaintenanceRequestBody) = apiService.saveNextMaintenanceApi(body)
    
    suspend fun maintenanceListApi(body: RequestBodies.GetAppointmentRequestBody) =apiService.getMaintenanceListApi(body)

    suspend fun ServiceSubmitCompleteRequest(body: RequestBodies.SubmitCompleteRequest) = apiService.submitServiceCompleteRequest(body)

    suspend fun AppraisalHistoryRequest(body: RequestBodies.VehicleInformationBody) =apiService.appraisalHistoryApi(body)

    suspend fun GetNftStatsRequest() =apiService.getNftStats()

    suspend fun PostOfferRequest(body: RequestBodies.SubmitOfferPriceRequest) =apiService.submitOffer(body)

    suspend fun OfferListRequest(body: RequestBodies.VehicleInformationBody) =apiService.offerListApi(body)

    suspend fun OfferRejectRequest(body: RequestBodies.OfferRejectRequest) =apiService.offerRejectApi(body)

    suspend fun OfferAcceptRequest(body: RequestBodies.OfferAcceptedRequest)= apiService.offerAcceptApi(body)

}