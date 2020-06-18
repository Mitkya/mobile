package ua.nure.kushnir.clientportrait.service

import android.content.Context
import ua.nure.kushnir.clientportrait.model.dto.SignInUserDto
import ua.nure.kushnir.clientportrait.model.dto.SignUpUserDto

interface UserService {

    fun signUp(signUpUserDto: SignUpUserDto, context: Context): Boolean

    fun signIn(signInUserDto: SignInUserDto, context: Context)

}