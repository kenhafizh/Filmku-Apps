package com.example.filmkuapps

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguages(

    @SerializedName("english_name")
    var englishName: String? = null,

    @SerializedName("iso_639_1")
    var iso6391: String? = null,

    @SerializedName("name")
    var name: String? = null,

    ) : Parcelable