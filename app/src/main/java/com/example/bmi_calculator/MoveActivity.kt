package com.example.bmi_calculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MoveActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move)

        val b = intent.extras
        var value = "none"
        if (b != null)
            value = b.getString("result", "none")

        val resultTextView=findViewById<TextView>(R.id.textViewResult)
        resultTextView.setText(value)

        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonReset.setOnClickListener {
            reset()
        }
    }

    private fun reset(){
        val intent = Intent(this@MoveActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
