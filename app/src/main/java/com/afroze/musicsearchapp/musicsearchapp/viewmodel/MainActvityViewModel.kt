package com.afroze.musicsearchapp.musicsearchapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afroze.musicsearchapp.musicsearchapp.model.Track
import com.afroze.musicsearchapp.musicsearchapp.model.TrackModel
import com.afroze.musicsearchapp.musicsearchapp.repository.SongsRepository

class MainActvityViewModel(application: Application): AndroidViewModel(application) {

    private val songsRepository = SongsRepository(application)
    val showProgress : LiveData<Boolean>
    val trackList : LiveData<List<Track>>

    init {
       this.showProgress = songsRepository.showProgress
        this.trackList = songsRepository.trackList
    }

    fun changeState(){
        songsRepository.changeState()
    }

    fun searchArtist(artist: String) {
        songsRepository.searchArtist(artist)
    }

}