package com.example.testtask

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testtask.model.CompanyInfo
import com.example.testtask.model.CompanyPreview
import com.example.testtask.network.RetrofitDataProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(private val mInterface: ICompaniesPreviewView) {

    private val services = RetrofitDataProvider().getRetrofitService()

    fun requestListOfCompanies() {

        services.getCompaniesList().enqueue(object : Callback<List<CompanyPreview>> {

            override fun onFailure(call: Call<List<CompanyPreview>>, t: Throwable) {
                mInterface.onError()
            }

            override fun onResponse(
                call: Call<List<CompanyPreview>>,
                response: Response<List<CompanyPreview>>
            ) {

                response.body()?.let { mInterface.onDataReceived(it) }

            }
        })
    }

    fun requestForCompany(id: String){
        services.getCompanyInfo(id).enqueue(object : Callback<List<CompanyInfo>> {
            override fun onFailure(call: Call<List<CompanyInfo>>, t: Throwable) {
                mInterface.onError()
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<CompanyInfo>>, response: Response<List<CompanyInfo>>) {
                response.body()?.let { mInterface.onDataReceived(it[0]) }
            }
        })
    }

    companion object {
        fun fillImageViewViaLoad(imageView: ImageView, url: String) {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView)
        }
    }
}
