package com.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoppinglist.database.ShoppingDatabase
import com.shoppinglist.databinding.ActivityShoppingBinding
import com.shoppinglist.model.ShoppingItem
import com.shoppinglist.repository.ShoppingRepository
import com.shoppinglist.viewmodel.ShoppingViewModel
import com.shoppinglist.viewmodel.ShoppingViewModelFactory
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory : ShoppingViewModelFactory by instance()


    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProviders.of(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        binding.rcvShoppingItem.layoutManager = LinearLayoutManager(this)
        binding.rcvShoppingItem.adapter = adapter

        viewModel.getProducts().observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        binding.fabButton.setOnClickListener {
            ShoppingDialog(this,
                object : DialogListener {
                    override fun onAddButton(item: ShoppingItem) {
                        viewModel.insert(item)
                    }

                }).show()
        }
    }
}