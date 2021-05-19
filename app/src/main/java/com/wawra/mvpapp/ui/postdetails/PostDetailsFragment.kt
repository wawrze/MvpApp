package com.wawra.mvpapp.ui.postdetails

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
import com.wawra.mvpapp.ui.base.BaseFragment
import com.wawra.mvpapp.utils.gone
import com.wawra.mvpapp.utils.show

typealias BaseMvpFragment = BaseFragment<PostDetailsPresentationModel, PostDetailsView, PostDetailsPresenter>

class PostDetailsFragment : PostDetailsView, BaseMvpFragment() {

    private var binding: FragmentPostDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun loadUrl(linkUrl: String) {
        binding?.fragmentPostDetailsProgressBar?.show()
        binding?.fragmentPostDetailsWebView?.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                presenter.setLinkLoaded()
            }
        }
        binding?.fragmentPostDetailsWebView?.loadUrl(linkUrl)
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