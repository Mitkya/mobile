package ua.nure.kushnir.clientportrait.facade

import retrofit2.http.GET
import retrofit2.http.Header
import ua.nure.kushnir.clientportrait.model.entity.PopularProduct

interface StatisticFacade {

    @GET("/api/statistics/popular")
    fun getAllPopularProducts(@Header("x-auth-token") auth: String?): retrofit2.Call<List<PopularProduct>>

}