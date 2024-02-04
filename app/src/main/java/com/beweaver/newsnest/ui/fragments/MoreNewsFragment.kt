package com.beweaver.newsnest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.R
import com.beweaver.newsnest.databinding.FragmentMoreNewsBinding
import com.beweaver.newsnest.ui.fragments.adapters.MoreNewsListAdapter
import com.beweaver.newsnest.viewmodels.MoreNewsViewModel
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import javax.inject.Inject

class MoreNewsFragment: Fragment() {

    lateinit var binding: FragmentMoreNewsBinding
    private val moreNewsViewModel: MoreNewsViewModel by viewModels()

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
        binding = FragmentMoreNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        toolbarViewModel.title = getString(R.string.tab_title_more_news)
        toolbarViewModel.backButtonVisibility = false
        val newsAdapter = MoreNewsListAdapter(moreNewsViewModel.loadItems(requireContext())) {
            findNavController().navigate(MoreNewsFragmentDirections.toSelectedNewsFragment().setSelectedNews(it.name))
        }

        binding.newsCategoryList.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = newsAdapter
        }

    }
}