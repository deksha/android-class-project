package com.example.secondassignment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {

    @Insert
    fun insertItem(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("Select * from itemsTable")
    fun getAllItems(): LiveData<List<Item>>

}