package com.example.bookglowup


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val category = intent.getStringExtra("category")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = findViewById<SearchView>(R.id.searchView)

        val productList = when (category) {

            "facial" -> listOf(
                Product("Detan Treatment", "₹700", R.drawable.skin1,"Deep cleansing facial treatment","facial"),
                Product("Anti-Aging Treatment", "₹1,500", R.drawable.skin2,"Deep cleansing facial treatment","facial"),
                Product("Acne Treatment", "₹800", R.drawable.skin3,"Deep cleansing facial treatment","facial"),
                Product("Chemical Peel", "₹1000", R.drawable.skin4,"Deep cleansing facial treatment","facial"),
                Product("Face Bleaching", "₹500", R.drawable.skin5,"Deep cleansing facial treatment","facial"),
                Product("Gua-Sha facial", "₹800", R.drawable.skin6,"Deep cleansing facial treatment","facial"),
                Product("Hydra Facial", "₹1,200", R.drawable.skin7,"Deep cleansing facial treatment","facial"),
                Product("LED-Light Facial", "₹1,500", R.drawable.skin8,"Deep cleansing facial treatment","facial"),
                Product("Vitamin-C Facial", "₹800", R.drawable.skin9,"Deep cleansing facial treatment","facial"),
                Product("Deep-Pore cleansing", "₹700", R.drawable.skin10,"Deep cleansing facial treatment","facial")
            )

            "nail" -> listOf(
                Product("Manicure", "₹500", R.drawable.nail1,"Nail polish service","nail"),
                Product("Pedicure", "₹500", R.drawable.nail2,"Nail remover treatment","nail"),
                Product("Gel Polish", "₹1,000", R.drawable.nail3,"Beautiful nail art","nail"),
                Product("Nail Art", "₹1,700", R.drawable.nail4,"Beautiful nail art","nail"),
                Product("Nail Extensions", "₹2000", R.drawable.nail5,"Beautiful nail art","nail"),
                Product("Nail Repair", "₹1,100", R.drawable.nail7,"Beautiful nail art","nail"),
                Product("Dip Powder", "₹1,800", R.drawable.nail8,"Beautiful nail art","nail")
            )

            "makeup" -> listOf(
                Product("Party MakeUp", "₹10,000", R.drawable.makeup1,"Professional lipstick","makeup"),
                Product("Wedding Makeup", "₹20,000", R.drawable.makeup2,"Makeup foundation","makeup"),
                Product("Engagement Makeup", "₹15,000", R.drawable.makeup3,"Face powder","makeup"),
                Product("Reception Makeup", "₹25,000", R.drawable.makeup4,"Face powder","makeup"),
                Product("Event Makeup", "₹8,000", R.drawable.makeup6,"Face powder","makeup"),
                Product("No-Makeup Look", "₹5,000", R.drawable.makeup5,"Face powder","makeup")
            )

            "hair" -> listOf(
                Product("Hair Colouring", "₹5,000", R.drawable.hair1,"Hair wash service","hair"),
                Product("Party Hairstyles", "₹7,000", R.drawable.hair7,"Hair mask treatment","hair"),
                Product("Wedding Hairstyles", "₹15,000", R.drawable.hair8,"Hair mask treatment","hair"),
                Product("Hair Styling", "₹8,000", R.drawable.hair3,"Hair mask treatment","hair"),
                Product("Hair Cuts", "₹3,000", R.drawable.hair4,"Hair mask treatment","hair"),
                Product("Hair Extensions", "₹5,000", R.drawable.hair5,"Hair mask treatment","hair"),
                Product("Keratin Treatment", "₹5,600", R.drawable.hair6,"Hair mask treatment","hair"),
                Product("Hair Wash & Blow Dry", "₹2,500", R.drawable.hair2,"Hair conditioning","hair"),
                Product("Engagement Hairstyle", "₹10,000", R.drawable.hair10,"Hair conditioning","hair"),
                Product("Reception Hairstyle", "₹12,000", R.drawable.hair11,"Hair conditioning","hair"),
                )

            "waxing" -> listOf(
                Product("Leg Waxing", "₹1,300", R.drawable.wax1,"Waxing service","waxing"),
                Product("Underarm Waxing", "₹1,000", R.drawable.wax2,"Waxing service","waxing"),
                Product("Arm Waxing", "₹1,000", R.drawable.wax3,"Waxing service","waxing"),
                Product("UpperLips Waxing", "₹500", R.drawable.wax4,"Waxing service","waxing"),
                Product("Upper Eyebrow Waxing", "₹700", R.drawable.wax5,"Waxing service","waxing"),

            )

            "spa" -> listOf(
                Product("Full Body Spa Therapy", "₹10,000", R.drawable.spa1,"Relaxing spa treatment","spa"),
                Product("Facial Treatment", "₹1,800", R.drawable.spa2,"Relaxing spa treatment","spa"),
                Product("Aromatherapy Spa", "₹3,700", R.drawable.spa3,"Relaxing spa treatment","spa"),
                Product("Bubble Bath", "₹5,100", R.drawable.spa4,"Relaxing spa treatment","spa"),
                Product("Head Massage", "₹4,250", R.drawable.massage1,"Relaxing massage","spa"),
                Product("Facial Massage", "₹1,900", R.drawable.massage2,"Relaxing massage","spa"),
                Product("Foot Massage", "₹1,500", R.drawable.massage3,"Relaxing massage","spa"),
                Product("Leg Massage", "₹2,250", R.drawable.massage4,"Relaxing massage","spa"),
                Product("Back & Neck Massage", "₹3,400", R.drawable.massage6,"Relaxing massage","spa")
            )

            "massage" -> listOf(
                Product("Head Massage", "₹1500", R.drawable.massage1,"Relaxing massage","massage"),
                Product("EyeBrow Threading", "₹500", R.drawable.add1,"Extra beauty service","massage"),
                Product("Upper lip Threading", "₹500", R.drawable.add2,"Extra beauty service","massage"),
                Product("forehead threading", "₹400", R.drawable.add3,"Extra beauty service","massage")
                )

            "addon" -> listOf(
                Product("Eye-lash Extensions", "₹799", R.drawable.add4,"Extra beauty service","addon"),
                Product("Hair Hot oil Treatment", "₹699", R.drawable.add5,"Extra beauty service","addon"),
                Product("Detan Pack", "₹1,000", R.drawable.add6,"Extra beauty service","addon")

            )

            else -> emptyList()
        }

        val adapter = ProductAdapter(productList)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
    }
}