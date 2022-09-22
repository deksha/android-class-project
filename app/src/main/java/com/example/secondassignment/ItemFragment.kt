package com.example.secondassignment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class ItemFragment: Fragment(R.layout.item_fragment) {

    override fun onResume() {
        super.onResume()
        val activity = requireActivity()
        val itemTitleTextView = activity.findViewById<TextView>(R.id.fragment_title)
        val itemImageView = activity.findViewById<ImageView>(R.id.fragment_image)
        val itemDescTextView = activity.findViewById<TextView>(R.id.fragment_desc)
        val title = requireArguments().getString("theItemTitle")
        val image = requireArguments().getInt("theItemImage")
        val description = requireArguments().getString("theItemDescription")
        itemTitleTextView.text = title
        itemImageView.setImageResource(image)
        itemDescTextView.text = description
    }

}