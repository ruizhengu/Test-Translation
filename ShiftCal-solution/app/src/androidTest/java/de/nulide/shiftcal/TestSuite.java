package de.nulide.shiftcal;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.graphics.Point;
import android.view.KeyEvent;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestSuite {

    @Rule
    public ActivityScenarioRule<CalendarActivity> activityScenarioRule = new ActivityScenarioRule<>(CalendarActivity.class);

    /**
     * The application is a calendar for managing your employers and shifts.
     */

    UiDevice device = UiDevice.getInstance(getInstrumentation());

    @Test
    public void setShifts() {
        // 1. Click the menu icon in the top-right corner - Espresso
        onView(withId(R.id.btnPopup)).perform(click());
        // 2. Click "Employers" - Espresso
        onView(withText("Employers")).perform(click());
        // 3. Click the + icon on the bottom-right corner - Espresso
        onView(withId(R.id.fabAddEmployer)).perform(click());
        // 4. Enter "Sheffield" in the Name field (then press enter on the keyboard) - Espresso
        onView(withId(R.id.scEditTextName)).perform(typeText("Sheffield")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER));
        // 5. Click the ✓ icon - Espresso
        onView(withId(R.id.fabDoneEmployer)).perform(click());
        // 6. Press back to return to the homepage - Espresso
        pressBack();
        // 7. Click the menu icon in the top-right corner - Espresso
        onView(withId(R.id.btnPopup)).perform(click());
        // 8. Click "Shifts" - Espresso
        onView(withText("Shifts")).perform(click());
        // 9. Click the + icon on the bottom-right corner - Espresso
        onView(withId(R.id.fabAddShift)).perform(click());
        // 10. Enter "Weekday" in the Name field - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/scEditTextName")).setText("Weekday");
        // 11. Enter "WD" in the Short Name field - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/scEditTextSName")).setText("WD");
        // 12. Click the button of Start Time, click 9 then click OK - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/btnStartTime")).click();
        device.wait(Until.findObject(By.res("android:id/time_header")), 5000);
        device.findObject(By.desc("9")).click();
        device.wait(Until.findObject(By.desc("30")), 5000);
        device.findObject(By.text("OK")).click();
        device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/btnEndTime")), 5000);
        // 13. Click the button of End Time, click 17 then click OK - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/btnEndTime")).click();
        device.wait(Until.findObject(By.res("android:id/time_header")), 5000);
        device.findObject(By.desc("17")).click();
        device.wait(Until.findObject(By.desc("30")), 5000);
        device.findObject(By.text("OK")).click();
        device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabDoneShift")), 5000);
        // 14. Click the ✓ icon - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/fabDoneShift")).click();
        device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabAddShift")), 5000);
        // 15. Press back to return to the homepage - UI Automator
        device.pressBack();
        // 16. Click the Edit icon in the bottom-right corner of the homepage - Espresso
        onView(withId(R.id.fabEdit)).perform(click());
        // 17. Click the S icon - Espresso
        onView(withId(R.id.fabShiftSelector)).perform(click());
        // 18. Click "Weekday" - Espresso
        onView(withText("Weekday")).perform(click());
        // 19. Click date 30 of January 2023 - UI Automator
        device.findObject(By.desc("30")).click();
        // 20. Click the ✓ icon - Espresso
        onView(withId(R.id.fabEdit)).perform(click());
        // 21. Click the Edit icon in the bottom-right corner of the homepage - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/fabEdit")).click();
        device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabShiftSelector")), 5000);
        // 22. Click the WD icon - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/fabShiftSelector")).click();
        device.wait(Until.findObject(By.text("Weekday")), 5000);
        // 23. Click "Delete" - UI Automator
        device.findObject(By.text("Delete")).click();
        device.wait(Until.findObject(By.desc("30")), 5000);
        // 24. Click date 30 of January 2023 - UI Automator
        device.findObject(By.desc("30")).click();
        // 25. Click the ✓ icon - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/fabEdit")).click();
        device.wait(Until.gone(By.res("de.nulide.shiftcal:id/fabShiftSelector")), 5000);
        // 26. Click the menu icon in the top-right corner - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/btnPopup")).click();
        device.wait(Until.findObject(By.text("Employers")), 5000);
        // 27. Click "Shifts" - UI Automator
        device.findObject(By.text("Shifts")).click();
        device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabAddShift")), 5000);
        // 28. Long click "Weekday" - Espresso
        onView(withId(R.id.textViewSName)).perform(longClick());
        // 29. Click "Delete" - Espresso
        onView(withText("Delete")).perform(click());
        // 30. Press back to return to the homepage - Espresso
        pressBack();
        // 31. Click the menu icon in the top-right corner - UI Automator
        device.findObject(By.res("de.nulide.shiftcal:id/btnPopup")).click();
        device.wait(Until.findObject(By.text("Employers")), 5000);
        // 32. Click "Employers" - UI Automator
        device.findObject(By.text("Employers")).click();
        device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabAddEmployer")), 5000);
        // 33. Long click "Sheffield" - Espresso
        onView(withId(R.id.textViewName)).perform(longClick());
        // 34. Click "Delete" - UI Automator
        device.findObject(By.text("Delete")).click();
        device.wait(Until.gone(By.text("Delete")), 5000);
        // 35. Press back to return to the homepage - UI Automator
        device.pressBack();
    }
}
