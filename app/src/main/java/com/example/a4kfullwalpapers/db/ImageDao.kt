package com.example.a4kfullwalpapers.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImageDao {

    @Insert
    fun insertImage(imageEntity: ImageEntity)

    @Query("select * from imageentity")
    fun getAllImage(): LiveData<List<ImageEntity>>

    @Delete
    fun deleteImage(imageEntity: ImageEntity)

    @Query("select * from imageentity where id=:id")
    fun getImageById(id: Int):ImageEntity

}