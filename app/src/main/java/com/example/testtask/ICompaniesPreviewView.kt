package com.example.testtask

import com.example.testtask.model.CompanyInfo
import com.example.testtask.model.CompanyPreview

interface ICompaniesPreviewView {

    fun onError()
    fun onDataReceived(list: List<CompanyPreview>)
    fun onDataReceived(company: CompanyInfo)

}
