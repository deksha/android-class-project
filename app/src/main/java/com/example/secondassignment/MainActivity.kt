package com.example.secondassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackeruapp.ImagesManager
import com.example.hackeruapp.NotificationsManager
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var chosenItem :Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_main)
        onAddButtonClickListener()
        //buttonsClickListeners()
        createRecyclerView()
    }

    private fun createNewItem(): Item {
        val itemTitle = findViewById<EditText>(R.id.text).text.toString()
        val itemDesc = findViewById<EditText>(R.id.description).text.toString()
        val item = Item(itemTitle,itemDesc)
        thread (start = true){
            Repository.getInstance(this).addItem(item)
        }
        return item
    }

    private fun onAddButtonClickListener(){
        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            val item = createNewItem()
            NotificationsManager.display(this, item)
        }
    }

    private fun displayItemDetailsFragment(item: Item){
        val itemFragment = ItemFragment()
        val bundle = bundleOf("theItemTitle" to item.title, "theItemImage" to item.imagePath, "theItemDescription" to item.description)
        itemFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, itemFragment)
            .show(itemFragment)
            .commit()

        val mainScreen = findViewById<ConstraintLayout>(R.id.main_screen)
        mainScreen.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .hide(itemFragment)
                .commit()
        }
    }

    private fun onItemTitleClick(): (item:Item) -> Unit = {
        displayItemDetailsFragment(it)
    }
    private fun onItemImageClick(): (item:Item) -> Unit = { item ->
        chosenItem = item
        ImagesManager.displayImagesAlertDialog(this, item, getContentFromGallery)
    }

    val getContentFromGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            ImagesManager.onImageResultFromGallery(result, chosenItem!!, this)
        }

    private fun createRecyclerView(){
        val listView = findViewById<RecyclerView>(R.id.myList)
        val adapter = MyAdapter(arrayListOf(), onItemTitleClick(), onItemImageClick(), this)
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this)
        val notesListLiveData = Repository.getInstance(this).getAllItemsAsLiveData()
        notesListLiveData.observe(this){
            adapter.adapterUpdateTheView(it)
        }
    }

}

