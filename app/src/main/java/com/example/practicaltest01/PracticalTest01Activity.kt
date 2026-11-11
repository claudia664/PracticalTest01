package com.example.practicaltest01

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

const val CLICKS = "clicks"
const val TAG = "PracticalTest01"
const val REQUEST_CODE = 1
const val THRESHOLD = 3 // D.1 b: > 3 elemente -> porneste serviciul
const val SLEEP_TIME = 5000L // D.1 a: 5 secunde
const val ACTION = "ro.pub.cs.systems.eim.practicaltest01var05.ELEMENT"
const val BROADCAST_EXTRA = "message"
class PracticalTest01Activity : AppCompatActivity() {
    private lateinit var templateEditText: EditText
    private var clicks = 0
    private var serviceStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practicaltest01_activity)
        templateEditText = findViewById(R.id.template_edit_text)
        val listener = View.OnClickListener { v ->
            val buttonText = (v as Button).text.toString()
            val current = templateEditText.text.toString()
            if (current.isEmpty()) {
                templateEditText.setText(buttonText)
            } else {
                templateEditText.setText("$current, $buttonText")
            }
            clicks++

        }
        findViewById<Button>(R.id.button_left).setOnClickListener(listener)
        findViewById<Button>(R.id.button_right).setOnClickListener(listener)
        findViewById<Button>(R.id.center).setOnClickListener(listener)
        findViewById<Button>(R.id.button_left_bottom).setOnClickListener(listener)
        findViewById<Button>(R.id.button_right_bottom).setOnClickListener(listener)

        findViewById<Button>(R.id.navigate_to_secondary_activity_button).setOnClickListener {
            val intent = Intent(this, PracticalTest01SecondActivity::class.java)
            intent.putExtra(TEMPLATE, templateEditText.text.toString())
            startActivityForResult(intent, REQUEST_CODE) }

        if (savedInstanceState != null && savedInstanceState.containsKey(CLICKS)) {
            clicks = savedInstanceState.getInt(CLICKS)
            Toast.makeText(this, "Numar apasari: $clicks", Toast.LENGTH_LONG).show()
        }


    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CLICKS, clicks) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            val result = if (resultCode == RESULT_OK) "Verify"
            else "Cancel"
            Toast.makeText(this, "Rezultat: $result", Toast.LENGTH_LONG).show()
            templateEditText.setText("")
            clicks = 0 } }
}
