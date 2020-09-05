package com.afroze.musicsearchapp.musicsearchapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrackModel (@SerializedName("resultCount")  val resultCount : Int,
                       @SerializedName("results")  val results : List<Track>)
