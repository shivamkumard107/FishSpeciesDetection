package com.myntra.android.speciesdetectionandroid.ui

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.myntra.android.speciesdetectionandroid.R
import com.myntra.android.speciesdetectionandroid.auth.LoginActivity
import com.myntra.android.speciesdetectionandroid.ui.catcharea.DiseaseCountSheet
import com.myntra.android.speciesdetectionandroid.ui.catcharea.HeatMapDemoFragment
import com.myntra.android.speciesdetectionandroid.ui.home.HomeFragment
import com.myntra.android.speciesdetectionandroid.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), DiseaseCountSheet.BottomSheetListener {
    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_catch -> selectedFragment = HeatMapDemoFragment()
                R.id.nav_camera -> selectedFragment = HomeFragment()
                R.id.nav_catch_history -> selectedFragment = HomeFragment()
                R.id.nav_profile -> selectedFragment = ProfileFragment()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment!!
            ).commit()
            true
        }

    private fun getFileExtension(uri: Uri): String? {
        val contentResolver: ContentResolver = this.getContentResolver()
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 12345 && resultCode == RESULT_OK
            && data != null && data.getData() != null){
            val imageUri = data.getData();
            val intent = Intent(this,CameraActivity::class.java)
            intent.putExtra("uri",imageUri.toString())
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        val pref = getPreferences(MODE_PRIVATE)
        val id = pref.getString("user", null)
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                FirebaseAuth.getInstance().currentUser!!.phoneNumber,
                Toast.LENGTH_SHORT
            ).show()
        }
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                HomeFragment()
            ).commit()
        }

        fab.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 12345)
        }
    }

    override fun onButtonClicked(text: String?) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}