package com.wawra.mvpapp.ui.base

import android.content.Context
import android.support.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wawra.mvpapp.R

class DialogProvider(private val context: Context) {

    fun showError(@StringRes stringRes: Int) {
        showError(context.getString(stringRes))
    }

    fun showError(message: String) {
        showDialog(context.getString(R.string.error_title), message)
    }

    fun showDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
