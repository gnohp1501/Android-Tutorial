package com.shoppinglist

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.shoppinglist.databinding.DialogShopBinding
import com.shoppinglist.model.ShoppingItem
import java.util.UUID

class ShoppingDialog(context: Context, var listener: DialogListener) : AppCompatDialog(context) {

    private val binding = DialogShopBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                return@setOnClickListener
            }

            val item = ShoppingItem(
                id = UUID.randomUUID().toString(),
                name = name,
                amount = amount.toInt()
            )
            listener.onAddButton(item)
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}

interface DialogListener {
    fun onAddButton(item: ShoppingItem)
}