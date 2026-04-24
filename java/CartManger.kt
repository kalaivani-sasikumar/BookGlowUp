package com.example.bookglowup

object CartManager {

    val cartItems = mutableListOf<Product>()

    // Total cart price (any number of items)
    // Inside CartManager.kt
    fun getTotalPrice(): Int {
        var total = 0
        cartItems.forEach { product ->
            // This regex removes EVERY character except 0-9
            // It converts "₹ 1,200" -> "1200"
            val numericString = product.price.replace(Regex("[^0-9]"), "")

            val price = numericString.toIntOrNull() ?: 0
            total += price

            // This log will show you in Logcat exactly what is happening
            android.util.Log.d("CartManager", "Item: ${product.name} | Original: ${product.price} | Parsed: $price")
        }
        return total
    }

    // GST 5%
    fun getGST(): Int {

        return (getTotalPrice() * 5) / 100

    }

    // Platform fee (fixed once per order)
    fun getPlatformFee(): Int {

        if(cartItems.isEmpty())
            return 0

        return 5
    }

    // Final total
    fun getFinalTotal(): Int {

        return getTotalPrice() +
                getGST() +
                getPlatformFee()

    }

    // Advance = 60% (3/5)
    fun getAdvance(): Int {

        val total = getFinalTotal()

        return (total * 3) / 5

    }

    // Remaining
    fun getRemaining(): Int {

        return getFinalTotal() -
                getAdvance()

    }

}