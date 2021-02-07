package com.myntra.android.speciesdetectionandroid.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.myntra.android.speciesdetectionandroid.R
import kotlinx.android.synthetic.main.fragment_catch_history.view.*

class CatchHistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catch_history, container, false)
        view.rvCatchHistory.layoutManager = LinearLayoutManager(context)
        view.rvCatchHistory.adapter = CatchHistoryAdapter(context!!)
        return view
    }
}