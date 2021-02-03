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
            NotificationModel("Don't go fishing", "We have a bad weather at 07:00. Please don't go fishing", "13:00, today","warning")
        )
        list.add(
            NotificationModel("Don't go fishing", "We have a bad weather at 02:00. Please don't go fishing", "11:00, 27 Jan","warning")
        )
        list.add(
            NotificationModel("A Good News", "Government give you an opportunity about a free accurance program", "15:30, 15 Jan","good")
        )
        list.add(
            NotificationModel("Don't go fishing", "We have a bad weather at 02:00. Please don't go fishing", "13:00, 11 Nov","warning")
        )
        rvNotifs.layoutManager = LinearLayoutManager(this)
        rvNotifs.adapter = NotificationAdapter(this, list)

    }
}