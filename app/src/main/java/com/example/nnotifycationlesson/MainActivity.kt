package com.example.nnotifycationlesson

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnNotify)
        val btn2 = findViewById<Button>(R.id.btnSecond)

        btn2.setOnClickListener {

        }

        btn.setOnClickListener {
            createNotify()
        }
    }

    private fun createNotify(){

        val notifyIntent = Intent(this, MainActivity::class.java).apply{
            flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0)
        val notifyChanel: NotificationChannel =
            NotificationChannel("1", "title", NotificationManager.IMPORTANCE_DEFAULT)
        val manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(notifyChanel)

        val notifyFirst = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Уведомление")
            .setContentText("Первое уведомление")
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.InboxStyle()
                .addLine("Первый")
                .addLine("Второй")
                .addLine("Третий")
            )


        with(NotificationManagerCompat.from(this)){
            notify(1, notifyFirst.build())
        }
    }

}