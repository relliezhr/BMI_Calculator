package com.example.bmi_calculator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView


class MainActivity : Activity(), View.OnClickListener {

    private var gender: String = "Laki-laki"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)


        buttonCalculate.setOnClickListener {
            calculateBMI()
        }

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonCalculate -> {
                calculateBMI()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateBMI() {
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAddres = findViewById<EditText>(R.id.editTextAddres)
        val name = editTextName.text.toString()
        val addres = editTextAddres.text.toString()
        val height = editTextHeight.text.toString().toDouble()
        val weight = editTextWeight.text.toString().toDouble()

        val selectedGenderId = radioGroupGender.checkedRadioButtonId

        gender = when (selectedGenderId) {
            R.id.radioButtonMale -> "Laki-laki"
            R.id.radioButtonFemale -> "Perempuan"
            else -> "Laki-laki"
        }

        val bmi = when (gender) {
            "Laki-laki" -> weight / ((height / 100) * (height / 100))
            "Perempuan" -> weight / ((height / 100) * (height / 100)) * 0.9
            else -> 0.0
        }

        val result = when {
            bmi < 18.5 -> "Berat badan kurang"
            bmi >= 18.5 && bmi < 24.9 -> "Berat badan normal"
            bmi >= 24.9 && bmi < 29.9 -> "Berat badan berlebih"
            else -> "Obesitas"
        }

        val textResult = "$name\n$addres \nBMI: %.2f\n$result".format(bmi)

        val intent = Intent(this@MainActivity, MoveActivity::class.java)
        val b = Bundle()
        b.putString("result", textResult)
        intent.putExtras(b)
        startActivity(intent)
        finish()
    }
}