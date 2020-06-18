package ua.nure.kushnir.clientportrait.model.entity

data class Profile(
    var businesses: Array<String>? = null,
    var moneySpent: Int? = null,
    var user: User? = null,
    var age: Int? = null,
    var city: String? = null,
    var country: String? = null,
    var dateOfBirth: String? = null,
    var income: Int? = null,
    var phone: String? = null,
    var sex: String? = null,
    var status: String? = null
)