package com.example.hackeruapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.secondassignment.Item
import com.example.secondassignment.R

object NotificationsManager {
    val CHANNEL_ID = "CHANNEL_ID"

    fun createNotificationChannel(context: Context){
        val name = "Notification Channel"
        val descriptionText = "Notification Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = descriptionText
        val notficationManager: NotificationManager =  context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notficationManager.createNotificationChannel(channel)
    }

    fun display(context: Context, item: Item){
        createNotificationChannel(context)
        val builder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("New Item")
                .setSmallIcon(R.drawable.camera)
                .setContentText("Hello! Item -${item.title}- has been added to your list")
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(1, builder.build())
    }
}