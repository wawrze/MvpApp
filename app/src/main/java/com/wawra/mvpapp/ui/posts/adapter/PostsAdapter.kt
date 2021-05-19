package com.wawra.mvpapp.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.wawra.mvpapp.R
import com.wawra.mvpapp.presentation.posts.PostDto

class PostsAdapter(
    private val posts: List<PostDto>,
    private val listener: PostsListener?
) : Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false),
        listener
    )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        posts.getOrNull(position)?.let { holder.bind(it) }
    }

    override fun getItemCount() = posts.size
}
