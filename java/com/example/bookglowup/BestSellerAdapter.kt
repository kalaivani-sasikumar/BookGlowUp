package com.example.bookglowup

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BestSellerAdapter(
    private val list: List<Product>
) : RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view){

        val image: ImageView =
            view.findViewById(R.id.serviceImage)

        val name: TextView =
            view.findViewById(R.id.serviceName)

        val price: TextView =
            view.findViewById(R.id.servicePrice)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.best_seller_item,
                parent,
                false
            )

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = list[position]

        holder.name.text = item.name
        holder.price.text = item.price
        holder.image.setImageResource(item.imageResId)

        // ⭐ OPEN DETAIL PAGE (THIS FIXES YOUR ISSUE)
        holder.itemView.setOnClickListener {

            val intent =
                Intent(
                    holder.itemView.context,
                    ProductDetailActivity::class.java
                )

            intent.putExtra("name", item.name)
            intent.putExtra("price", item.price)
            intent.putExtra("image", item.imageResId)
            intent.putExtra("description", item.description)
            intent.putExtra("category", item.category)

            holder.itemView.context.startActivity(intent)

        }

    }

}