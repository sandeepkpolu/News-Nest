package com.beweaver.newsnest.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.R
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMEOUT: Long = 2000 // 2 seconds

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsNestApplication.appComponent.inject(this)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val launchIntent: Intent
            val termsStatus = sharedPreferences.getBoolean("TERMS_ACCEPTED", false)
            launchIntent = if (termsStatus) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, TermsActivity::class.java)
            }
            startActivity(launchIntent)
            finish()
        }, SPLASH_TIMEOUT)
    }
}