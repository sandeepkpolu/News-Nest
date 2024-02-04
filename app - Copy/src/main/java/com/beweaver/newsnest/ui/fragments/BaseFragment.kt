package com.beweaver.newsnest.ui.fragments

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.beweaver.newsnest.R

open class BaseFragment: Fragment() {
    protected fun showErrorDialog(context: Context, error: String, onRetry: () -> Unit) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(getString(R.string.error_dialog_title))
        alertDialogBuilder.setMessage(error)

        alertDialogBuilder.setPositiveButton(R.string.error_dialog_try_again) { _, _ ->
            onRetry()
        }
        alertDialogBuilder.setNegativeButton(R.string.error_dialog_exit) { _, _ ->
           activity?.finish()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}