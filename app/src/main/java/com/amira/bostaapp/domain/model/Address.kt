package com.amira.bostaapp.domain.model

data class Address(
    var street: String,
    var suite: String,
    var city:String,
    var zipcode:String,
    var geo : Geometric
)
