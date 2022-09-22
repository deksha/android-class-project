package com.example.secondassignment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemsTable")
class Item(
    @ColumnInfo(name = "title")val title:String,
    @ColumnInfo(name = "image")val image:Int,
    @ColumnInfo(name = "description")val description:String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    fun getImage(image: Int): Int {
        if (image == 1){
            return (R.drawable.bannana)
        } else if (image == 2){
            return (R.drawable.strawberry)
        } else {
            return (R.drawable.peach)
        }
    }
}