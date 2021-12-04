package com.example.example

import com.google.gson.annotations.SerializedName


data class Videocdn (

  @SerializedName("url"      ) var url      : List<String> = arrayListOf(),
  @SerializedName("quality"  ) var quality  : List<String> = arrayListOf(),
  @SerializedName("duration" ) var duration : String?      = null,
  @SerializedName("voice"    ) var voice    : List<String> = arrayListOf()

)