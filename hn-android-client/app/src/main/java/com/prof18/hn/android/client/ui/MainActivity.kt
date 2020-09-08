package com.prof18.hn.android.client.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.prof18.hn.android.client.R
import com.prof18.hn.android.client.adapter.NewsAdapter
import com.prof18.hn.android.client.data.model.NewsState

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var errorButton: Button

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))

        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)
        errorMessage = findViewById(R.id.error_message)
        errorButton = findViewById(R.id.error_button)

        val adapter = NewsAdapter()
        recyclerView.adapter = adapter

        viewModel.appState.observe(this, {
            it?.let { appState ->

                when (appState.newsState) {

                    is NewsState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        errorMessage.visibility = View.GONE
                        errorButton.visibility = View.GONE
                    }

                    is NewsState.Error -> {
                        val errorState = appState.newsState as NewsState.Error

                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.GONE

                        errorButton.visibility = View.VISIBLE
                        errorMessage.visibility = View.VISIBLE
                        errorMessage.text = errorState.reason
                        errorButton.setOnClickListener {
                            viewModel.loadData()
                        }
                    }

                    is NewsState.Success -> {
                        val successState = appState.newsState as NewsState.Success
                        progressBar.visibility = View.GONE
                        errorMessage.visibility = View.GONE
                        errorButton.visibility = View.GONE

                        recyclerView.visibility = View.VISIBLE
                        adapter.items = successState.news
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
        viewModel.loadData()
    }
}