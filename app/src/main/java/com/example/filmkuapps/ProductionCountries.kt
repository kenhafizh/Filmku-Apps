package com.example.filmkuapps

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountries(

  @SerializedName("iso_3166_1")
  var iso31661: String? = null,

  @SerializedName("name")
  var name: String? = null,

  ) : Parcelable