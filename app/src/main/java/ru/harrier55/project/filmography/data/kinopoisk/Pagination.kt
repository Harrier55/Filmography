package com.example.example

import com.google.gson.annotations.SerializedName


data class Pagination (

  @SerializedName("current_page" ) var currentPage : String? = null,
  @SerializedName("end_page"     ) var endPage     : String? = null,
  @SerializedName("total_pages"  ) var totalPages  : Int?    = null

)