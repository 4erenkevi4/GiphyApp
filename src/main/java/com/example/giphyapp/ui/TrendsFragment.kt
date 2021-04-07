package com.example.giphyapp.ui

import com.example.giphyapp.Adapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.giphyapp.R
import com.example.giphyapp.ViewModel
import com.example.giphyapp.constans.Constants
import com.example.giphyapp.repository.Repository

class TrendsFragment() : Fragment() {
    private lateinit var viewModel: ViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        Adapter {
            val intent = Intent(this.context, ShowGifActivity::class.java)
            intent.putExtra(Constants.GIF_URL, it.images.original.url)
            intent.putExtra(Constants.GIF_TITLE, it.title)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repo = Repository()
        val viewModelFactory = ViewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        val view = inflater.inflate(R.layout.trend_fragment, container, false)
        recyclerView = view.findViewById(R.id.rv_trends)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        viewModel.trendGifs()
        viewModel.TrendData.observe(viewLifecycleOwner, Observer { responce ->
            if (responce.isSuccessful) {
                responce.body()?.data?.let { adapter.setData(it) }
            }
        })
        return view
    }

    class ViewModelFactory(
        private val repository: Repository
    ) : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
            return ViewModel(repository) as T
        }
    }


}