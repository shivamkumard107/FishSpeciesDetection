package com.myntra.android.speciesdetectionandroid.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.myntra.android.speciesdetectionandroid.R
import com.myntra.android.speciesdetectionandroid.auth.LoginActivity
import com.myntra.android.speciesdetectionandroid.ui.catcharea.HeatMapDemoFragment
import com.myntra.android.speciesdetectionandroid.ui.home.HomeFragment
import com.myntra.android.speciesdetectionandroid.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
    }
}