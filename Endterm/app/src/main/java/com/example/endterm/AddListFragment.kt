package com.example.endterm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_add_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddListFragment : Fragment() {
    lateinit var commentList: MutableList<Comment>
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var viewAdapter: CommentAdapter
    private lateinit var viewManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_list, container, false)
        val idToDo = arguments?.getInt("ToDoId")!!
        commentList = ArrayList()
        myRecyclerView = rootView.findViewById(R.id.myRecyclerView)
        viewManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager = viewManager
        val dividerItemDecoration = DividerItemDecoration(
            myRecyclerView.context,
            viewManager.orientation
        )
        myRecyclerView.addItemDecoration(dividerItemDecoration)

        rootView.back.setOnClickListener {
            val action = AddListFragmentDirections.actionAddTodoToDetail()
            rootView.findNavController().navigate(action)
        }

        viewAdapter = context?.let {
            CommentAdapter(
                commentList, it,
            )
        }!!
        myRecyclerView.adapter = viewAdapter
        viewAdapter.notifyDataSetChanged()
        getCommentList(idToDo)
        return rootView
    }

    private fun getCommentList(id: Int) {
        val list = ArrayList<Comment>()
        ApiClient.getApiService().getCommentsById(id).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                list.addAll(response.body() as ArrayList<Comment>)
                viewAdapter.commentList = list
                viewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
            }

        })
    }
}
