package com.example.bugstrakerapp.model.api

import com.example.bugstrakerapp.model.data.AvtorizationData
import com.example.bugstrakerapp.model.data.LoginPasswordJson
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface KaurcevApi {

    @POST("/v1/signin")
    @Headers("Content-kaurcev: dev")
    suspend fun avtorization(@Body loginPasswordJson: LoginPasswordJson): AvtorizationData
}