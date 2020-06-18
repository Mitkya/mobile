package ua.nure.kushnir.clientportrait

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Response
import ua.nure.kushnir.clientportrait.facade.RetrofitInstance
import ua.nure.kushnir.clientportrait.facade.UserFacade
import ua.nure.kushnir.clientportrait.model.entity.Profile

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initProfileForm()
        initPopularProducts()
        initBusinessActivity()
        initSignOut()
    }

    private fun initProfileForm() {
        val profileButton = findViewById<Button>(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initBusinessActivity() {
        val businessesActivity = findViewById<Button>(R.id.createBusinessButton)
        val allBusinesses = findViewById<Button>(R.id.allBusinessesButton)
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(UserFacade::class.java)
        val sharedPreference =
            this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("token", "")
        var profile: Profile
        println("Token : $token")
        retrofitInstance.profile(token).enqueue(object : retrofit2.Callback<Profile> {
            override fun onFailure(call: retrofit2.Call<Profile>, t: Throwable) {
                Toast.makeText(
                    this@MenuActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: retrofit2.Call<Profile>, response: Response<Profile>) {
                if (response.code() == 200) {
                    profile = response.body()!!
                    if (profile.user!!.role != "owner") {
                        businessesActivity.visibility = View.GONE
                        allBusinesses.visibility = View.GONE
                    } else {
                        businessesActivity.visibility = View.VISIBLE
                        allBusinesses.visibility = View.VISIBLE
                    }
                } else {
                    Toast.makeText(this@MenuActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })

        businessesActivity.setOnClickListener {
            val intent = Intent(this, BusinessesActivity::class.java)
            startActivity(intent)
        }
        allBusinesses.setOnClickListener {
            val intent = Intent(this, AllBusinessMainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initPopularProducts() {
        val popularProductsButton: Button = findViewById(R.id.popularProductsButton)
        popularProductsButton.setOnClickListener {
            val intent = Intent(this, PopularProductsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initSignOut() {
        val signOutButton = findViewById<Button>(R.id.signOutButton)
        signOutButton.setOnClickListener {
            val sharedPreference =
                this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.remove("token")
            finish()
        }
    }

}