package com.example.recycler_view_practice

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.recycler_view_practice.databinding.CustomDialogBinding
import com.example.recycler_view_practice.databinding.RecommendRecyclerviewItemBinding

class EditDialogFragment(val list : ArrayList<RecommendModel>) : DialogFragment() {
    //todo recommendModel을 파라미터로 받아서 값을 바인딩 시켜주자.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val binding = CustomDialogBinding.inflate(inflater)

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.custom_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.save,
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                        val title = binding.titleEdit.toString()
                        val singer = binding.singerEdit.toString()
                        list.add(RecommendModel(R.drawable.music,title,singer))

                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()!!.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
