package com.example.secondassignment

import android.app.Application
import androidx.lifecycle.LiveData

class Repository(application: Application) {

    private val dao = ItemsDataBase.getDatabase(application).getItemsDao()

    fun getAllItemsAsLiveData() : LiveData<List<Item>> {
        return dao.getAllItems()
    }

    fun addItem(item: Item){
        dao.insertItem(item)
    }
}