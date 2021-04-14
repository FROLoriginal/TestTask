package com.example.testtask.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testtask.MainActivityPresenter
import com.example.testtask.R
import com.example.testtask.model.CompanyInfo
import com.example.testtask.toast

class CompanyInfoFragment(
    private val companyInfo: CompanyInfo
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.company_info_fragment, container, false)

        val img: ImageView = view.findViewById(R.id.companyImageView)
        val name: TextView = view.findViewById(R.id.companysName)
        val description: TextView = view.findViewById(R.id.description)

        name.text = companyInfo.name
        description.text = companyInfo.description
        MainActivityPresenter.fillImageViewViaLoad(img, companyInfo.getRealImgPath())

        val location: Button = view.findViewById(R.id.location)
        val phone: Button = view.findViewById(R.id.phone)
        val link: Button = view.findViewById(R.id.link)

        if (companyInfo.www.isEmpty()){
            link.visibility = View.GONE
        }else link.setOnClickListener(browserListener)

        if (companyInfo.phone.isEmpty()){
            phone.visibility = View.GONE
        }else phone.setOnClickListener(phoneListener)

        location.setOnClickListener(locationListener)

        return view
    }

    private val locationListener = View.OnClickListener {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:${companyInfo.lat},${companyInfo.lon}")
        )
        if (intent.isDeviceMeetRequirement()) {
            startActivity(intent)
        } else requireContext().toast(resources.getString(R.string.geo_error))
    }

    private val phoneListener = View.OnClickListener {
        val intent = Intent(
            Intent.ACTION_DIAL,
            Uri.parse("tel:${companyInfo.phone}")
        )
        if (intent.isDeviceMeetRequirement()) {
            startActivity(intent)
        } else requireContext().toast(resources.getString(R.string.phone_error))
    }

    private val browserListener = View.OnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(companyInfo.www))
        if (intent.isDeviceMeetRequirement()) {
            startActivity(intent)
        } else requireContext().toast(resources.getString(R.string.browser_error))
    }

    private fun Intent.isDeviceMeetRequirement(): Boolean {
        return this.resolveActivity(requireContext().packageManager) != null
    }

    companion object {
        const val TAG = "CompanyInfoFragment"
    }
}
