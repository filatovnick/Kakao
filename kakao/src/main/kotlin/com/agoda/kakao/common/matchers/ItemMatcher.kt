@file:Suppress("unused")

package com.agoda.kakao.common.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches first RecyclerView descendant which matches specific matcher
 *
 * @param parent Matcher of the recycler view
 * @param item Matcher of the item in adapter
 */
class ItemMatcher(private val parent: Matcher<View>, private val item: Matcher<View>)
    : BoundedMatcher<View, View>(View::class.java) {

    var position = -1

    override fun describeTo(desc: Description) {
        desc.appendText("view holder: ")
                .appendDescriptionOf(item)
                .appendText("of recycler view: ")
                .appendDescriptionOf(parent)
    }

    override fun matchesSafely(view: View?): Boolean {
        view?.let {
            if (parent.matches(it.parent) && it.parent is RecyclerView) {
                if (item.matches(it)) {
                    position = (it.parent as RecyclerView).getChildAdapterPosition(it)
                    return true
                }
            }
        }

        return false
    }
}