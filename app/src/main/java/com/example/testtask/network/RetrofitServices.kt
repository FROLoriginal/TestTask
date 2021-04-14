package com.example.testtask.network

import com.example.testtask.model.CompanyInfo
import com.example.testtask.model.CompanyPreview
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET(COMPANIES_PREVIEW_PATH) //We get info from BaseUrl ("https://lifehack.studio/test_task/test.php")
    fun getCompaniesList(): Call<List<CompanyPreview>>

    @GET(COMPANY_INFO_PATH)
    fun getCompanyInfo(@Query("id") query: String): Call<List<CompanyInfo>>


    companion object {
        private const val TEST_MODULE = "test_task/"
        private const val COMPANIES_PREVIEW_PATH = "${TEST_MODULE}test.php"
        private const val COMPANY_INFO_PATH = "${TEST_MODULE}test.php"
        const val IMAGE_URL = "https://lifehack.studio/$TEST_MODULE"

    }

}
