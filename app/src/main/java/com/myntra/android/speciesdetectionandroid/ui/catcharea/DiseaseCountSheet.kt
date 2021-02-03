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
import com.myntra.android.speciesdetectionandroid.ui.CameraActivity.Companion.bool
import kotlinx.android.synthetic.main.disease_bottom_sheet.*
import java.util.*

class DiseaseCountSheet : BottomSheetDialogFragment() {
    private val list1 = arrayListOf<Int>(R.drawable.cantherhines_pardalis, R.drawable.oxymonacanthus_longirostris, R.drawable.caranx_melampygus)
    private val list2 = arrayListOf<Int>(R.drawable.lutjanus_sebae,R.drawable.caranx_melampygus, R.drawable.pseudanthias_squamipinnis_5, R.drawable.oxymonacanthus_longirostris_25)
    private val list1Name = arrayListOf("cantherhines_pardalis", "oxymonacanthus_longirostris", "caranx_melampygus")
    private val list2Name = arrayListOf("lutjanus_sebae", "caranx_melampygus", "pseudanthias_squamipinnis", "oxymonacanthus_longirostris")
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
            if (boolean) {
                rvHeatmap.adapter = HeatmapRecycler(context!!, list1, list1Name)
                boolean = false
            }else{
                rvHeatmap.adapter = HeatmapRecycler(context!!, list2, list2Name)
                boolean = true
            }
        }, 1000)

        return v
    }

    companion object{
        var boolean = false
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