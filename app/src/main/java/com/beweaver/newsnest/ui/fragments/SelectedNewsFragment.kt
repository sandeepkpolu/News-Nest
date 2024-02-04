package com.beweaver.newsnest.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.database.DBRepository
import com.beweaver.newsnest.databinding.FragmentSelectedNewsBinding
import com.beweaver.newsnest.databindingobservers.SelectedNewsViewObserver
import com.beweaver.newsnest.ui.fragments.adapters.NewsListAdapter
import com.beweaver.newsnest.viewmodels.SelectedNewsViewModel
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import javax.inject.Inject

class SelectedNewsFragment: BaseFragment() {

    lateinit var binding: FragmentSelectedNewsBinding
    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    @Inject
    lateinit var selectedNewsViewModel: SelectedNewsViewModel

    @Inject
    lateinit var dbRepository: DBRepository

    @Inject
    lateinit var viewObserver: SelectedNewsViewObserver

    val args: SelectedNewsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsNestApplication.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectedNewsBinding.inflate(inflater, container, false)
        binding.viewObserver = viewObserver
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        toolbarViewModel.title = args.selectedNews
        toolbarViewModel.backButtonVisibility = true
        selectedNewsViewModel.selectedNews.observe(this) {
            binding.selectedNewsList.adapter = it.channel?.let { channel ->
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
        selectedNewsViewModel.loadSelectedNews(args.selectedNews) {
            showErrorDialog(requireContext(), it) {
                Handler(Looper.getMainLooper()).postDelayed({
                    loadNews()
                }, 1000)
            }
        }
    }

}