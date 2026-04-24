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

class PaymentActivity : AppCompatActivity() {

    lateinit var payadvance1: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_payment)

        payadvance1 = findViewById(R.id.payadvance)


        // Receive date & time from BookingDetails
        val bookingDate = intent.getStringExtra("date") ?: ""
        val bookingTime = intent.getStringExtra("time") ?: ""


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
        Log.d("PaymentActivity", "Cart size: ${CartManager.cartItems.size}")


        // Get cart calculations
        val totalPrice = CartManager.getTotalPrice()
        val gst = CartManager.getGST()
        val platformFee = CartManager.getPlatformFee()
        val advance = CartManager.getAdvance()
        val finalTotal = CartManager.getFinalTotal()
        val remaining = CartManager.getRemaining()


        // Connect XML TextViews
        val basePrice = findViewById<TextView>(R.id.basePrice)
        val gstPrice = findViewById<TextView>(R.id.gstPrice)
        val platformFeeTxt = findViewById<TextView>(R.id.platformFee)
        val totalAmount = findViewById<TextView>(R.id.totalAmount)
        val advanceAmount = findViewById<TextView>(R.id.advanceAmount)
        val remainingAmount = findViewById<TextView>(R.id.remainingAmount)


        // Set values
        basePrice.text = "₹${String.format("%,d", totalPrice)}"
        gstPrice.text = "₹${String.format("%,d", gst)}"
        platformFeeTxt.text = "₹${String.format("%,d", platformFee)}"
        totalAmount.text = "₹${String.format("%,d", finalTotal)}"
        advanceAmount.text = "₹${String.format("%,d", advance)}"
        remainingAmount.text = "₹${String.format("%,d", remaining)}"


        // Payment button
        payadvance1.setOnClickListener {

            val intent =
                Intent(this@PaymentActivity,
                    PaymentSuccessfulActivity::class.java)

            // Send date & time to final page
            intent.putExtra("date", bookingDate)
            intent.putExtra("time", bookingTime)

            startActivity(intent)

        }

    }

}