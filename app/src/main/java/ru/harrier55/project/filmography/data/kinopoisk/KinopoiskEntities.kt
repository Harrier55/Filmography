package com.example.example

import com.google.gson.annotations.SerializedName


data class KinopoiskEntities (

  @SerializedName("movies"     ) var movies     : List<Movies> = arrayListOf(),
  @SerializedName("pagination" ) var pagination : Pagination?  = Pagination()

)