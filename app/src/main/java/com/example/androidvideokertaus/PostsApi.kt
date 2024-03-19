package com.example.androidvideokertaus

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val postsService = retrofit.create(PostsApi::class.java)


interface PostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostItem>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int) : List<CommentItem>
}