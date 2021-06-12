package com.m27lab.fcmtestwithnodejs.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.m27lab.fcmtestwithnodejs.R
import kotlin.math.log

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
//        if(intent.getStringExtra("type")!=null){
//            Toast.makeText(this, ""+intent.getStringExtra("type"), Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
//        }
        Toast.makeText(this, ","+intent.getStringExtra("type"), Toast.LENGTH_SHORT).show()
    }
}