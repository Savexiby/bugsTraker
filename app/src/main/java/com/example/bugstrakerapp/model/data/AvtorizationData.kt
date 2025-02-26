package com.example.bugstrakerapp.model.data

data class AvtorizationData(
    val status:String,
    val message:String,
    val data:Data?
)

data class Data(
    val token:String
)