package com.beweaver.newsnest.ui.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.R
import com.beweaver.newsnest.database.DBNewsItem
import com.beweaver.newsnest.database.DBRepository
import com.beweaver.newsnest.database.DatabaseEvent
import com.beweaver.newsnest.databinding.ActivityMainBinding
import com.beweaver.newsnest.viewmodels.MainActivityViewModel
import com.beweaver.newsnest.viewmodels.MainViewModelProviderFactory
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    @Inject
    lateinit var dbRepository: DBRepository

    lateinit var mainActivityViewModel: MainActivityViewModel

    private val bookmarkTaskReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

            intent.action?.let {
                when(intent.action) {
                    DatabaseEvent.SAVE_BOOKMARK -> {
                        val dbNewsItem = intent.getSerializableExtra(DatabaseEvent.DATABASE_TASK_ARG) as DBNewsItem
                        mainActivityViewModel.saveNewsItem(dbNewsItem)
                    }
                    DatabaseEvent.DELETE_BOOKMARK -> {
                        val dbNewsItem = intent.getSerializableExtra(DatabaseEvent.DATABASE_TASK_ARG) as DBNewsItem
                        mainActivityViewModel.deleteNewsItemByLink(dbNewsItem.link)
                    }
                    DatabaseEvent.DATABASE_TASK -> {
                        Toast.makeText(context, "Database task", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DaggerApp
        NewsNestApplication.appComponent.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        mainActivityViewModel = ViewModelProvider(this,
            MainViewModelProviderFactory(dbRepository))[MainActivityViewModel::class.java]

        val navController: NavController = findNavController(R.id.navi_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
        binding.toolbarViewModel = toolbarViewModel
        toolbarViewModel.title = getString(R.string.app_name)
        binding.toolBar.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(bookmarkTaskReceiver, IntentFilter(DatabaseEvent.SAVE_BOOKMARK))
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(bookmarkTaskReceiver, IntentFilter(DatabaseEvent.DELETE_BOOKMARK))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bookmarkTaskReceiver)
    }

}