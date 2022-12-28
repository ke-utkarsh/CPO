package ymsli.com.cpo.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import ymsli.com.cpo.data.model.*

interface API {

    @GET("api/account/roles")
    suspend fun roleApi(): Response<RoleResponse>

    @POST("api/account/roles/users")
    suspend fun userApi(@Body body: RequestBodies.UserRequestBody): Response<UserResponse>

    @POST("api/SignIn")
    suspend fun loginApi(@Body body: RequestBodies.LoginRequestBody): Response<LoginResponse>

    @GET("api/Account/user/Profile")
    suspend fun loginApi(): Response<ProfileResponse>

    @POST("api/account/user/linkWallet")
    suspend fun walletLinkApi(@Body walletLinkRequestBody: RequestBodies.WalletLinkRequestBody): Response<WalletLinkResponse>

    @POST("api/VehicleNFT/Vehicles/VehicleDetail")
    suspend fun vehicleInfoApi(@Body body: RequestBodies.VehicleInformationBody): Response<VehicleInformationResponse>

    @GET("api/VehicleNFT/Vehicles/PendingNFT")
    suspend fun activeWarrantiesApi(): Response<ActiveWarrantiesResponse>

    @Multipart
    @POST("api/ImageUpload/saveImage/pdi")
    suspend fun postPdiImageData(
        @Part("vehicleMasterId") vehicleMasterId: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<PostImageResponse>


    @Multipart
    @POST("api/ImageUpload/saveImage/vehicle")
    suspend fun postVehicleImageData(
        @Part("vehicleMasterId") vehicleMasterId: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<PostImageResponse>

    @POST("api/VehicleNFT/Vehicles/SubmitNFTData")
    suspend fun submitNFTDataApi(@Body body: RequestBodies.SubmitNftDataBody): Response<SubmitNFTDataResponse>


    @POST("api/VehicleNFT/Vehicles/user-nfts")
    suspend fun customerNftDetailsApi(@Body body: RequestBodies.CustomerNftDetailRequestBody): Response<CustomerNftDetailResponse>

    @POST("api/VehicleNFT/Vehicles/mint-nft")
    suspend fun mintNftSubmitApi(@Body body: RequestBodies.MintNftRequestBody): Response<MintNftResponse>

    @GET("api/Maintenance/appointment/get-dates")
    suspend fun getDatesApi(): Response<SelectDateResponse>

    @GET("api/Maintenance/appointment/get-times")
    suspend fun getTimeApi(): Response<SelectTimeResponse>

    @GET("api/Maintenance/appointment/get-dealers")
    suspend fun dealersNearApi(): Response<DealerNearResponse>

    @GET("api/Maintenance/appointment/new-id")
    suspend fun getNewUidApi(): Response<GetNewUidResponse>

    @Multipart
    @POST("api/Maintenance/appointment/upload-concern-images")
    suspend fun postConcernedIssueImageData(
        @Part("AppointmentId") appointmentId: RequestBody,
        @Part("AppointmentLineId") appointmentLineId: RequestBody,
        @Part("Description") issueDetail: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<PostImageResponse>


    @Multipart
    @POST("api/Maintenance/appointment/upload-tripandfuel-images")
    suspend fun postTripMeterImageData(
        @Part("AppointmentId") appointmentId: RequestBody,
        @Part("AppointmentLineId") appointmentLineId: RequestBody,
        @Part("Description") issueDetail: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<PostImageResponse>


    @POST("api/Maintenance/appointment/submit-appointment")
    suspend fun submitAppointmentApi(@Body body: RequestBodies.SubmitAppointRequestBody): Response<SubmitAppointmentResponse>


    @POST("api/Maintenance/appointment/get-appointments")
    suspend fun getAppointmentApi(@Body body: RequestBodies.GetAppointmentRequestBody): Response<GetAppointmentResponse>


    @GET("api/Maintenance/service/get-service-items")
    suspend fun getServiceItemApi(): Response<ServiceItemResponse>

    @Multipart
    @POST("api/Maintenance/service/upload-maintenance-images")
    suspend fun postMaintenanceImageData(
        @Part("AppointmentId") appointmentId: RequestBody,
        @Part("MaintenanceId") maintenanceId: RequestBody,
        @Part("MaintenanceItemId") appointmentItemId: RequestBody,
        @Part("MaintenanceLineId") appointmentLineId: RequestBody,
        @Part("Description") description: RequestBody,
        @Part("VehicleId") vehicleId: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<PostImageResponse>

    @POST("api/Maintenance/service/get-maintenance-detail")
    suspend fun getMaintenanceDetailApi(@Body body: RequestBodies.GetMaintenanceRequestBody): Response<GetMaintenanceItems>

    @POST("api/Maintenance/appointment/get-appointment-detail")
    suspend fun getServiceSubmitDetails(@Body body: RequestBodies.GetAppointmentDetail): Response<GetBeforeImagesModel>

    @Multipart
    @POST("api/Maintenance/appointment/upload-maintenance-concern-images")
    suspend fun afterImageData(
        @Part("AppointmentId") appointmentId: RequestBody,
        @Part("AppointmentLineId") appointmentLineId: RequestBody,
        @Part("Description") issueDetail: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<PostImageResponse>

    @POST("api/Maintenance/service/save-maintenance")
    suspend fun saveNextMaintenanceApi(@Body body: RequestBodies.SaveMaintenanceRequestBody): Response<GetNewUidResponse>

    @POST("api/Maintenance/service/get-maintenance-list")
    suspend fun getMaintenanceListApi(@Body body: RequestBodies.GetAppointmentRequestBody): Response<GetMaintenanceListResponse>

    @POST("api/Maintenance/service/submit-maintenance")
    suspend fun submitServiceCompleteRequest(@Body body: RequestBodies.SubmitCompleteRequest): Response<SubmitCompleteResponseModel>

    @POST("api/Maintenance/appraisal/get-appraisal-data")
    suspend fun appraisalHistoryApi(@Body body: RequestBodies.VehicleInformationBody): Response<AppraisalHistoryResponse>

    @GET("api/VehicleNFT/Vehicles/user-nfts-stats")
    suspend fun getNftStats(): Response<GetNftStatsResponse>

    @POST("api/VehicleNFT/Vehicles/offer-request")
    suspend fun submitOffer(@Body body: RequestBodies.SubmitOfferPriceRequest) : Response<GetNewUidResponse>

    @POST("api/VehicleNFT/Vehicles/offer-list")
    suspend fun offerListApi(@Body body: RequestBodies.VehicleInformationBody) : Response<OfferListResponse>

    @POST("api/VehicleNFT/Vehicles/reject-request")
    suspend fun offerRejectApi(@Body body: RequestBodies.OfferRejectRequest) : Response<GetNewUidResponse>

    @POST("api/VehicleNFT/Vehicles/accept-transfer-request")
    suspend fun offerAcceptApi(@Body body: RequestBodies.OfferAcceptedRequest) : Response<AcceptApiResponse>

}