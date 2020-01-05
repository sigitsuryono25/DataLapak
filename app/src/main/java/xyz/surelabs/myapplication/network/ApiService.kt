package xyz.surelabs.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.surelabs.myapplication.network.datamodel.detailpelapak.ResponseDetailPelapak

interface ApiService {

    @GET("webservices/get_detail_lapak/{id}")
    fun getDetailLapak(@Path("id") id: String): Call<ResponseDetailPelapak>
}