package com.beweaver.newsnest.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.R
import com.beweaver.newsnest.database.DBRepository
import com.beweaver.newsnest.databinding.FragmentTopNewsBinding
import com.beweaver.newsnest.databindingobservers.TopNewsViewObserver
import com.beweaver.newsnest.ui.fragments.adapters.NewsListAdapter
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import com.beweaver.newsnest.viewmodels.TopNewsViewModel
import javax.inject.Inject

class TopNewsFragment: BaseFragment() {

    lateinit var binding: FragmentTopNewsBinding
    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    @Inject
    lateinit var topNewsViewModel: TopNewsViewModel

    @Inject
    lateinit var viewObserver: TopNewsViewObserver

    @Inject
    lateinit var dbRepository: DBRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsNestApplication.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopNewsBinding.inflate(inflater, container, false)
        binding.viewObserver = viewObserver
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        toolbarViewModel.title = getString(R.string.tab_title_top_news)
        toolbarViewModel.backButtonVisibility = false

        topNewsViewModel.topNews.observe(this) {
            binding.topNewsList.adapter = it.channel?.let { channel ->
                NewsListAdapter(dbRepository, channel) {
                    it.link?.let { url ->
                        findNavController().navigate(TopNewsFragmentDirections.toArticleFragment().setNewsItemURL(url))
                    }
                }
            }
            viewObserver.loadingProgressBar = false
            binding.swipeRefresh.isRefreshing = false
        }

        binding.swipeRefresh.setOnRefreshListener {
            loadNews()
        }
        viewObserver.loadingProgressBar = true
        loadNews()
    }

    private fun loadNews() {
        topNewsViewModel.loadTopNews {
            showErrorDialog(requireContext(), it) {
                Handler(Looper.getMainLooper()).postDelayed({
                    loadNews()
                }, 1000)
            }
        }
    }
}