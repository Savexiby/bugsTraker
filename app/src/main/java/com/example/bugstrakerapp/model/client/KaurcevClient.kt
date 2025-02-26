package com.example.bugstrakerapp.model.client

import com.example.bugstrakerapp.model.api.KaurcevApi

class KaurcevClient :BaseClient<KaurcevApi>(){
    override fun getClient(): KaurcevApi {
        return buildClient(baseUrl = "https://api.kaurcev.dev/")
    }
}