@file:Suppress("unused")

package com.agoda.kakao.common.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches RecyclerView descendant at given position in adapter
 *
 * @param parent Matcher of the recycler view
 * @param position Position of item in adapter
 */
class PositionMatcher(private val parent: Matcher<View>, private val position: Int)
    : BoundedMatcher<View, View>(View::class.java) {

    override fun describeTo(desc: Description) {
        desc.appendText("view holder at $position position of recycler view: ")
                .appendDescriptionOf(parent)
    }

    override fun matchesSafely(view: View?): Boolean {
        view?.let {
            if (parent.matches(it.parent) && it.parent is RecyclerView) {
                val holder = (it.parent as RecyclerView).findViewHolderForAdapterPosition(position)
                return holder?.itemView === it
            }
        }

        return false
    }
}