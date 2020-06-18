package ua.nure.kushnir.clientportrait.facade

import retrofit2.http.*
import ua.nure.kushnir.clientportrait.model.dto.BusinessesDto
import ua.nure.kushnir.clientportrait.model.entity.Business

interface BusinessFacade {

    @Headers("Content-Type:application/json")
    @POST("api/businesses/")
    fun createBusiness(
        @Body businessesDto: BusinessesDto,
        @Header("x-auth-token") auth: String?
    ): retrofit2.Call<Business>

    @GET("/api/businesses/")
    fun profile(@Header("x-auth-token") auth: String?): retrofit2.Call<List<Business>>

}