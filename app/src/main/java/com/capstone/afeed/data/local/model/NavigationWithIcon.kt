package com.capstone.afeed.data.local.model

data class NavigationWithIcon(
    val id : Int,
    val lefIcon : Int? = null,
    val rightIcon : Int? = null,
    val title : String,
    val description : String,
)