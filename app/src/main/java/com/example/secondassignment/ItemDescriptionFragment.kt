package com.example.secondassignment

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class ItemDescriptionFragment: Fragment(R.layout.item_description_fragment) {

    override fun onResume() {
        super.onResume()
        val activity = requireActivity()
        val editText = activity.findViewById<EditText>(R.id.desc_edit)
        val button = activity.findViewById<Button>(R.id.text_edit_button)
        val newText = requireArguments().getString("theDescription")
        editText.hint = newText
    }
}