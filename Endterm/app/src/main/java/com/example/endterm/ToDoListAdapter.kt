package com.example.endterm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(
    var todolist: MutableList<ToDo>,
    private val context: Context,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<ToDoListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todo = todolist[position]
        holder.titleText.text = todo.title
        holder.itemView.setOnClickListener {
            listener.itemClick(
                position,
                todo
            )
        }

    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleText: TextView = view.findViewById(R.id.title)

    }

    interface ItemClickListener {
        fun itemClick(position: Int, item: ToDo?)
    }
}