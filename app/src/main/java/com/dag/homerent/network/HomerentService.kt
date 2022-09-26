package com.dag.homerent.network

import com.dag.homerent.network.commonresponse.ActivityBodyResponse
import com.dag.homerent.ui.onboard.login.data.request.LoginRequest
import com.dag.homerent.ui.onboard.login.data.response.LoginResponse
import com.dag.homerent.ui.onboard.register.data.request.RegisterUserModel
import com.dag.homerent.ui.onboard.register.data.response.RegisterResult
import retrofit2.http.*

interface HomerentService {
    @POST
    fun genericPostRequest(@Url url: String?, @Body body:Any? = {}): BaseResult<BaseResponse?>

    @GET
    fun genericGetRequest(@Url url: String?):BaseResult<BaseResponse>

    @PUT
    fun genericPutRequest(@Url url: String?, @Body body:Any? = {}):BaseResult<BaseResponse?>

    @DELETE
    fun genericDeleteRequest(@Url url: String?): BaseResult<BaseResponse>

    @POST("auth/register/{userType}")
    suspend fun register(
        @Body registerUserModel: RegisterUserModel,
        @Path("userType") userType: String
    ): BaseResult<ApiResponse<RegisterResult>>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): BaseResult<ApiResponse<LoginResponse>>


    @GET("formcontent/name/{name}")
    suspend fun getActivityBody(@Path("name") name: String): BaseResult<ApiResponse<ActivityBodyResponse>>
}