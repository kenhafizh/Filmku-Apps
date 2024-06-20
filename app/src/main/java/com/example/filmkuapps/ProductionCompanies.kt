package com.example.filmkuapps

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompanies(

  @SerializedName("id")
  var id: Int? = null,

  @SerializedName("logo_path")
  var logoPath: String? = null,

  @SerializedName("name")
  var name: String? = null,

  @SerializedName("origin_country")
  var originCountry: String? = null,

  ) : Parcelable