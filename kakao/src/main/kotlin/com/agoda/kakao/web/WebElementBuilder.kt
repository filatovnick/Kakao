@file:Suppress("unused")

package com.agoda.kakao.web

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.model.ElementReference
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.Locator
import com.agoda.kakao.common.KakaoDslMarker
import com.agoda.kakao.delegate.WebInteractionDelegate
import com.agoda.kakao.intercept.Interceptable

/**
 * Class for building WebView element matchers
 *
 * @param web WebInteraction where elements should be matched
 */
@KakaoDslMarker
class WebElementBuilder(override val view: WebInteractionDelegate) : Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>> {
    /**
     * Looks up web view element and performs actions/assertions on it
     *
     * @param locator Locator of web view element
     * @param value Value to be searched for in web view
     * @param interaction Tail lambda where you can perform actions/assertions
     */
    fun withElement(locator: Locator, value: String, interaction: KWebInteraction.() -> Unit) {
        val ref = DriverAtoms.findElement(locator, value)
        KWebInteraction(view, ref).apply(interaction)
    }

    inner class KWebInteraction(override val web: WebInteractionDelegate, override val ref: Atom<ElementReference>) :
            WebActions, WebAssertions
}
