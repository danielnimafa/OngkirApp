package com.danielnimafa.android.appongkir.utils.networking

import com.danielnimafa.android.appongkir.model.response.Cities.CitiesModel
import com.danielnimafa.android.appongkir.model.response.Cost.CostModel
import com.danielnimafa.android.appongkir.model.response.Province.ProvinceModel
import com.danielnimafa.android.appongkir.utils.Const
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ServiceApi {

    @GET(Const.province)
    fun provinceData(): Observable<Response<ProvinceModel>>

    @GET(Const.city)
    fun cityData(): Observable<Response<CitiesModel>>

    @Multipart
    @POST(Const.cost)
    fun costData(@Part("origin") originID: RequestBody,
                 @Part("destination") destination: RequestBody,
                 @Part("weight") weight: RequestBody,
                 @Part("courier") courier: RequestBody): Observable<Response<CostModel>>

    /*@Multipart
    @POST("api/login")
    fun login(@Part("User[username]") username: RequestBody,
              @Part("User[password]") password: RequestBody): Observable<Response<ResponseBody>>

    @POST("api/logout")
    fun logout(): Observable<Response<ResponseBody>>*/

    /*@Multipart
    @POST("data/submit")
    fun submitData(@Part("Data[taskId]") taskId: RequestBody,
                       @Part("Data[name]") signName: RequestBody,
                       @Part("Data[codes]") palletCodes: RequestBody,
                       @Part picture: MultipartBody.Part): Observable<Response<CommonBody>>*/
}