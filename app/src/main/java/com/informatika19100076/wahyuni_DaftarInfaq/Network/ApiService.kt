package com.informatika19100076.wahyuni_DaftarInfaq.Network

import com.informatika19100061.DIKKYRAHMADANA.daftarinfaq.adapter.ListContent
import com.informatika19100061.DIKKYRAHMADANA.daftarinfaq.model.responactioninfaq
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getBarang(): Call<ListContent.responactioninfaq>

    @FormUrlEncoded
    @POST("create.php")
    fun insertBarang(
        @Field("nama_orang") nama_orang: String?,
        @Field("tanggal")tanggal: String?,
        @Field("jumlah_uang")jumlah_uang: String?,
        @Field("keterangan")keterangan: String?,
    ): Call<responactioninfaq>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
        @Field("id") id: String?,
        @Field("nama_orang") nama_orang: String?,
        @Field("tanggal")tanggal: String?,
        @Field("jumlah_uang")jumlah_uang: String?,
        @Field("keterangan")keterangan: String?,

        @FormUrlEncoded
        @POST("delete.php")
        fun deleteBarang(
    @Field("id") id: String?
    ): Call<responactioninfaq>

    @FormUrlEncoded
    @POST("login.php")
    fun logIn(
        @Field("username") username : String?,
        @Field("password") password : String?
    ):Call<responactioninfaq>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") username : String?,
        @Field("password") password : String?
    ):Call<responactioninfaq>

    fun updateBarang(id: String, nama_orang: String, tanggal: String): Call<responactioninfaq>
