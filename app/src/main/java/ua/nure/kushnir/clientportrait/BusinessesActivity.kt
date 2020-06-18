package ua.nure.kushnir.clientportrait

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.nure.kushnir.clientportrait.facade.BusinessFacade
import ua.nure.kushnir.clientportrait.facade.RetrofitInstance
import ua.nure.kushnir.clientportrait.model.dto.BusinessesDto
import ua.nure.kushnir.clientportrait.model.entity.Business

class BusinessesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_businesses)
        createBusiness()
    }

    private fun createBusiness() {
        val createButton = findViewById<Button>(R.id.createBisenessButton)
        createButton.setOnClickListener {
            val retrofitInstance =
                RetrofitInstance.getRetrofitInstance().create(BusinessFacade::class.java)
            val sharedPreference =
                this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", "")
            val title = findViewById<EditText>(R.id.busenessNameEditTextText)
            val businessesDto = BusinessesDto(title.text.toString())
            var business: Business
            retrofitInstance.createBusiness(businessesDto, token)
                .enqueue(object : Callback<Business> {
                    override fun onFailure(call: Call<Business>, t: Throwable) {
                        Toast.makeText(
                            this@BusinessesActivity,
                            t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(call: Call<Business>, response: Response<Business>) {
                        if (response.code() == 200) {
                            business = response.body()!!
                            if (business.title != null) {
                                Toast.makeText(
                                    this@BusinessesActivity,
                                    "Success!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
        }
    }

}
