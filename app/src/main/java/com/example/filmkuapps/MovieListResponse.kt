package com.example.filmkuapps

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class MovieListResponse(

  @SerializedName("page")
  var page: Int? = null,
  @SerializedName("results")
  var results: ArrayList<Movie> = arrayListOf(),
  @SerializedName("total_pages")
  var totalPages: Int? = null,
  @SerializedName("total_results")
  var totalResults: Int? = null,

  )