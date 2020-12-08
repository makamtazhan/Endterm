package com.example.endterm

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("posts")
    fun getTodos(): Call<List<ToDo>>

    @GET("posts/{id}")
    fun getTodoById(@Path("id") todoId: Int): Call<ToDo>

    @GET("comments")
    fun getCommentsById(@Query("postId") todoId: Int): Call<List<Comment>>

}