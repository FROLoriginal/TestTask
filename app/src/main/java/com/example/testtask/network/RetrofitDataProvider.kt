package com.example.testtask.network

class RetrofitDataProvider {
    private val BASE_URL = "https://lifehack.studio"

    fun getRetrofitService(): RetrofitServices {
        return Client.getInstance(BASE_URL).create(RetrofitServices::class.java)
    }


}
