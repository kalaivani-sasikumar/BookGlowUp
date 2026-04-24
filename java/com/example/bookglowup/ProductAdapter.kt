package com.example.bookglowup

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var filteredList = productList.toMutableList()

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val name: TextView = itemView.findViewById(R.id.productName)
        val price: TextView = itemView.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = filteredList[position]

        holder.name.text = product.name
        holder.price.text = product.price
        holder.image.setImageResource(product.imageResId)

        holder.itemView.setOnClickListener {

            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailActivity::class.java)

            intent.putExtra("name", product.name)
            intent.putExtra("price", product.price)
            intent.putExtra("image", product.imageResId)
            intent.putExtra("description", product.description)

            context.startActivity(intent)
        }

    }

    fun filter(query: String) {

        filteredList = if (query.isEmpty()) {
            productList.toMutableList()
        } else {

            val matching = productList.filter {
                it.name.contains(query, ignoreCase = true)
            }

            val notMatching = productList.filter {
                !it.name.contains(query, ignoreCase = true)
            }

            (matching + notMatching).toMutableList()
        }

        notifyDataSetChanged()
    }
}