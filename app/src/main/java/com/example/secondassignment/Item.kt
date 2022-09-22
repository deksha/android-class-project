package com.example.secondassignment

class Item(val title:String, val image:Int, val description:String) {

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