package com.example.mynavigation

data class CartItem(
    val   id:Int ,
    val name : String,
    val price: Int,
    val image: String,
    val ingredients: String,
    val restaurantId: Int ,
    val quantity: Int
)
