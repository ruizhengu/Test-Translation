package de.nulide.shiftcal

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Playground {
    private var device: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @get:Rule
    val activityRule = ActivityScenarioRule(CalendarActivity::class.java)

    @Test
    fun setShifts() {
        onView(withId(R.id.btnPopup)).perform(click())
        onView(withText("Shifts")).perform(click())
        onView(withId(R.id.fabAddShift)).perform(click())
        // find children
        device.findObject(By.res("de.nulide.shiftcal:id/shift_creator_layout")).findObject(By.res("de.nulide.shiftcal:id/scEditTextName"))
        onData(withId(R.id.shift_creator_layout)).onChildView(withId(R.id.scEditTextName))
        // find element that match multiple selectors
        device.findObject(By.text("")).findObject(By.res("de.nulide.shiftcal:id/scEditTextName"))
        onView(allOf(withId(R.id.scEditTextName), withText("")))
    }
}