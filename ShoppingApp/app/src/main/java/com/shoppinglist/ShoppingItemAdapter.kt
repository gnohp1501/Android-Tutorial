package com.shoppinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoppinglist.databinding.ShoppingItemBinding
import com.shoppinglist.model.ShoppingItem
import com.shoppinglist.viewmodel.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val itemBinding =
            ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.bind(curShoppingItem)
    }

    inner class ShoppingViewHolder(private val itemBinding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(curShoppingItem: ShoppingItem) {
            with(itemBinding) {
                tvName.text = curShoppingItem.name
                tvAmount.text = "${curShoppingItem.amount}"

                ivDelete.setOnClickListener {
                    viewModel.delete(curShoppingItem)
                }

                ivPlus.setOnClickListener {
                    curShoppingItem.amount++
                    viewModel.insert(curShoppingItem)
                }

                ivMinus.setOnClickListener {
                    if (curShoppingItem.amount > 0) {
                        curShoppingItem.amount--
                        viewModel.insert(curShoppingItem)
                    }
                }
            }

        }
    }
}