package com.example.giphyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyapp.dataModel.Data

class Adapter(val click: (Data) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var list = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_gif,
                parent,
                false
            )
        return ViewHolder(view, OnClik = click)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.glide(item)
        holder.OnClick(item)
    }

    fun setData(setList: List<Data>) {
        this.list = setList
        notifyDataSetChanged()
    }

    class ViewHolder(
        view: View, val OnClik: (Data) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val root: View = view.findViewById(R.id.item_gif)

        fun glide(data: Data) {
            Glide
                .with(root.context)
                .load(data.images.original.url)
                .into(root.findViewById(R.id.item_gif))
        }

        fun OnClick(position: Data) {
            root.setOnClickListener {
                OnClik(position)
            }
        }
    }
}