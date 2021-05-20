package com.wawra.mvpapp.ui.posts.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.wawra.mvpapp.R
import com.wawra.mvpapp.databinding.ItemPostBinding
import com.wawra.mvpapp.presentation.posts.PostDto

class PostViewHolder(
    itemView: View,
    private val listener: PostsListener?
) : ViewHolder(itemView) {

    private val binding = ItemPostBinding.bind(itemView)

    fun bind(post: PostDto) {
        binding.apply {
            itemPostTitle.text = post.title
            itemPostDescription.text = post.description
            itemPostDate.text = post.date
            loadImage(post.imageUrl)
            root.setOnClickListener { listener?.showDetails(post.id) }
        }
    }

    private fun loadImage(imageUrl: String) {
        Picasso.get().load(imageUrl)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(binding.itemPostImage)
    }
}
