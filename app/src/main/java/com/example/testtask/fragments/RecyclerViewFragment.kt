package com.example.testtask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.MainActivityPresenter
import com.example.testtask.R
import com.example.testtask.adapters.RecyclerViewAdapter
import com.example.testtask.model.CompanyPreview

class RecyclerViewFragment(
    private val presenter: MainActivityPresenter,
    private val list: List<CompanyPreview>
) : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.recycler_view_fragment, container, false)
        val manager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById(R.id.complaniesList)
        recyclerView.layoutManager = manager
        recyclerView.adapter = RecyclerViewAdapter(list, this)
        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onItemClicked(position: Int) {
        presenter.requestForCompany(list[position].id)
    }

    companion object {
        const val TAG = "RecyclerViewFragment"
    }


}
