package com.example.testtask.model

import com.example.testtask.network.RetrofitServices

data class CompanyPreview(
    val id: String,
    val name: String,
    val img: String
) {
    fun getRealImgPath() = (RetrofitServices.IMAGE_URL + img)
}
