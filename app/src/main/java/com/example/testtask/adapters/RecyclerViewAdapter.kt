package com.example.testtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.MainActivityPresenter
import com.example.testtask.R
import com.example.testtask.model.CompanyPreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecyclerViewAdapter(
    private val companiesPreview: List<CompanyPreview>,
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CompanyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.company_card_view,
            parent,
            false
        )

        return CompanyViewHolder(view).also { vh ->
            view.setOnClickListener {
                clickListener.onItemClicked(vh.adapterPosition)
                println(vh.adapterPosition)
            }
        }

    }


    override fun onBindViewHolder(holderCompany: CompanyViewHolder, position: Int) {
        val companyPreview = companiesPreview[position]
        holderCompany.name.text = companyPreview.name
        //Thread to async-load avatars
        GlobalScope.launch(Dispatchers.Main) {
            MainActivityPresenter.fillImageViewViaLoad(
                holderCompany.img,
                companyPreview.getRealImgPath()
            )
        }
    }

    override fun getItemCount() = companiesPreview.size

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

}
