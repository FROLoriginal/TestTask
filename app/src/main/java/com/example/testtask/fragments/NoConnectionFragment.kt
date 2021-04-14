package com.example.testtask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.testtask.MainActivityPresenter
import com.example.testtask.R

class NoConnectionFragment(
    private val presenter: MainActivityPresenter
) : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.no_connection_fragment, container, false)
        view.findViewById<Button>(R.id.reloadButton).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        presenter.requestListOfCompanies()
    }

    companion object{
        const val TAG = "NoConnectionFragment"
    }
}
