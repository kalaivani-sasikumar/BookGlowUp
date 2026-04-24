package com.example.bookglowup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookingSummaryAdapter(
    private val items: List<Product>
) : RecyclerView.Adapter<BookingSummaryAdapter.ViewHolder>() {

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view){

        val name =
            view.findViewById<TextView>(R.id.serviceName)

        val price =
            view.findViewById<TextView>(R.id.servicePrice)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_booking_summary,
                    parent,
                    false
                )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return items.size

    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ){

        val product = items[position]

        holder.name.text =
            product.name

        holder.price.text =
            product.price

    }

}