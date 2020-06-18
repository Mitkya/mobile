package ua.nure.kushnir.clientportrait

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ua.nure.kushnir.clientportrait.model.dto.SignInUserDto
import ua.nure.kushnir.clientportrait.service.UserService
import ua.nure.kushnir.clientportrait.service.impl.UserServiceImpl

class MainActivity : AppCompatActivity() {

    private lateinit var userService: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObjects()
        initSignUpForm()
        initSignIn()
    }

    private fun initObjects() {
        userService = UserServiceImpl()
    }

    private fun initSignUpForm() {
        val signUpButton = findViewById<Button>(R.id.signUpLinkButton)
        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initSignIn() {
        val signInUserDto = SignInUserDto()
        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailEditText)
            val password = findViewById<EditText>(R.id.passwordEditText)
            signInUserDto.email = email.text.toString()
            signInUserDto.password = password.text.toString()
            userService.signIn(signInUserDto, this)
            val sharedPreference =
                this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", "")
            println(token)
            token.let {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
