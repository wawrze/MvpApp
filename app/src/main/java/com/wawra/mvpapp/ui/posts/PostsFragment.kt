package com.wawra.mvpapp.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.wawra.mvpapp.databinding.FragmentPostsBinding
import com.wawra.mvpapp.domain.models.Post
import com.wawra.mvpapp.presentation.posts.PostsPresentationModel
import com.wawra.mvpapp.presentation.posts.PostsPresenter
import com.wawra.mvpapp.presentation.posts.PostsView
import com.wawra.mvpapp.ui.base.BaseFragment

typealias BaseMvpFragment = BaseFragment<PostsPresentationModel, PostsView, PostsPresenter>

class PostsFragment : PostsView, BaseMvpFragment() {

    private var binding: FragmentPostsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.fragmentPostsText?.setOnClickListener { presenter.refreshPosts() }
    }

    override fun showPosts(posts: List<Post>) {
        Toast.makeText(
            requireContext(),
            "SUCCESS, ${posts.size} POSTS DOWNLOADED AND SAVEDf",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
    }

    override fun createPresentationModel() = PostsPresentationModel()
}
