package ua.nure.kushnir.clientportrait

import android.content.Context
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Response
import ua.nure.kushnir.clientportrait.adapter.PopularListViewAdapter
import ua.nure.kushnir.clientportrait.facade.RetrofitInstance
import ua.nure.kushnir.clientportrait.facade.StatisticFacade
import ua.nure.kushnir.clientportrait.model.entity.PopularProduct

class PopularProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_products)
        showPopularProducts()
    }

    private fun showPopularProducts() {
        val retrofitInstance =
            RetrofitInstance.getRetrofitInstance().create(StatisticFacade::class.java)
        val sharedPreference =
            this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("token", "")
        var popularProducts: List<PopularProduct>
        retrofitInstance.getAllPopularProducts(token)
            .enqueue(object : retrofit2.Callback<List<PopularProduct>> {
                override fun onFailure(call: retrofit2.Call<List<PopularProduct>>, t: Throwable) {
                    Toast.makeText(
                        this@PopularProductsActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: retrofit2.Call<List<PopularProduct>>,
                    response: Response<List<PopularProduct>>
                ) {
                    if (response.code() == 200) {
                        popularProducts = response.body()!!
                        val popularProductListView = findViewById<ListView>(R.id.popularProductView)
                        popularProductListView.adapter =
                            PopularListViewAdapter(this@PopularProductsActivity, popularProducts)
                        Toast.makeText(this@PopularProductsActivity, "Success!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this@PopularProductsActivity, "Failed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            })
    }

}