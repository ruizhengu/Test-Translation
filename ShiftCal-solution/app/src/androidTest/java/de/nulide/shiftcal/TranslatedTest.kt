package de.nulide.shiftcal

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)

class TranslatedTest {
    private val PACKAGE = "de.nulide.shiftcal"
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
    fun testSetShifts() {
//        val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // 1. Click the menu icon in the top-right corner - Espresso
        onView(withId(R.id.btnPopup)).perform(click())

        // 2. Click "Employers" - Espresso
        onView(withText("Employers")).perform(click())

        // 3. Click the + icon on the bottom-right corner - Espresso
        onView(withId(R.id.fabAddEmployer)).perform(click())

        // 4. Enter "Sheffield" in the Name field (then press enter on the keyboard) - Espresso
        onView(withId(R.id.scEditTextName)).perform(typeText("Sheffield"), pressKey(66))

        // 5. Click the ✓ icon - Espresso
        onView(withId(R.id.fabDoneEmployer)).perform(click())

        // 6. Press back to return to the homepage - Espresso
        uiDevice.pressBack()

        // 7. Click the menu icon in the top-right corner - Espresso
        onView(withId(R.id.btnPopup)).perform(click())

        // 8. Click "Shifts" - Espresso
        onView(withText("Shifts")).perform(click())

        // 9. Click the + icon on the bottom-right corner - Espresso
        onView(withId(R.id.fabAddShift)).perform(click())

        // 10. Enter "Weekday" in the Name field - UI Automator
        uiDevice.findObject(UiSelector().resourceId("de.nulide.shiftcal:id/scEditTextName"))
            .text = "Weekday"

        // 11. Enter "WD" in the Short Name field - UI Automator
        uiDevice.findObject(UiSelector().resourceId("de.nulide.shiftcal:id/scEditTextSName"))
            .text = "WD"

        // 12. Click the button of Start Time, click 9 then click OK - UI Automator
        uiDevice.findObject(UiSelector().resourceId("de.nulide.shiftcal:id/btnStartTime")).click()
        uiDevice.findObject(UiSelector().description("9")).click()
        uiDevice.findObject(UiSelector().resourceId("android:id/button1")).click()

        // 13. Click the button of End Time, click 17 then click OK - UI Automator
        uiDevice.findObject(UiSelector().resourceId("de.nulide.shiftcal:id/btnEndTime")).click()
        uiDevice.findObject(UiSelector().description("17")).click()
        uiDevice.findObject(UiSelector().resourceId("android:id/button1")).click()

        // 14. Click the ✓ icon - UI Automator
        uiDevice.findObject(UiSelector().resourceId("de.nulide.shiftcal:id/fabDoneShift")).click()

        // 15. Press back to return to the homepage - UI Automator
        uiDevice.pressBack()

        // 16. Click the Edit icon in the bottom-right corner of the homepage - Espresso
        onView(withId(R.id.fabEdit)).perform(click())

        // 17. Click the S icon - Espresso
        onView(withId(R.id.fabShiftSelector)).perform(click())

        // 18. Click "Weekday" - Espresso
        onView(withText("Weekday")).perform(click())

        // 19. Click date 30 of January 2023 - UI Automator
        uiDevice.findObject(UiSelector().description("30")).click()

        // 20. Click the ✓ icon - Espresso
        onView(withId(R.id.fabEdit)).perform(click())

        // 21. Click the Edit icon in the bottom-right corner of the homepage - UI Automator
        onView(withId(R.id.fabEdit)).perform(click())

        // 22. Click the WD icon - UI Automator
        uiDevice.findObject(UiSelector().resourceId("de.nulide.shiftcal:id/fabShiftSelector"))
            .click()

        // 23. Click "Delete" - UI Automator
        uiDevice.findObject(UiSelector().text("Delete")).click()

        // 24. Click date 30 of January 2023 - UI Automator
        uiDevice.findObject(UiSelector().description("30")).click()

        // 25. Click the ✓ icon - UI Automator
        onView(withId(R.id.fabEdit)).perform(click())

        // 26. Click the menu icon in the top-right corner - UI Automator
        onView(withId(R.id.btnPopup)).perform(click())

        // 27. Click "Shifts" - UI Automator
        onView(withText("Shifts")).perform(click())

        // 28. Long click "Weekday" - Espresso
        val text_view =
            uiDevice.findObject(UiSelector().resourceId("de.nulide.shiftcal:id/textViewSName"))
//        uiDevice.executeScript(
//            "mobile: longClickGesture",
//            mapOf(
//                "x" to text_view.bounds.centerX(),
//                "y" to text_view.bounds.centerY(),
//                "duration" to 1000
//            )
//        )
        uiDevice.swipe(
            text_view.bounds.centerX(),
            text_view.bounds.centerY(),
            text_view.bounds.centerX(),
            text_view.bounds.centerY(),
            100
        )

        // 29. Click "Delete" - Espresso
        uiDevice.findObject(UiSelector().text("Delete")).click()

        // 30. Press back to return to the homepage - Espresso
        uiDevice.pressBack()

        // 31. Click the menu icon in the top-right corner - UI Automator
        onView(withId(R.id.btnPopup)).perform(click())

        // 32. Click "Employers" - UI Automator
        onView(withText("Employers")).perform(click())

        // 33. Long click "Sheffield" - Espresso
        val text_view_name =
            uiDevice.findObject(UiSelector().resourceId("de.nulide.shiftcal:id/textViewName"))
//        uiDevice.executeScript(
//            "mobile: longClickGesture",
//            mapOf(
//                "x" to text_view_name.bounds.centerX(),
//                "y" to text_view_name.bounds.centerY(),
//                "duration" to 1000
//            )
//        )
        uiDevice.swipe(
            text_view_name.bounds.centerX(),
            text_view_name.bounds.centerY(),
            text_view_name.bounds.centerX(),
            text_view_name.bounds.centerY(),
            100
        )


        // 34. Click "Delete" - UI Automator
        uiDevice.findObject(UiSelector().text("Delete")).click()

        // 35. Press back to return to the homepage - UI Automator
        uiDevice.pressBack()
    }
}