package ua.nure.kushnir.clientportrait.facade

import okhttp3.ResponseBody
import retrofit2.http.*
import ua.nure.kushnir.clientportrait.model.dto.SignInUserDto
import ua.nure.kushnir.clientportrait.model.dto.SignUpUserDto
import ua.nure.kushnir.clientportrait.model.entity.Profile
import ua.nure.kushnir.clientportrait.model.entity.UserToken

interface UserFacade {

    @Headers("Content-Type:application/json")
    @POST("api/users/client")
    fun signUp(@Body signUpUserDto: SignUpUserDto): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("api/auth")
    fun signIn(@Body signInUserDto: SignInUserDto): retrofit2.Call<UserToken>

    @GET("/api/profiles/me")
    fun profile(@Header("x-auth-token") auth: String?): retrofit2.Call<Profile>

}