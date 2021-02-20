package com.pieter.party.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.pieter.party.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.login).setOnClickListener { startDatastoreChaos() }
        findViewById<MaterialButton>(R.id.signUp).setOnClickListener { startDatastoreChaos() }
    }

    private fun startDatastoreChaos() {
        val intent = Intent(this, DatastoreActivity::class.java)
        startActivity(intent)
    }
}