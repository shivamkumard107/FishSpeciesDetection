package com.myntra.android.speciesdetectionandroid.ui

//import cc.cloudist.acplibrary.R
import android.app.AlertDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.myntra.android.speciesdetectionandroid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_camera.*
import kotlinx.android.synthetic.main.fish_catched_information.*


class CameraActivity : AppCompatActivity() {

    fun getNameFromURI(uri: Uri?): String? {
        val c: Cursor? = contentResolver.query(uri!!, null, null, null, null)
        c?.moveToFirst()
        return c?.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }


    /**
     * Name of the species -
     * 1) cantherhines_pardalis.jpg , result_cantherhines_pardalis.png
     * 2) caranx_melampygus.jpg , result_caranx_melampygus.png
     * 3) lutjanus_sebae.jpg , result_lutjanus_sebae.png
     * 4) cantherhines_pardalis_two.jpg , result_cantherhines_pardalis_two.png
     * 5) caranx_melampygus_two.jpg , result_caranx_melampygus_two.png
     * 6) pseudanthias_squamipinnis.jpg , result_pseudanthias_squamipinnis.png
     * 7) halichoeres_melanurus.jpg , result_halichoeres_melanurus.png
     * 8) oxymonacanthus_longirostris.jpg , result_oxymonacanthus_longirostris.png
     * 9) oxymonacanthus_longirostris_two.jpg, result_oxymonacanthus_longirostris_two.png
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val uri = Uri.parse(intent.getStringExtra("uri"))
        Picasso.get().load(uri).into(fishImageView)


        val dialog = ACProgressFlower.Builder(this)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .text("Finding")
            .fadeColor(Color.DKGRAY).build()
        dialog.show()

        var fileName = getNameFromURI(uri)
        fileName = fileName!!.substring(0, fileName.length - 4)

        Handler().postDelayed({
            Toast.makeText(this, fileName, Toast.LENGTH_LONG).show()
            val res = resources.getIdentifier(
                "com.myntra.android.speciesdetectionandroid:drawable/result_" + fileName,
                null,
                null
            )
            Log.i("------", res.toString())
            Log.i("------ i ", (R.drawable.cantherhines_pardalis).toString())
            dialog.dismiss()
            fishImageView.setImageResource(res)
            linearLayout.visibility = View.VISIBLE
        }, 5000)


        saveImageButton.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setCancelable(true)
            val view = getLayoutInflater().inflate(R.layout.catchweight_alert, null)
            dialog.setView(view)
            val builder = dialog.create()
            view.findViewById<TextView>(R.id.save).setOnClickListener {
                val weight = view.findViewById<EditText>(R.id.editTextWeight).text
                if(weight.isEmpty()){
                    Toast.makeText(this,"Enter weight",Toast.LENGTH_SHORT).show()
                }
                else{
                    builder.dismiss()
                    fishNameTextView.text = fileName
                    fishWeightTextView.text = weight
                    fishImageViewFinal.setImageResource(resources.getIdentifier(
                        "com.myntra.android.speciesdetectionandroid:drawable/" + fileName,
                        null,
                        null
                    ))
                    frameLayout.visibility = View.GONE
                    fishInformationFrameLayout.visibility = View.VISIBLE
                }
            }
            builder.show()
        }

        helpButton.setOnClickListener {

            val dialog = AlertDialog.Builder(this)
            dialog.setCancelable(true)
            val view = getLayoutInflater().inflate(R.layout.helpus_alert, null)
            dialog.setView(view)
            val builder = dialog.create()
            view.findViewById<TextView>(R.id.buttonHelpUs).setOnClickListener {

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("shivam.kumard107@gmail.com\"")
                intent.setClassName(
                    "com.google.android.gm",
                    "com.google.android.gm.ComposeActivityGmail"
                )
                intent.putExtra(Intent.EXTRA_SUBJECT, "COMPLAINT")
                intent.putExtra(Intent.EXTRA_TEXT, "Write your query here")

                startActivity(intent)

            }
            builder.show()
        }
    }
}