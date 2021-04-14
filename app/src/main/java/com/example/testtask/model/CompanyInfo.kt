package com.example.testtask.model

import com.example.testtask.network.RetrofitServices

data class CompanyInfo(
    val id: String,
    val name: String,
    val img: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val www: String,
    val phone: String
){
    fun getRealImgPath() = (RetrofitServices.IMAGE_URL + img)
}
