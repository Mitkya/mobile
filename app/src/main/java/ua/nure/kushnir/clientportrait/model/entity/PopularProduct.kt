package ua.nure.kushnir.clientportrait.model.entity

data class PopularProduct(
    var wasBought: Int? = null,
    var id: String? = null,
    var storeId: String? = null,
    var title: String? = null,
    var cost: Int? = null
)