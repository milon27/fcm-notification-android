package com.m27lab.fcmtestwithnodejs.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.m27lab.fcmtestwithnodejs.R
import com.m27lab.fcmtestwithnodejs.api.RetrofitBuilder
import com.m27lab.fcmtestwithnodejs.views.activity.NotificationActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val TAG = "FirebaseMessagingService"
    override fun onMessageReceived(rm: RemoteMessage) {

        //Log.d(TAG, "Notification Title: " + rm.notification?.title);
       // Log.d(TAG, "Notification Message: " + rm.notification?.body );

        if (rm.data.isNotEmpty()) {//we have data
            Log.d(TAG, "Message data account: " +rm.data["title"]);
            Log.d(TAG, "Message data balance: " +rm.data["body"]);
            Log.d(TAG, "Message data type: " +rm.data["type"]);
            //its works when the app in forground
            return openNotification(rm.data["title"]+"-dt",rm.data["body"]+"-"+rm.data["type"]+"-db")
        }else{
            openNotification("-dt","-db")
        }

        //its works when the app in forground
        //openNotification(rm.notification?.title+" nt",rm.notification?.body+" nb")
    }

    private fun openNotification(title:String,msg:String) {

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "my-channel"
            val descriptionText ="my-channel-desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("mycid", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
        //pending intent
        val intent=Intent(this,NotificationActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("type",msg+"milon")
        }
        val uniqueInt = (System.currentTimeMillis() and 0xfffffff).toInt()

        val builder = NotificationCompat.Builder(this,"mycid")
            .setSmallIcon(R.mipmap.ic_launcher_round) //icon
            .setContentTitle("Hello-"+title) //tittle
            .setAutoCancel(true) //swipe for delete
            .setContentIntent(PendingIntent.getActivity(this,uniqueInt,intent,PendingIntent.FLAG_UPDATE_CURRENT))
            .setContentText("Hello,"+msg) as NotificationCompat.Builder //content
        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "onNewToken: $token")
        //sendRegistrationToServer(token)
        CoroutineScope(Dispatchers.IO).launch {
            val obj=RetrofitBuilder.api.setToken("milon27",token)
            Log.d(TAG, obj.msg)
        }
    }

}