package com.example.cursolinkedin

// Asegúrate de que todas las importaciones sean correctas
import MyListAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

class ThirdActivity : AppCompatActivity() {
    private lateinit var adapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_activity)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = mutableListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        adapter = MyListAdapter(data)
        recyclerView.adapter = adapter

        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition
                adapter.onItemMove(fromPos, toPos)
                return true // true if moved, false otherwise
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // no op
            }
        })
        touchHelper.attachToRecyclerView(recyclerView)
    }
}