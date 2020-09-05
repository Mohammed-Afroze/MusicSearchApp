package com.afroze.musicsearchapp.musicsearchapp.network

import com.afroze.musicsearchapp.musicsearchapp.model.Track
import com.afroze.musicsearchapp.musicsearchapp.model.TrackModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApiSearch {

    @GET("search")
    fun getTracks(@Query("term") term: String) : Call<TrackModel>
}
