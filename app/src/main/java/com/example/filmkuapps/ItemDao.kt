package com.example.filmkuapps

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ItemDao { //dao (data acces object), dao menjelaskan gimana caranya kita mendapatkan sesuatu untuk item ini
    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("SELECT * FROM movie_database ORDER BY name ASC")
    fun getAll(): List<Item>

    @Query("SELECT * FROM movie_database WHERE id = :movie_id")
    fun getId(movie_id: Int?): Item?
}


//val item = Item(name= "Ken", NIM = "19103114")
//dao.insert(item)