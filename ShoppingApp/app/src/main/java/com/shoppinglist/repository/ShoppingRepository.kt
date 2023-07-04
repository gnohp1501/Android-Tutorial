package com.shoppinglist.repository

import com.shoppinglist.database.ShoppingDatabase
import com.shoppinglist.model.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {

    suspend fun insert(item: ShoppingItem) = db.getShoppingDao().insert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getProducts() = db.getShoppingDao().getAll()
}