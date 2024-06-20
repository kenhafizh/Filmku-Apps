package com.example.filmkuapps

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie_database") //deklarasi nama tabel
data class Item (
    @PrimaryKey() //autogenerate = true yaitu agar tidak ada data yang duplikat
    val id: Long= 0,
    val name: String,
) : Parcelable
