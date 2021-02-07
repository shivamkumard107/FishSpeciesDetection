package com.myntra.android.speciesdetectionandroid.ui.notif

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myntra.android.speciesdetectionandroid.R
import com.myntra.android.speciesdetectionandroid.ui.MainActivity
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        /**
         * holder.title.text = list[position].title
        holder.desc.text = list[position].desc
        holder.timestamp.text = list[position].timestamp
        when(list[position].notifType){
        "warning" -> Glide.with(context).load(R.drawable.warning).into(holder.notifType)
        "alert" -> Glide.with(context).load(R.drawable.alert).into(holder.notifType)
        "good" -> Glide.with(context).load(R.drawable.heart).into(holder.notifType)
        }
         */
        val list = ArrayList<NotificationModel>()
        list.add(
            NotificationModel(
                "Daily Updates",
                "TN govt committed to protect traditional fishing rights of fishermen",
                "13:00, today",
                "warning"
            )
        )
        list.add(
            NotificationModel(
                "Don't go fishing",
                "We have a bad weather at 02:00. Please don't go fishing",
                "11:00, 27 Jan",
                "warning"
            )
        )
        list.add(
            NotificationModel(
                "A Good News",
                "QR code-based biometric ID cards for coastal fishermen",
                "15:30, 15 Jan",
                "good"
            )
        )
        list.add(
            NotificationModel(
                "Don't go fishing",
                "We have a bad weather at 02:00. Please don't go fishing",
                "13:00, 11 Nov",
                "warning"
            )
        )

        val handler = Handler()
        handler.postDelayed({
            pb.visibility = View.GONE;
            rvNotifs.visibility = View.VISIBLE;
            rvNotifs.layoutManager = LinearLayoutManager(this)
            rvNotifs.adapter = NotificationAdapter(this, list)
        }, 1000)

    }
}