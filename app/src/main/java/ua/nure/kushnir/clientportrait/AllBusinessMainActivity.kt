package ua.nure.kushnir.clientportrait

import android.content.Context
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Response
import ua.nure.kushnir.clientportrait.adapter.AllBusinessViewAdapter
import ua.nure.kushnir.clientportrait.facade.BusinessFacade
import ua.nure.kushnir.clientportrait.facade.RetrofitInstance
import ua.nure.kushnir.clientportrait.model.entity.Business

class AllBusinessMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_business_main)
        showAllBusinesses()
    }

    private fun showAllBusinesses() {
        val retrofitInstance =
            RetrofitInstance.getRetrofitInstance().create(BusinessFacade::class.java)
        val sharedPreference =
            this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("token", "")
        var businesses: List<Business>
        println("Token : $token")
        retrofitInstance.profile(token).enqueue(object : retrofit2.Callback<List<Business>> {
            override fun onFailure(call: Call<List<Business>>, t: Throwable) {
                Toast.makeText(
                    this@AllBusinessMainActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(
                call: Call<List<Business>>,
                response: Response<List<Business>>
            ) {
                if (response.code() == 200) {
                    businesses = response.body()!!
                    val popularProductListView = findViewById<ListView>(R.id.allBusinessView)
                    popularProductListView.adapter =
                        AllBusinessViewAdapter(this@AllBusinessMainActivity, businesses)
                    Toast.makeText(this@AllBusinessMainActivity, "Success!", Toast.LENGTH_SHORT)
                        .show()

                    Toast.makeText(this@AllBusinessMainActivity, "Failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })


    }

}