package com.example.bugstrakerapp.model.api

import com.example.bugstrakerapp.model.data.BugsListData
import retrofit2.http.GET

interface BugsListApi{
    @GET("поменять")
    fun getBugList():BugsListData
}