package com.shoppinglist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppinglist.model.ShoppingItem
import com.shoppinglist.repository.ShoppingRepository
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    fun insert(item: ShoppingItem) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun delete(item: ShoppingItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    fun getProducts() = repository.getProducts()

}