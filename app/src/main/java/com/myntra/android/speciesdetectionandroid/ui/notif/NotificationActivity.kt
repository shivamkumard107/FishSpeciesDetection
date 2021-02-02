package com.myntra.android.speciesdetectionandroid.ui.notif

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myntra.android.speciesdetectionandroid.R
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        rvNotifs.layoutManager = LinearLayoutManager(this)
        rvNotifs.adapter = NotificationAdapter(this, ArrayList())

    }
}