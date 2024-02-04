package com.beweaver.newsnest

import android.app.Application
import com.beweaver.newsnest.di.components.AppComponent
import com.beweaver.newsnest.di.components.DaggerAppComponent
import com.beweaver.newsnest.di.modules.AppModule

class NewsNestApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var application: NewsNestApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }

}