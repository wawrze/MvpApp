package com.wawra.mvpapp.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.wawra.mvpapp.databinding.FragmentPostsBinding
import com.wawra.mvpapp.presentation.posts.*
import com.wawra.mvpapp.ui.base.BaseFragment
import com.wawra.mvpapp.ui.posts.adapter.PostsAdapter
import com.wawra.mvpapp.ui.posts.adapter.PostsListener

typealias BaseMvpFragment = BaseFragment<PostsPresentationModel, PostsView, PostsPresenter>

class PostsFragment : PostsView, PostsListener, BaseMvpFragment() {

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
        binding?.fragmentPostsSwipeLayout?.setOnRefreshListener {
            presenter.performAction(PostsViewInteraction.Refresh)
        }
    }

    override fun showPosts(posts: List<PostDto>) {
        binding?.fragmentPostsRecycler?.adapter = PostsAdapter(posts, this as PostsListener)
        Toast.makeText(
            requireContext(),
            "${posts.size} POSTS LOADED",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
    }

    override fun showDetails(linkUrl: String) {
        Toast.makeText(
            requireContext(),
            "SHOULD OPEN LINK: $linkUrl",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showLoading() {
        binding?.fragmentPostsSwipeLayout?.isRefreshing = true
    }

    override fun hideLoading() {
        binding?.fragmentPostsSwipeLayout?.isRefreshing = false
    }

    override fun showDetails(postId: Long) {
        presenter.performAction(PostsViewInteraction.ShowDetails(postId))
    }

    override fun createPresentationModel() = PostsPresentationModel()
}
