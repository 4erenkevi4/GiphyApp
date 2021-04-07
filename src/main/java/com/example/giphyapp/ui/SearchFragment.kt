package com.example.giphyapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.giphyapp.Adapter
import com.example.giphyapp.R
import com.example.giphyapp.ViewModel
import com.example.giphyapp.constans.Constants
import com.example.giphyapp.repository.Repository

class SearchFragment : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var viewModel: ViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var searshButton: AppCompatImageButton
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
        val viewModelFactory = TrendsFragment.ViewModelFactory(repo)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        recyclerView = view.findViewById(R.id.rv_search)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        searchEditText = view.findViewById(R.id.search_edit_text)
        searshButton = view.findViewById(R.id.search_button)
        searshButton.setOnClickListener {
            val result = searchEditText.text.toString()
            viewModel.searshGifs(result)
            viewModel.searshData.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    response.body()?.data?.let { adapter.setData(it) }
                }
            })
        }
        return view
    }
}