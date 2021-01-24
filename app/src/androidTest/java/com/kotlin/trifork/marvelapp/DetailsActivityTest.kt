package com.kotlin.trifork.marvelapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.kotlin.trifork.marvelapp.ui.activity.DetailsActivity
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailsActivityTest {

    @Rule
    @JvmField
    var detailsRule = ActivityTestRule(DetailsActivity::class.java)

    @Rule
    @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @Before
    fun init() {
        detailsRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun checkView() {
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentView() {
        onView(withId(R.id.comicsRv)).check(matches(isDisplayed()))
    }

}
