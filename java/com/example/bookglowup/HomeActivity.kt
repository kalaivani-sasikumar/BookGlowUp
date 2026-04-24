package com.example.bookglowup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)


        val cartIcon = findViewById<ImageView>(R.id.imageView3)
        val searchView = findViewById<SearchView>(R.id.searchView)

        // ⭐ Best Seller Recycler
        val bestSellerRecycler =
            findViewById<RecyclerView>(R.id.bestSellerRecycler)

        bestSellerRecycler.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        val bestList = listOf(

            Product(
                "Manicure",
                "₹500",
                R.drawable.nail1,
                "Deep cleansing facial",
                "facial"
            ),

            Product(
                "Nail Art",
                "₹1700",
                R.drawable.nail4,
                "Nail design",
                "nail"
            ),

            Product(
                "Party Hairstyle",
                "₹7000",
                R.drawable.hair7,
                "Hair styling",
                "hair"
            ),

            Product(
                "Bubble Bath",
                "₹5100",
                R.drawable.spa4,
                "Spa treatment",
                "spa"
            )

        )

        bestSellerRecycler.adapter =
            BestSellerAdapter(bestList)


        // 🔍 Search toggle

        // 🛒 Cart
        cartIcon.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    CartActivity::class.java
                )
            )
        }

        // Categories
        findViewById<ImageView>(R.id.facialIcon)
            .setOnClickListener {
                openCategory("facial")
            }

        findViewById<ImageView>(R.id.nailIcon)
            .setOnClickListener {
                openCategory("nail")
            }

        findViewById<ImageView>(R.id.makeupIcon)
            .setOnClickListener {
                openCategory("makeup")
            }

        findViewById<ImageView>(R.id.hairIcon)
            .setOnClickListener {
                openCategory("hair")
            }

        findViewById<ImageView>(R.id.waxingIcon)
            .setOnClickListener {
                openCategory("waxing")
            }

        findViewById<ImageView>(R.id.spaIcon)
            .setOnClickListener {
                openCategory("spa")
            }

        findViewById<ImageView>(R.id.massageIcon)
            .setOnClickListener {
                openCategory("massage")
            }

        findViewById<ImageView>(R.id.addonIcon)
            .setOnClickListener {
                openCategory("addon")
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

    private fun openCategory(category: String) {

        val intent =
            Intent(
                this,
                ProductListActivity::class.java
            )

        intent.putExtra("category", category)

        startActivity(intent)

    }

}