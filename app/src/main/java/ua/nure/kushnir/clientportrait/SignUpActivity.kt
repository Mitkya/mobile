package ua.nure.kushnir.clientportrait

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ua.nure.kushnir.clientportrait.model.dto.SignUpUserDto
import ua.nure.kushnir.clientportrait.service.UserService
import ua.nure.kushnir.clientportrait.service.impl.UserServiceImpl
import ua.nure.kushnir.clientportrait.util.InputValidator

class SignUpActivity : AppCompatActivity() {

    private lateinit var userService: UserService
    private lateinit var inputValidator: InputValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initObjects()
        signUp()
    }

    private fun initObjects() {
        userService = UserServiceImpl()
        inputValidator = InputValidator(this)
    }

    private fun signUp() {
        val signUpUserDto = SignUpUserDto()
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.nameEditText)
            val email = findViewById<EditText>(R.id.emailEditText)
            val password = findViewById<EditText>(R.id.passwordEditText)
            signUpUserDto.name = name.text.toString()
            signUpUserDto.email = email.text.toString()
            signUpUserDto.password = password.text.toString()
            userService.signUp(signUpUserDto, this)
            onBackPressed()
        }
    }

}