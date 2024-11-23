package com.example.rewrite1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val GREETING = "GREETING"
        const val GREETING_VALUE = "Hello, my friend - "
        const val GREETING_RESULT = "GREETING_RESULT"
        const val GREETING_NOT_SET = "Name wasn't set :("
    }

    private var startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
       val textView = findViewById<TextView>(R.id.resultGreeting)
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val callBackGreeting = intent?.getStringExtra(GREETING_RESULT)
            textView.text = if (!callBackGreeting.isNullOrEmpty()) {
                callBackGreeting
            } else {
                GREETING_NOT_SET
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPause() {
        super.onPause()
        findViewById<TextView>(R.id.resultGreeting).text = ""
    }

    fun myOnClick(view: View) {
        val intent = Intent(this, SecondActivity::class.java).apply { putExtra(GREETING, GREETING_VALUE) }
        startForResult.launch(intent)
    }

}