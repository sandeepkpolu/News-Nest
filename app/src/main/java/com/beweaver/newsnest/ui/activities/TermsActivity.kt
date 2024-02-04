package com.beweaver.newsnest.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.beweaver.newsnest.NewsNestApplication
import com.beweaver.newsnest.R
import com.beweaver.newsnest.databinding.ActivityTermsBinding
import com.beweaver.newsnest.viewmodels.ToolbarViewModel
import javax.inject.Inject

class TermsActivity: AppCompatActivity() {

    lateinit var binding: ActivityTermsBinding

    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsNestApplication.appComponent.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_terms)
        binding.toolbarViewModel = toolbarViewModel
        toolbarViewModel.title = getString(R.string.terms_conditions_title)
        setListeners()
    }

    private fun setListeners() {

        val appName = getString(R.string.app_title)
        val companyName = getString(R.string.org_name)
        val contactEmail = getString(R.string.contact_email)

        val termsAndConditions = String.format(getString(R.string.terms_and_conditions), appName, companyName, contactEmail)

        binding.termsConditionsText.text = SpannableStringBuilder(
            Html.fromHtml(termsAndConditions, Html.FROM_HTML_MODE_LEGACY))

        binding.termsAccept.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("TERMS_ACCEPTED", true)
            editor.commit()
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.termsDecline.setOnClickListener {
            finishAffinity()
        }
    }

}