package com.beweaver.newsnest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.R
import com.beweaver.newsnest.databinding.FragmentArticleBinding
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.inject.Inject


class ArticleFragment: Fragment() {

    lateinit var binding: FragmentArticleBinding

    val args: ArticleFragmentArgs by navArgs()

    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsNestApplication.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        toolbarViewModel.title = getString(R.string.tab_title_article)
        toolbarViewModel.backButtonVisibility = true

        binding.webView.webViewClient = object: WebViewClient() {

            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest
            ): WebResourceResponse? {
                val url = request.url.toString()
                if (url.contains("google") || url.contains("facebook") || url.contains("mgid")) {
                    val textStream = ByteArrayInputStream("".toByteArray())
                    return getTextWebResource(textStream)
                }
                return super.shouldInterceptRequest(view, request)
            }
        }

        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        binding.webView.loadUrl(args.newsItemURL)
    }

    private fun getTextWebResource(data: InputStream): WebResourceResponse? {
        return WebResourceResponse("text/plain", "UTF-8", data)
    }

}