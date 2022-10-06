package com.example.secondassignment

import android.content.Context
import androidx.lifecycle.LiveData

class Repository private constructor(applicationContext: Context) {
    private val dao = ItemsDataBase.getDatabase(applicationContext).getItemsDao()

    companion object{
        private lateinit var instance:Repository

        fun getInstance(context:Context): Repository {
            if (!::instance.isInitialized){
                instance = Repository(context)
            }
            return instance
        }
    }

    fun getAllItemsAsLiveData() : LiveData<List<Item>> {
        return dao.getAllItems()
    }

    fun addItem(item: Item){
        dao.insertItem(item)
    }

    fun updateItemImage(item: Item, imagePath: String, imageType: IMAGE_TYPE) {
        dao.updateItemImageUri(item, imagePath, imageType)
    }
}