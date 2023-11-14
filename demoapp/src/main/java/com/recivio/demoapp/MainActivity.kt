package com.recivio.demoapp

import android.os.Bundle
import android.util.Log
import com.recivio.recivio
import com.recivio.component.CompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.button_support.setOnClickListener {
            recivio.openSupport(this@MainActivity)
            Log.e("recivio Android SDK", "OPEN SUPPORT")
        }

        this.button_logout.setOnClickListener {
            recivio.logoutUser()
            Log.e("recivio Android SDK", "LOGGED OUT")
        }

        this.button_register.setOnClickListener {
            val email = this@MainActivity.edittext_register.text.toString()
            recivio.logoutUser {
                recivio.registerUser(email)
            }
            Log.e("recivio Android SDK", "REGISTERING $email")
        }
    }
}
