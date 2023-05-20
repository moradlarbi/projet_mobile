package com.example.mynavigation.entity

import com.google.gson.annotations.SerializedName


data class Movie(
    val id:Int,
    val etat:String,
    val type:String,
    val position:String? ,
    val idClient : Int,
    val idRegion : Int,
    val idAM : Int,
    val codeDeverouillage : String
)