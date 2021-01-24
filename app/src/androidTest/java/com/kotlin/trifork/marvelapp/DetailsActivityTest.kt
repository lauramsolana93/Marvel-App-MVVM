package com.kotlin.trifork.marvelapp

import androidx.annotation.UiThread
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.kotlin.trifork.marvelapp.ui.activity.CharactersActivity
import com.kotlin.trifork.marvelapp.ui.activity.DetailsActivity
import com.kotlin.trifork.marvelapp.ui.fragment.SeriesFragment
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import kotlinx.android.synthetic.main.activity_details.*
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailsActivityTest {

    @Rule
    @JvmField
    var detailsRule = ActivityTestRule(DetailsActivity::class.java)

    @Rule
    @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @Before
    fun init(){
        detailsRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun checkView(){
        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFragmentView(){
        onView(withId(R.id.comicsRv)).check(matches(isDisplayed()))
    }

}
