package com.afroze.musicsearchapp.musicsearchapp.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.afroze.musicsearchapp.musicsearchapp.model.Track
import com.afroze.musicsearchapp.musicsearchapp.model.TrackModel
import com.afroze.musicsearchapp.musicsearchapp.network.ItunesApiSearch
import com.afroze.musicsearchapp.musicsearchapp.network.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongsRepository(val application: Application) {

    val showProgress =  MutableLiveData<Boolean>()
    val trackList = MutableLiveData<List<Track>>()
    private val itunesApiSearch : ItunesApiSearch by lazy {
        RetrofitClient.itunesApiSearch
    }

    fun changeState(){
        showProgress.value = !(showProgress.value!=null && showProgress.value!!)
    }

    fun searchArtist(artist: String) {
        showProgress.value = true
        itunesApiSearch.getTracks(artist).enqueue(object : Callback<TrackModel>{
            override fun onFailure(call: Call<TrackModel>, t: Throwable) {
                showProgress.value = false
                Log.i("songsRepositories",""+t.message)
                Toast.makeText(application, " Error while accessing the API", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<TrackModel>, response: Response<TrackModel>) {
             //   Log.i("songsRepositories", "Response :${Gson().toJson(response.body().toString())}")
                Log.i("songsRepositories", "Response :${response.body().toString()}")
                trackList.value = response.body()?.results
                showProgress.value = false
            }

        })
    }
}