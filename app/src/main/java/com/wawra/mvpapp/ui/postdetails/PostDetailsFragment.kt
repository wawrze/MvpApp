package com.wawra.mvpapp.ui.postdetails

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wawra.mvpapp.databinding.FragmentPostDetailsBinding
import com.wawra.mvpapp.presentation.postdetails.PostDetailsPresentationModel
import com.wawra.mvpapp.presentation.postdetails.PostDetailsPresenter
import com.wawra.mvpapp.presentation.postdetails.PostDetailsView
import com.wawra.mvpapp.presentation.postdetails.PostDetailsViewInteraction
import com.wawra.mvpapp.ui.base.BaseFragment
import com.wawra.mvpapp.utils.gone
import com.wawra.mvpapp.utils.overrideOnBackPressed
import com.wawra.mvpapp.utils.show

typealias BaseMvpFragment = BaseFragment<PostDetailsPresentationModel, PostDetailsView, PostDetailsPresenter>

class PostDetailsFragment : PostDetailsView, BaseMvpFragment() {

    private var binding: FragmentPostDetailsBinding? = null
    private val webClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            presenter.performAction(PostDetailsViewInteraction.ChangeActiveUrl(url))
            presenter.performAction(PostDetailsViewInteraction.LinkLoading(false))
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            presenter.performAction(PostDetailsViewInteraction.LinkLoading(true))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.fragmentPostDetailsProgressBar?.show()
        binding?.fragmentPostDetailsWebView?.webViewClient = webClient
        activity?.overrideOnBackPressed {
            presenter.performAction(PostDetailsViewInteraction.BackPressed)
        }
    }

    override fun loadUrl(linkUrl: String) {
        binding?.fragmentPostDetailsWebView?.loadUrl(linkUrl)
    }

    override fun showLoading() {
        binding?.fragmentPostDetailsProgressBar?.show()
    }

    override fun close() {
        activity?.overrideOnBackPressed(null)
        activity?.onBackPressed()
    }

    override fun hideLoading() {
        binding?.fragmentPostDetailsProgressBar?.gone()
    }

    override fun createPresentationModel() = PostDetailsPresentationModel(
        arguments?.getString(LINK_URL_KEY) ?: ""
    )

    companion object {
        const val LINK_URL_KEY = "PostDetailsFragmentLinkUrlKey"
    }
}