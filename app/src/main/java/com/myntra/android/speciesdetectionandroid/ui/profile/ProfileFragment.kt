package com.myntra.android.speciesdetectionandroid.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.myntra.android.speciesdetectionandroid.R
import com.myntra.android.speciesdetectionandroid.auth.LoginActivity
import com.myntra.android.speciesdetectionandroid.auth.LoginOption
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    var aboutus: MaterialCardView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        v.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context!!, LoginActivity::class.java))
            activity!!.finish()
        }
        return v
    }
}