package com.example.endterm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {
    private lateinit var title: TextView
    private lateinit var body: TextView
    private lateinit var item: ToDo

    val args = arguments?.let { DetailFragmentArgs.fromBundle(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = view.findViewById(R.id.title)
        body = view.findViewById(R.id.body)
        val idToDo = arguments?.getInt("ToDoId")!!
        getById(idToDo)
        back.setOnClickListener()
        {
            val action = DetailFragmentDirections.actionDetailToTodo()
            view.findNavController().navigate(action)
        }

        comments.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("ToDoId", idToDo)
            val action = DetailFragmentDirections.actionDetailToAddToDo()
            view.findNavController().navigate(R.id.addListFragment, bundle)
        }
    }

    private fun getById(id: Int) {

        ApiClient.getApiService().getTodoById(id).enqueue(object : Callback<ToDo> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<ToDo>?,
                response: Response<ToDo>
            ) {
                if (response.isSuccessful) {
                    item = response.body()!!
                    title.text = item.title
                    body.text = item.body
                }
            }

            override fun onFailure(call: Call<ToDo>?, t: Throwable) {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
            }
        })
    }
}
