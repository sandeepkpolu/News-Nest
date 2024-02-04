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
import com.beweaver.newsnest.databinding.FragmentWorldNewsBinding
import com.beweaver.newsnest.databindingobservers.WorldNewsViewObserver
import com.beweaver.newsnest.ui.fragments.adapters.NewsListAdapter
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import com.beweaver.newsnest.viewmodels.WorldNewsViewModel
import javax.inject.Inject

class WorldNewsFragment: BaseFragment() {

    lateinit var binding: FragmentWorldNewsBinding
    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    @Inject
    lateinit var worldNewsViewModel: WorldNewsViewModel

    @Inject
    lateinit var viewObserver: WorldNewsViewObserver

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
        binding = FragmentWorldNewsBinding.inflate(inflater, container, false)
        binding.viewObserver = viewObserver
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        toolbarViewModel.title = getString(R.string.tab_title_world_news)
        toolbarViewModel.backButtonVisibility = false
        worldNewsViewModel.worldNews.observe(this) {
            binding.worldNewsList.adapter = it.channel?.let { channel ->
                NewsListAdapter(dbRepository, channel) {
                    it.link?.let { url ->
                        findNavController().navigate(WorldNewsFragmentDirections.toArticleFragment().setNewsItemURL(url))
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
        worldNewsViewModel.loadWorldNews {
            showErrorDialog(requireContext(), it) {
                Handler(Looper.getMainLooper()).postDelayed({
                    loadNews()
                }, 1000)
            }
        }
    }

}