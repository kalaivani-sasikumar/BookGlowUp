package com.example.bookglowup

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReceiptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_receipt)


        // Receive booking details
        val bookingDate =
            intent.getStringExtra("date") ?: ""

        val bookingTime =
            intent.getStringExtra("time") ?: ""

        val advance =
            intent.getIntExtra("advance",0)

        val remaining =
            intent.getIntExtra("remaining",0)


        // Connect TextViews (add these in XML)
        val dateTxt =
            findViewById<TextView>(R.id.receiptDate)

        val timeTxt =
            findViewById<TextView>(R.id.receiptTime)

        val advanceTxt =
            findViewById<TextView>(R.id.receiptAdvance)

        val remainingTxt =
            findViewById<TextView>(R.id.receiptRemaining)


        // Set values
        dateTxt.text =
            "Date : $bookingDate"

        timeTxt.text =
            "Time : $bookingTime"

        advanceTxt.text =
            "Advance Paid : ₹${String.format("%,d",advance)}"

        remainingTxt.text =
            "Remaining : ₹${String.format("%,d",remaining)}"


        // Services list
        val recycler =
            findViewById<RecyclerView>(R.id.bookingRecycler)

        recycler.layoutManager =
            LinearLayoutManager(this)

        recycler.adapter =
            BookingSummaryAdapter(CartManager.cartItems)


        val download =
            findViewById<Button>(R.id.downloadBtn)



        download.setOnClickListener {

            Toast.makeText(
                this,
                "Receipt Downloaded",
                Toast.LENGTH_SHORT
            ).show()

        }




    }

}