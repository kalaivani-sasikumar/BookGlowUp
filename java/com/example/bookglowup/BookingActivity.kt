package com.example.bookglowup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView

    private var selectedTime = ""
    private var selectedDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        calendarView = findViewById(R.id.calendarView)

        val time10 = findViewById<Button>(R.id.time10)
        val time11 = findViewById<Button>(R.id.time11)
        val time12 = findViewById<Button>(R.id.time12)
        val time1 = findViewById<Button>(R.id.time1)
        val time2 = findViewById<Button>(R.id.time2)
        val time3 = findViewById<Button>(R.id.time3)
        val time4 = findViewById<Button>(R.id.time4)
        val time5 = findViewById<Button>(R.id.time5)
        val time6 = findViewById<Button>(R.id.time6)

        val buttons = listOf(
            time10,time11,time12,
            time1,time2,time3,
            time4,time5,time6
        )

        var selectedButton: Button? = null

        // Time selection
        buttons.forEach { btn ->

            btn.setOnClickListener {

                // reset previous selection
                selectedButton?.isSelected = false

                // select new button
                btn.isSelected = true

                selectedButton = btn

                selectedTime = btn.text.toString()
            }
        }

        // Date selection
        calendarView.setOnDateChangeListener { _, year, month, day ->

            selectedDate = "$day/${month+1}/$year"

        }

        // Book button
        val paymentBtn = findViewById<Button>(R.id.paymentBtn)

        paymentBtn.setOnClickListener {

            if(selectedDate.isEmpty() || selectedTime.isEmpty())
            {
                Toast.makeText(this,"Select date and time",Toast.LENGTH_SHORT).show()
            }
            else{

                val intent = Intent(this@BookingActivity, BookingDetailsActivity::class.java)

                intent.putExtra("date",selectedDate)
                intent.putExtra("time",selectedTime)

                startActivity(intent)

            }

        }

    }
}