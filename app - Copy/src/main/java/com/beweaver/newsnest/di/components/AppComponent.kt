package com.beweaver.newsnest.di.components

import android.app.Application
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.di.modules.AppModule
import com.beweaver.newsnest.di.modules.NetworkModule
import com.beweaver.newsnest.ui.activities.MainActivity
import com.beweaver.newsnest.ui.activities.SplashActivity
import com.beweaver.newsnest.ui.activities.TermsActivity
import com.beweaver.newsnest.ui.fragments.ArticleFragment
import com.beweaver.newsnest.ui.fragments.BaseFragment
import com.beweaver.newsnest.ui.fragments.BookmarksFragment
import com.beweaver.newsnest.ui.fragments.MoreNewsFragment
import com.beweaver.newsnest.ui.fragments.SelectedNewsFragment
import com.beweaver.newsnest.ui.fragments.TopNewsFragment
import com.beweaver.newsnest.ui.fragments.WorldNewsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(application: NewsNestApplication)

    fun inject(activity: MainActivity)

    fun inject(activity: SplashActivity)

    fun inject(activity: TermsActivity)

    fun inject(fragment: BaseFragment)

    fun inject(fragment: TopNewsFragment)

    fun inject(fragment: WorldNewsFragment)

    fun inject(fragment: MoreNewsFragment)

    fun inject(fragment: BookmarksFragment)

    fun inject(fragment: ArticleFragment)

    fun inject(fragment: SelectedNewsFragment)
}