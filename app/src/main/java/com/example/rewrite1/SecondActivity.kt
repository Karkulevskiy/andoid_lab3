package com.example.rewrite1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
   private var greeting: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

   fun onClickSendMessage(view: View) {
       val extras = intent.extras
       var name = ""

       if (extras != null) {
          val nameText = findViewById<EditText>(R.id.name)
          name = nameText.text.toString()
          greeting = extras.getString(MainActivity.GREETING, "") + name
       }


       if (name.isEmpty()) {
           resetGreeting()
       }

       val intent = Intent().apply {
           putExtra(MainActivity.GREETING_RESULT, greeting)
       }

       setResult(RESULT_OK, intent)
       finish()
   }

    private fun resetGreeting() { greeting = "" }
}