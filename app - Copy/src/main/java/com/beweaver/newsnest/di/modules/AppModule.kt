package com.beweaver.newsnest.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.beweaver.newsnest.database.DBRepository
import com.beweaver.newsnest.database.NewsItemDBRepository
import com.beweaver.newsnest.database.NewsItemDatabase
import com.beweaver.newsnest.viewmodels.BookmarksViewModel
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return  context.getSharedPreferences("NEWS_NEST_PREFS", 0)
    }

    @Provides
    @Singleton
    fun provideToolbarViewModel(): ToolbarViewModel {
        return ToolbarViewModel()
    }

    @Provides
    @Singleton
    fun provideNewsItemDBRepository() : DBRepository {
        val dao = NewsItemDatabase.getInstance(context.applicationContext)
            .newsItemDao()
        return NewsItemDBRepository(dao)
    }

    @Provides
    @Singleton
    fun provideBookmarksViewModel(repository: DBRepository): BookmarksViewModel {
        return BookmarksViewModel(repository)
    }

}