package com.example.bugstrakerapp.model.client

import com.example.bugstrakerapp.model.api.BugsListApi

class BugsListClient : BaseClient<BugsListApi>(){
    override fun getClient(): BugsListApi {
        TODO("Not yet implemented")
        return buildClient(
            baseUrl = ""
        )
    }

}