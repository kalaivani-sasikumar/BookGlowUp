package com.example.bookglowup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentSuccessfulActivity : AppCompatActivity() {

    lateinit var details1: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_payment_successful)


        // Receive date & time from PaymentActivity
        val bookingDate = intent.getStringExtra("date") ?: ""
        val bookingTime = intent.getStringExtra("time") ?: ""


        details1 = findViewById(R.id.details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }


        // DEBUG cart
        Log.d("PaymentSuccesfulActivity",
            "Cart size: ${CartManager.cartItems.size}")


        // Get cart calculations
        val advance = CartManager.getAdvance()

        val remaining = CartManager.getRemaining()


        // Connect XML
        val advanceAmount =
            findViewById<TextView>(R.id.advanceAmount)

        val remainingAmount =
            findViewById<TextView>(R.id.remainingAmount)


        // Set values
        advanceAmount.text =
            "₹${String.format("%,d", advance)}"

        remainingAmount.text =
            "₹${String.format("%,d", remaining)}"


        // Send everything to Receipt page
        details1.setOnClickListener {

            val intent =
                Intent(this@PaymentSuccessfulActivity,
                    ReceiptActivity::class.java)

            intent.putExtra("date", bookingDate)

            intent.putExtra("time", bookingTime)

            intent.putExtra("advance", advance)

            intent.putExtra("remaining", remaining)

            startActivity(intent)

        }

    }

}