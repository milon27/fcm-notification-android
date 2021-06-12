package com.m27lab.fcmtestwithnodejs.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.m27lab.fcmtestwithnodejs.R
import com.m27lab.fcmtestwithnodejs.viewmodels.FcmViewModel

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<FcmViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textview=findViewById<TextView>(R.id.custom_data)
        val btn=findViewById<Button>(R.id.btn)
        val cdata=intent.extras

        textview.text="account="+cdata?.getString("account")+",balance="+cdata?.getString("balance")

        btn.setOnClickListener {
            Log.d("FirebaseMessagingSe", "onCreate: te")
            viewModel.setToken("ofkfoe").observe(this){
                Log.d("FirebaseMessagingSe", "onCreate: "+it)
            }
        }
    }
}