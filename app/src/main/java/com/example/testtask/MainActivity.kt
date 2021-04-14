package com.example.testtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testtask.fragments.CompanyInfoFragment
import com.example.testtask.fragments.NoConnectionFragment
import com.example.testtask.fragments.RecyclerViewFragment
import com.example.testtask.model.CompanyInfo
import com.example.testtask.model.CompanyPreview


class MainActivity : AppCompatActivity(), ICompaniesPreviewView {

    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = resources.getString(R.string.download)
        presenter = MainActivityPresenter(this)
        presenter.requestListOfCompanies()

    }

    override fun onError() {
        //Checking to avoid double-triple-... adding of the same Fragment
        if (!isFragmentsAdded(NoConnectionFragment.TAG)) {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.fragmentContainer,
                    NoConnectionFragment(presenter),
                    NoConnectionFragment.TAG
                )
                commit()
            }
            title = resources.getString(R.string.error)
        }
    }
    override fun onDataReceived(list: List<CompanyPreview>) {
        //Checking to avoid double-triple-... adding of the same Fragment
        if (!isFragmentsAdded(RecyclerViewFragment.TAG)) {
            supportFragmentManager.beginTransaction().run {
                replace(
                    R.id.fragmentContainer,
                    RecyclerViewFragment(presenter, list),
                    RecyclerViewFragment.TAG
                )
                commit()
            }
            title = resources.getString(R.string.investment)
        }
    }
    override fun onDataReceived(company: CompanyInfo) {
        val tag = CompanyInfoFragment.TAG
        //Checking to avoid double-triple-... adding of the same Fragment
        if (!isFragmentsAdded(tag)) {
            supportFragmentManager.beginTransaction().run {
                replace(R.id.fragmentContainer, CompanyInfoFragment(company), tag)
                addToBackStack(tag)
                commit()
            }
            title = resources.getString(R.string.information)

        }
    }
    // We are checking if the same Fragments is already been added
    private fun isFragmentsAdded(tag: String): Boolean {
        val fragments = supportFragmentManager.fragments
        if (fragments.isNotEmpty()) {
            if (fragments[0].tag == tag) return true
        }
        return false
    }

}
