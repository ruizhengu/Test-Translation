package com.example.toyaut

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val PACKAGE = "com.example.toyaut"
    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun startMainActivity() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            PACKAGE
        )
        context.startActivity(intent)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.toyaut", appContext.packageName)

//        val element = uiDevice.findObject(UiSelector().textContains("Hello Android!"))
        val element = uiDevice.findObject(By.text("Hello Android!"))
        element.click()
//        onView(withText("Hello Android!")).perform(click())
    }
}

