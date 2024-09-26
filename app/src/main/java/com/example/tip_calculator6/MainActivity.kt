package com.example.tip_calculator6

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    // Declare properties as lateinit (for non-nullable types)
    private lateinit var etBill: EditText
    private lateinit var radioGroupTip: RadioGroup
    private lateinit var tvTipAmount: TextView
    private lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the lateinit properties after the view is set up
        etBill = findViewById(R.id.etBill)
        radioGroupTip = findViewById(R.id.radioGroupTip)
        tvTipAmount = findViewById(R.id.tvTip) // Change the TextView ID accordingly
        btnCalculate = findViewById(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        // Validate EditText input
        val billAmountString = etBill.text.toString().trim()
        if (billAmountString.isBlank()) {
            showToast("Please enter a valid bill amount")
            return
        }

        // Convert the bill amount to a Double
        val billAmount = billAmountString.toDoubleOrNull()
        if (billAmount == null || billAmount <= 0) {
            showToast("Please enter a valid bill amount")
            return
        }

        // Determine the selected tip percentage
        val tipPercent = when (radioGroupTip.checkedRadioButtonId) {
            R.id.rbAmazing -> 20
            R.id.rbGood -> 18
            R.id.rbOk -> 15
            else -> {
                showToast("Please select a tip percentage")
                return
            }
        }

        // Calculate the tip amount
        val tipAmount = billAmount * (tipPercent / 100.0)

        // Round up the tip amount
        val roundedTip = ceil(tipAmount)

        // Display the tip amount
        tvTipAmount.text = String.format("Tip Amount: $%.2f", roundedTip)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
