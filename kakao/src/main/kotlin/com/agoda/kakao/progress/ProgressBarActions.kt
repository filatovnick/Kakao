@file:Suppress("unused")

package com.agoda.kakao.progress

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.common.actions.BaseActions

/**
 * Provides action for ProgressBar
 */
interface ProgressBarActions : BaseActions {
    /**
     * Set progress for ProgressBar
     *
     * @param number of progress to set for the ProgressBar
     */
    fun setProgress(number: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "set progress value of progress bar as: $number"

            override fun getConstraints() = ViewMatchers.isAssignableFrom(ProgressBar::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is ProgressBar) {
                    view.progress = number
                }
            }
        })
    }
}