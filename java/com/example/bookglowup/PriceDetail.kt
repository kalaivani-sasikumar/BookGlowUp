package com.example.bookglowup

data class PriceDetails(
    val cartItems: List<Product> = CartManager.cartItems  // Get from CartManager
) {
    val totalMrp: Int = CartManager.getTotalPrice()  // Use existing calculation
    val gstPercent: Int = 5
    val platformFee: Int = 5
    val advancePercent: Int = 60  // 60% advance (3/5)

    val gstAmount: Int = (totalMrp * gstPercent) / 100
    val totalAmount: Int = totalMrp + gstAmount + platformFee
    val advanceAmount: Int = (totalAmount * 3) / 5  // 60% of total
    val remainingAmount: Int = totalAmount - advanceAmount
}
