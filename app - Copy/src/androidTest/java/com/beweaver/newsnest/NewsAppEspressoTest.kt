package com.beweaver.newsnest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.beweaver.newsnest.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsAppEspressoTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testTopNewsTab() {
        // Click on the "Top News" tab
        onView(withId(R.id.topNewsFragment)).perform(click())

        // Verify that the RecyclerView is displayed
        onView(withId(R.id.top_news_list))
            .waitUntilVisible(5000)
            .check(matches(isDisplayed()))

        // Click on a news item in the "Top News" tab
        onView(withId(R.id.top_news_list))
            .waitUntilVisible(5000)
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Verify that the details view is displayed
        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
    }

    @Test
    fun testWorldNewsTab() {
        // Click on the "Top News" tab
        onView(withId(R.id.worldNewsFragment)).perform(click())

        // Verify that the RecyclerView is displayed
        onView(withId(R.id.world_news_list))
            .waitUntilVisible(5000)
            .check(matches(isDisplayed()))

        // Click on a news item in the "Top News" tab
        onView(withId(R.id.world_news_list))
            .waitUntilVisible(5000)
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Verify that the details view is displayed
        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
    }

    @Test
    fun testBookmarksTab() {
        // Click on the "Top News" tab
        onView(withId(R.id.bookmarksFragment)).perform(click())

        // Verify that the RecyclerView is displayed
        onView(withId(R.id.bookmarks_list))
            .waitUntilVisible(5000)
            .check(matches(isDisplayed()))

        // Click on a news item in the "Top News" tab
        onView(withId(R.id.bookmarks_list))
            .waitUntilVisible(5000)
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Verify that the details view is displayed
        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
    }

    @Test
    fun testMoreNewsTab() {
        // Click on the "More News" tab
        onView(withId(R.id.moreNewsFragment)).perform(click())

        // Verify that the ListView is displayed
        onView(withId(R.id.news_category_list))
            .waitUntilVisible(5000)
            .check(matches(isDisplayed()))

        // Click on an item in the "More News" tab
        onView(withText("US")).perform(click())

        // Verify that the details view for the selected item is displayed
        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
    }

}
