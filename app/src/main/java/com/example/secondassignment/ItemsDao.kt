package com.example.secondassignment

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemsDao {

    @Insert
    fun insertItem(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("Select * from itemsTable")
    fun getAllItems(): LiveData<List<Item>>

    @Update
    fun updateItem(item: Item)

    fun updateItemImageUri(item: Item, imagePath: String, imageType: IMAGE_TYPE) {
        item.imagePath = imagePath
        item.imageType = imageType
        updateItem(item)
    }

}