package com.example.endterm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(
    var commentList: MutableList<Comment>,
    private val context: Context
) :
    RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val comment = commentList[position]
        holder.name.text = comment.name
        holder.email.text = comment.email
        holder.body.text = comment.body
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var email: TextView = view.findViewById(R.id.email)
        var body: TextView = view.findViewById(R.id.body)

    }
}