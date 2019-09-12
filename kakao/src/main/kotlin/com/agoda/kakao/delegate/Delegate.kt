package com.agoda.kakao.delegate

import com.agoda.kakao.intercept.Interceptor

/**
 * Base delegate interface.
 *
 * Provides functionality of aggregating interceptors and invoking them on `check`
 * and `perform` invocations.
 *
 * @see Interceptor
 */
interface Delegate<INTERACTION, ASSERTION, ACTION> {
    var interaction: INTERACTION
    var interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>?

    fun screenInterceptors(): Iterable<Interceptor<INTERACTION, ASSERTION, ACTION>>
    fun kakaoInterceptor(): Interceptor<INTERACTION, ASSERTION, ACTION>?

    /**
     * Runs the interceptors available for the given delegate during the `check` operation.
     *
     * @return `true` if the call chain has been interrupted and there is no need to do Espresso call,
     *         false otherwise.
     */
    fun interceptCheck(assertion: ASSERTION): Boolean {
        fun intercept(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
            return interceptOnAll(interceptor) || interceptOnCheck(interceptor, assertion)
        }

        return interceptor?.let { intercept(it) } ?: false ||
                screenInterceptors().any { intercept(it) } ||
                kakaoInterceptor()?.let { intercept(it) } ?: false
    }

    /**
     * Runs the interceptors available for the given delegate during the `perform` operation.
     *
     * @return `true` if the call chain has been interrupted and there is no need to do Espresso call,
     *         false otherwise.
     */
    fun interceptPerform(action: ACTION): Boolean {
        fun intercept(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
            return interceptOnAll(interceptor) || interceptOnPerform(interceptor, action)
        }

        return interceptor?.let { intercept(it) } ?: false ||
                screenInterceptors().any { intercept(it) } ||
                kakaoInterceptor()?.let { intercept(it) } ?: false
    }

    private fun interceptOnAll(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
        return interceptor.onAll?.let { (isOverride, interception) ->
            interception(interaction)
            isOverride
        } ?: false
    }

    private fun interceptOnCheck(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>, assertion: ASSERTION): Boolean {
        return interceptor.onCheck?.let { (isOverride, interception) ->
            interception(interaction, assertion)
            isOverride
        } ?: false
    }

    private fun interceptOnPerform(interceptor: Interceptor<INTERACTION, ASSERTION, ACTION>, action: ACTION): Boolean {
        return interceptor.onPerform?.let { (isOverride, interception) ->
            interception(interaction, action)
            isOverride
        } ?: false
    }
}
