package com.example.practicaltest01

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

const val TEMPLATE = "template"


class PracticalTest01SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practicaltest01_secondactivity)
        findViewById<TextView>(R.id.template_text_view).text = intent.getStringExtra(TEMPLATE)
        findViewById<Button>(R.id.verify_button).setOnClickListener { setResult(RESULT_OK)
            finish() }
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish() }
    }
    }