package com.example.example

import com.google.gson.annotations.SerializedName


data class Docs (

  @SerializedName("externalId"       ) var externalId       : ExternalId?      = ExternalId(),
  @SerializedName("poster"           ) var poster           : Poster?          = Poster(),
  @SerializedName("rating"           ) var rating           : Rating?          = Rating(),
  @SerializedName("votes"            ) var votes            : Votes?           = Votes(),
  @SerializedName("movieLength"      ) var movieLength      : Int?             = null,
  @SerializedName("id"               ) var id               : Int?             = null,
  @SerializedName("type"             ) var type             : String?          = null,
  @SerializedName("name"             ) var name             : String?          = null,
  @SerializedName("description"      ) var description      : String?          = null,
  @SerializedName("year"             ) var year             : Int?             = null,
  @SerializedName("alternativeName"  ) var alternativeName  : String?          = null,
  @SerializedName("enName"           ) var enName           : String?          = null,
  @SerializedName("names"            ) var names            : ArrayList<Names> = arrayListOf(),
  @SerializedName("shortDescription" ) var shortDescription : String?          = null

)