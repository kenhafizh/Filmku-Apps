package com.example.filmkuapps

import com.google.gson.annotations.SerializedName


data class MovieReviews (

  @SerializedName("id"            ) var id           : Int?               = null,
  @SerializedName("page"          ) var page         : Int?               = null,
  @SerializedName("results"       ) var results      : ArrayList<Results> = arrayListOf(),
  @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
  @SerializedName("total_results" ) var totalResults : Int?               = null

)