package com.myntra.android.speciesdetectionandroid.ui.catcharea

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.myntra.android.speciesdetectionandroid.R
import kotlinx.android.synthetic.main.disease_bottom_sheet.*
import java.util.*

class DiseaseCountSheet : BottomSheetDialogFragment() {
    private var mListener: BottomSheetListener? = null
    private var rc_mod: ArrayList<ReportedCases>? = null
    var d_count: TextView? = null
        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.disease_bottom_sheet, container, false)
        d_count = v.findViewById(R.id.disease_count)
        rc_mod = HeatMapDemoFragment.rc_mod
        d_count!!.setText(rc_mod!!.size.toString())
        val handler = Handler()
        handler.postDelayed({
            pb.visibility = View.GONE
            rvHeatmap.visibility = View.VISIBLE
            rvHeatmap.layoutManager = LinearLayoutManager(context)
            rvHeatmap.adapter = HeatmapRecycler(context!!)
        }, 1000)

        return v
    }

    interface BottomSheetListener {
        fun onButtonClicked(text: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            context as BottomSheetListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement BottomSheetListener"
            )
        }
    }
}