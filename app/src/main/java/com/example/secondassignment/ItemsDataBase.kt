package com.example.secondassignment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class ItemsDataBase : RoomDatabase() {

    abstract fun getItemsDao():ItemsDao

    companion object{
        fun getDatabase(context: Context): ItemsDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                ItemsDataBase::class.java,
                "item_database"
            ).build()
        }
    }
}