package com.afroze.musicsearchapp.musicsearchapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient  {

     private const val BASE_URL = "https://itunes.apple.com/"

     private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                              .addConverterFactory(GsonConverterFactory.create())
                              .build()

     val itunesApiSearch: ItunesApiSearch = retrofit.create(ItunesApiSearch::class.java)


}