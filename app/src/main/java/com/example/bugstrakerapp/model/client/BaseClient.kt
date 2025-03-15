package com.example.bugstrakerapp.model.client

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseClient<T>() {

    abstract fun getClient(): T

    protected inline fun <reified T> buildClient(
        baseUrl: String,
        connectTimeoutInMillis: Long = 5000L,
        readTimeoutInMillis: Long = 10000L,
        logging: Boolean = true,
        interceptors: List<Interceptor> = emptyList(),

        ): T {
        val builder = OkHttpClient.Builder()
            .connectTimeout(connectTimeoutInMillis, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeoutInMillis, TimeUnit.MILLISECONDS)



        for (interceptor in interceptors) {
            builder.addInterceptor(interceptor)
        }


        val httpClient = builder.build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(httpClient)
            .build()

        return retrofit.create<T>(T::class.java)
    }
}


