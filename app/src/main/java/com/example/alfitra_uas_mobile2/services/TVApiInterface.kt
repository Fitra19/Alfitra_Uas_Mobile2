package com.example.alfitra_uas_mobile2.services

import com.example.alfitra_uas_mobile2.models.TVResponse
import retrofit2.Call
import retrofit2.http.GET

interface TVApiInterface {
    @GET("/3/tv/popular?api_key=90815fba49dc64faccc22cbef96a0934")
    fun getTVList(): Call<TVResponse>
}