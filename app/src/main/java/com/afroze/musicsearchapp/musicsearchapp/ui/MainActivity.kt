package com.afroze.musicsearchapp.musicsearchapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afroze.musicsearchapp.R
import com.afroze.musicsearchapp.musicsearchapp.adapter.SongAdapter
import com.afroze.musicsearchapp.musicsearchapp.model.Track
import com.afroze.musicsearchapp.musicsearchapp.viewmodel.MainActvityViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
         val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var viewModel:MainActvityViewModel
    lateinit var songAdapter: SongAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActvityViewModel::class.java)

        viewModel.showProgress.observe(this, Observer {
            if(it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        })

        viewModel.trackList.observe(this, Observer {
            songAdapter.displayArtistList(it)
        })
        songAdapter = SongAdapter(this)
        recyclerView.adapter = songAdapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

    }

    fun searchArtist(view: View) {
        if(searchArtist.isNotEmpty(inputLayoutName)) {
            viewModel.searchArtist(searchArtist.text.toString())
        }
    }

    private fun EditText.isNotEmpty(textInputLayout: TextInputLayout) : Boolean
    {
        return if(text.toString().isEmpty()) {
            textInputLayout.error = "Please Enter Artist name"
            false
        }
        else{
            textInputLayout.isErrorEnabled = false
            true
        }
    }
}