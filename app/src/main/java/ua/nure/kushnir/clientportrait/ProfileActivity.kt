package ua.nure.kushnir.clientportrait

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Response
import ua.nure.kushnir.clientportrait.facade.RetrofitInstance
import ua.nure.kushnir.clientportrait.facade.UserFacade
import ua.nure.kushnir.clientportrait.model.entity.Profile
import ua.nure.kushnir.clientportrait.service.UserService
import ua.nure.kushnir.clientportrait.service.impl.UserServiceImpl

class ProfileActivity : AppCompatActivity() {

    private lateinit var userService: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initObjects()
        initBackButton()
        enrichFields()
    }

    private fun initObjects() {
        userService = UserServiceImpl()
    }

    private fun enrichFields() {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(UserFacade::class.java)
        val sharedPreference =
            this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("token", "")
        var profile: Profile
        println("Token : $token")
        retrofitInstance.profile(token).enqueue(object : retrofit2.Callback<Profile> {
            override fun onFailure(call: retrofit2.Call<Profile>, t: Throwable) {
                Toast.makeText(
                    this@ProfileActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: retrofit2.Call<Profile>, response: Response<Profile>) {
                if (response.code() == 200) {
                    profile = response.body()!!
                    if (profile.user!!.role == "client") {
                        val nameTextView: TextView = findViewById(R.id.nameTextView)
                        val roleTextView: TextView = findViewById(R.id.roleTextView)
                        val moneySpentTextView: TextView = findViewById(R.id.moneySpentTextView)
                        val statusTextView: TextView = findViewById(R.id.statusTextView)
                        val textView5: TextView = findViewById(R.id.textView5)
                        val textView6: TextView = findViewById(R.id.textView6)
                        textView6.visibility = View.VISIBLE
                        textView5.visibility = View.VISIBLE
                        nameTextView.visibility = View.VISIBLE
                        roleTextView.visibility = View.VISIBLE
                        moneySpentTextView.visibility = View.VISIBLE
                        statusTextView.visibility = View.VISIBLE
                        nameTextView.text = profile.user!!.name
                        roleTextView.text = profile.user!!.role
                        moneySpentTextView.text =
                            profile.moneySpent.toString()
                        statusTextView.text = profile.status
                    } else if (profile.user!!.role == "salesman" || profile.user!!.role == "owner") {
                        val nameTextView: TextView = findViewById(R.id.nameTextView)
                        val roleTextView: TextView = findViewById(R.id.roleTextView)
                        nameTextView.visibility = View.VISIBLE
                        roleTextView.visibility = View.VISIBLE
                        nameTextView.text = profile.user!!.name
                        roleTextView.text = profile.user!!.role
                    }

                    Toast.makeText(this@ProfileActivity, "Success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ProfileActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    private fun initBackButton() {
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

}