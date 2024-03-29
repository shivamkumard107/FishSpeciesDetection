package com.myntra.android.speciesdetectionandroid.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.myntra.android.speciesdetectionandroid.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cv1.setOnClickListener { startActivity(Intent(context, CompassActivity::class.java)) }
        cv2.setOnClickListener { startActivity(Intent(context, WeatherActivity::class.java)) }
        cv3.setOnClickListener { changeLanguage() }
        cv4.setOnClickListener {
            val launchIntent: Intent? =
                activity!!.getPackageManager().getLaunchIntentForPackage("org.tensorflow.lite.examples.detection")
            if (launchIntent != null) {
                startActivity(launchIntent) //null pointer check in case package name was not found
            }
        }
    }

    private fun changeLanguage() {
        val items = arrayOf("English", "Malay", "Indonesian", "Vietnamese", "Lao", "Burmese")
        var alertDialogBuilder = AlertDialog.Builder(context!!)
        alertDialogBuilder.setTitle("Choose Language...")
        alertDialogBuilder.setSingleChoiceItems(items, -1,
            DialogInterface.OnClickListener { dialog, which ->
                if (which == 0) {
                    setLocale("en")
                    activity!!.recreate()
                } else if (which == 2) {
                    setLocale("su")
                    activity!!.recreate()
                } else if (which == 3) {
                    setLocale("vi")
                    activity!!.recreate()
                }
                dialog.dismiss()
            })
        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context!!.resources.updateConfiguration(config, context!!.resources.displayMetrics)
    }
}