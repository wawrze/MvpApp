package com.wawra.mvpapp.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.wawra.mvpapp.R
import com.wawra.mvpapp.databinding.FragmentPostsBinding
import com.wawra.mvpapp.presentation.posts.*
import com.wawra.mvpapp.ui.base.BaseFragment
import com.wawra.mvpapp.ui.base.FragmentProvider
import com.wawra.mvpapp.ui.posts.adapter.PostsAdapter
import com.wawra.mvpapp.ui.posts.adapter.PostsListener
import com.wawra.mvpapp.utils.changeFragment
import com.wawra.mvpapp.utils.gone
import com.wawra.mvpapp.utils.show
import javax.inject.Inject


typealias BaseMvpFragment = BaseFragment<PostsPresentationModel, PostsView, PostsPresenter>

class PostsFragment : PostsView, PostsListener, BaseMvpFragment() {

    @Inject
    lateinit var fragmentProvider: FragmentProvider

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
            binding?.fragmentPostsSwipeLayout?.isRefreshing = false
            presenter.performAction(PostsViewInteraction.Refresh)
        }
        setupRecyclerDecorator()
    }

    private fun setupRecyclerDecorator() {
        context?.let { context ->
            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(context, R.drawable.divider)?.let {
                itemDecorator.setDrawable(it)
            }
            binding?.fragmentPostsRecycler?.addItemDecoration(itemDecorator)
        }
    }

    override fun showPosts(posts: List<PostDto>) {
        binding?.fragmentPostsRecycler?.adapter = PostsAdapter(posts, this as PostsListener)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
    }

    override fun showDetails(linkUrl: String, postIndex: Int?) {
        activity?.changeFragment(fragmentProvider.providePostDetailsFragment(linkUrl))
        postIndex?.let { binding?.fragmentPostsRecycler?.smoothScrollToPosition(it) }
    }

    override fun showLoading() {
        binding?.fragmentPostsProgressBar?.show()
    }

    override fun hideLoading() {
        binding?.fragmentPostsProgressBar?.gone()
    }

    override fun showDetails(postId: Long) {
        presenter.performAction(PostsViewInteraction.ShowDetails(postId))
    }

    override fun createPresentationModel() = PostsPresentationModel()
}
