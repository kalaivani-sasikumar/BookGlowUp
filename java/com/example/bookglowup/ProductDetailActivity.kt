package com.example.bookglowup

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val image = findViewById<ImageView>(R.id.detailImage)
        val nameText = findViewById<TextView>(R.id.detailName)
        val priceText = findViewById<TextView>(R.id.detailPrice)
        val descriptionText = findViewById<TextView>(R.id.detailDescription)
        val addToCart = findViewById<Button>(R.id.addToCartBtn)

        // Get data from intent
        val productName = intent.getStringExtra("name") ?: ""
        val productPrice = intent.getStringExtra("price") ?: ""
        val productImage = intent.getIntExtra("image", 0)
        val productDescription = intent.getStringExtra("description") ?: ""
        val productCategory = intent.getStringExtra("category") ?: ""

        // Set data
        nameText.text = productName
        priceText.text = productPrice
        descriptionText.text = productDescription
        image.setImageResource(productImage)

        val product = Product(
            productName,
            productPrice,
            productImage,
            productDescription,
            productCategory
        )

        addToCart.setOnClickListener {

            if (CartManager.cartItems.contains(product)) {

                Toast.makeText(
                    this,
                    "Item already added to cart",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                CartManager.cartItems.add(product)

                Toast.makeText(
                    this,
                    "Item added to cart",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

}