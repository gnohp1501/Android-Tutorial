package com.shoppinglist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_item")
data class ShoppingItem(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    var id: String,

    @ColumnInfo(name = "ItemName")
    var name: String,

    @ColumnInfo(name = "ItemAmount")
    var amount: Int
)