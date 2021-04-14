package com.example.testtask.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R

class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //@JvmField allows as do some micro-optimisation
    @JvmField
    val img: ImageView = view.findViewById(R.id.company_img)

    @JvmField
    val name: TextView = view.findViewById(R.id.company_name)

}
