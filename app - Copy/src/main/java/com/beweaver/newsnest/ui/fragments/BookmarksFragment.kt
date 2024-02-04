package com.beweaver.newsnest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.R
import com.beweaver.newsnest.database.DBNewsItem
import com.beweaver.newsnest.databinding.FragmentBookmarksBinding
import com.beweaver.newsnest.ui.fragments.adapters.DBNewsListAdapter
import com.beweaver.newsnest.viewmodels.BookmarksViewModel
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import javax.inject.Inject

class BookmarksFragment: Fragment() {

    lateinit var binding: FragmentBookmarksBinding
    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    @Inject
    lateinit var bookmarksViewModel: BookmarksViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsNestApplication.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        toolbarViewModel.title = getString(R.string.tab_title_bookmarks)
        toolbarViewModel.backButtonVisibility = false
        bookmarksViewModel.bookmarkNewsItems.observe(this) {

            if (it.isEmpty()) {
                binding.noBookmarksText.visibility = View.VISIBLE
                binding.bookmarksList.visibility = View.GONE
            } else {
                binding.noBookmarksText.visibility = View.GONE
                binding.bookmarksList.visibility = View.VISIBLE
                binding.bookmarksList.adapter = it.let { bookmarks ->
                    DBNewsListAdapter(bookmarks as MutableList<DBNewsItem>,  {
                        it.link.let { url ->
                            findNavController().navigate(
                                BookmarksFragmentDirections.toArticleFragment().setNewsItemURL(url)
                            )
                        }
                    }, {
                        binding.noBookmarksText.visibility = View.VISIBLE
                        binding.bookmarksList.visibility = View.GONE
                    })
                }
            }
        }

        bookmarksViewModel.loadBookmarkNews()
    }

}