package com.example.example

import com.google.gson.annotations.SerializedName


data class Collapse (

  @SerializedName("url"      ) var url      : List<String> = arrayListOf(),
  @SerializedName("quality"  ) var quality  : List<String> = arrayListOf(),
  @SerializedName("duration" ) var duration : List<String> = arrayListOf(),
  @SerializedName("voice"    ) var voice    : List<String> = arrayListOf()

)