package com.example.example

import com.google.gson.annotations.SerializedName


data class Kodik (

  @SerializedName("url"      ) var url      : String? = null,
  @SerializedName("quality"  ) var quality  : String? = null,
  @SerializedName("duration" ) var duration : String? = null,
  @SerializedName("voice"    ) var voice    : String? = null

)