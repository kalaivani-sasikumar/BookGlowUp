package com.example.bookglowup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_booking_details)


        // Receive date & time (safe)
        val bookingDate =
            intent.getStringExtra("date") ?: ""

        val bookingTime =
            intent.getStringExtra("time") ?: ""


        // Connect date & time
        val dateTxt =
            findViewById<TextView>(R.id.bookingDate)

        val timeTxt =
            findViewById<TextView>(R.id.bookingTime)

        dateTxt.text = "Date : $bookingDate"

        timeTxt.text = "Time : $bookingTime"


        // Services list
        val recycler =
            findViewById<RecyclerView>(R.id.bookingRecycler)

        recycler.layoutManager =
            LinearLayoutManager(this)

        recycler.adapter =
            BookingSummaryAdapter(CartManager.cartItems)


        // Payment button navigation
        val paymentBtn =
            findViewById<Button>(R.id.paymentBtn)

        paymentBtn.setOnClickListener {

            val paymentIntent =
                Intent(this, PaymentActivity::class.java)

            paymentIntent.putExtra("date", bookingDate)

            paymentIntent.putExtra("time", bookingTime)

            startActivity(paymentIntent)

        }


        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars =
                insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

    }

}