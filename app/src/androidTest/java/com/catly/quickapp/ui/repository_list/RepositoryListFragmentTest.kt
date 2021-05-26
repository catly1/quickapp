package com.catly.quickapp.ui.repository_list

import android.os.Bundle
import androidx.constraintlayout.utils.widget.MockView
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.catly.quickapp.MainActivity
import com.example.quickapp.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryListFragmentTest(){


    @Before
    fun setUp(){
        val navControllerMock = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInContainer(Bundle(),R.style.Theme_AppCompat_DayNight_NoActionBar){
            RepositoryListFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever{ viewLifecycleOwner ->
                    if( viewLifecycleOwner != null) {
                        navControllerMock.setGraph(R.navigation.nav_graph)
                        Navigation.setViewNavController(fragment.requireView(), navControllerMock)
                    }
                }
            }
        }
    }

    @Test
    fun isRecyclerViewVisible(){
        onView(withId(R.id.recyclerViewRepoList)).check(matches(isDisplayed()))
    }



}