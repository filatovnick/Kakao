package com.agoda.sample

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.Kakao
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KTextView
import com.agoda.sample.screen.TestActivityScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class InterceptorTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(TestActivity::class.java)

    @Test
    fun testKakaoInterceptors() {
        val list = mutableListOf<String>()

        Kakao {
            intercept {
                onViewInteraction {
                    onAll { list.add("ALL") }
                    onCheck { _, _ -> list.add("CHECK") }
                    onPerform { _, _ -> list.add("PERFORM") }
                }
            }
        }

        onScreen<TestActivityScreen> {
            textViewLarge {
                isDisplayed()
                click()
            }
        }

        assertEquals(mutableListOf("ALL", "CHECK", "ALL", "PERFORM"), list)
    }

    @Test
    fun testScreenInterceptors() {
        onScreen<InterceptedTestScreen> {
            textViewLarge {
                isDisplayed()
                click()
            }

            assertEquals(mutableListOf("ALL", "CHECK", "ALL", "PERFORM"), list)
        }
    }

    @Test
    fun testViewInterceptors() {
        val list = mutableListOf<String>()

        onScreen<TestActivityScreen> {
            textViewLarge {
                intercept {
                    onAll { list.add("ALL") }
                    onCheck { _, _ -> list.add("CHECK") }
                    onPerform { _, _ -> list.add("PERFORM") }
                }

                isDisplayed()
                click()
            }
        }

        assertEquals(mutableListOf("ALL", "CHECK", "ALL", "PERFORM"), list)
    }

    @Test
    fun testNestedInterceptors() {
        val list = mutableListOf<String>()

        Kakao {
            intercept {
                onViewInteraction {
                    onAll { list.add("ALL_KAKAO") }
                    onCheck { _, _ -> list.add("CHECK_KAKAO") }
                    onPerform { _, _ -> list.add("PERFORM_KAKAO") }
                }
            }
        }

        onScreen<InterceptedTestScreen> {
            textViewLarge {
                intercept {
                    onAll { list.add("ALL_VIEW") }
                    onCheck { _, _ -> list.add("CHECK_VIEW") }
                    onPerform { _, _ -> list.add("PERFORM_VIEW") }
                }

                isDisplayed()
                click()
            }

            assert(this.list == mutableListOf("ALL", "CHECK", "ALL", "PERFORM"))
        }

        assertEquals(mutableListOf(
                "ALL_VIEW", "CHECK_VIEW", "ALL_KAKAO", "CHECK_KAKAO", "ALL_VIEW",
                "PERFORM_VIEW", "ALL_KAKAO", "PERFORM_KAKAO"
        ), list)
    }

    @Test
    fun testOverridingInterceptors() {
        val list = mutableListOf<String>()

        Kakao {
            intercept {
                onViewInteraction {
                    onAll { list.add("ALL_KAKAO") }
                    onCheck { _, _ -> list.add("CHECK_KAKAO") }
                    onPerform { _, _ -> list.add("PERFORM_KAKAO") }
                }
            }
        }

        onScreen<InterceptedTestScreen> {
            textViewLarge {
                intercept {
                    onAll { list.add("ALL_VIEW") }

                    onCheck(true) { interaction, assertion ->
                        list.add("CHECK_VIEW")
                        interaction.check(assertion)
                    }

                    onPerform(true) { interaction, action ->
                        list.add("PERFORM_VIEW")
                        interaction.perform(action)
                    }
                }

                isDisplayed()
                click()
            }

            assert(this.list.isEmpty())
        }

        assertEquals(mutableListOf("ALL_VIEW", "CHECK_VIEW", "ALL_VIEW", "PERFORM_VIEW"), list)
    }

    class InterceptedTestScreen : Screen<InterceptedTestScreen>() {
        val list = mutableListOf<String>()
        val textViewLarge = KTextView { withId(R.id.text_view_large) }

        init {
            intercept {
                onViewInteraction {
                    onAll { list.add("ALL") }
                    onCheck { _, _ -> list.add("CHECK") }
                    onPerform { _, _ -> list.add("PERFORM") }
                }
            }
        }
    }
}
