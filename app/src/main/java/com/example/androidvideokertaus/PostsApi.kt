package com.example.androidvideokertaus

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofitClient = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val postsService = retrofitClient.create(PostsApi::class.java)

interface PostsApi {
    // https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    suspend fun getPosts(): List<PostItem>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int) : List<CommentItem>
}