package com.example.hackeruapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignment.IMAGE_TYPE
import com.example.secondassignment.Item
import com.example.secondassignment.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

object ImagesManager {

    fun getImageFromGallery(item: Item, getContent: ActivityResultLauncher<Intent>) {
        Log.d("Test", "Click on ${item.title}")
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        getContent.launch(intent)
    }

    fun onImageResultFromGallery(
        result: ActivityResult,
        chosenItem: Item,
        context: Context
    ) {
        Log.d("Test", "got content: $result")
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val uri = result.data?.data
            if (uri != null) {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                addImageToItem(chosenItem, uri.toString(), IMAGE_TYPE.URI, context)
            }
        }
    }

    fun addImageToItem(item: Item, imagePath: String, imageType: IMAGE_TYPE, context: Context) {
        thread(start = true) {
            Repository.getInstance(context).updateItemImage(item, imagePath, imageType)
        }
    }

    fun getImageFromApi(item: Item, context: Context) {
        val retrofit = ApiInterface.create()
        retrofit.getImages(item.title).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val apiResponse = response.body()
                val apiImage = apiResponse!!.imagesList[3]
                addImageToItem(item, apiImage.imageUrl, IMAGE_TYPE.URL, context)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Wrong api response", t.message.toString())
            }
        })
    }

    fun displayImagesAlertDialog(
        context: Context,
        item: Item,
        getContent: ActivityResultLauncher<Intent>
    ) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Hey I am title")
        alertDialogBuilder.setMessage("choose image for ${item.title}")
        alertDialogBuilder.setNeutralButton("cancel") { dialogInterface: DialogInterface, i: Int -> }
        alertDialogBuilder.setPositiveButton("Gallery") { dialogInterface: DialogInterface, i: Int ->
            getImageFromGallery(item, getContent)
        }
        alertDialogBuilder.setNegativeButton("Network") { dialogInterface: DialogInterface, i: Int ->
            getImageFromApi(item, context)
        }


        alertDialogBuilder.show()
    }
}