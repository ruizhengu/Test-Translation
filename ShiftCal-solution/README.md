# ShiftCal - Practice Solution

In this document, the implementation process of the test case *setShifts* will be detailed, including the reasons for choosing a testing framework to perform the step or not.

## Environment

Emulator Device: Pixel 6

Android Version: API 26, Android 8.0

Application: *ShiftCal*

## Test Steps

### 1. Click the menu icon in the top-right corner.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/activity_calendar.xml*

```java
onView(withId(R.id.btnPopup)).perform(click());
```

**UIAutomator**

Creating UiDevice object.

```java
UiDevice device = UiDevice.getInstance(getInstrumentation());
```

**Please note**: It is recommended to add a waiting step between two actions from two different **windows**, including activities, menus, dialogs.

```java
device.findObject(By.res("de.nulide.shiftcal:id/btnPopup")).click();
device.wait(Until.findObject(By.text("Employers")), 5000);
```

### 2. Click "Employers".

Options: Espresso & UIAutomator

**EspressoClick "Employers"**

```java
onView(withText("Employers")).perform(click());
```

**UIAutomator**

```java
device.findObject(By.text("Employers")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabAddEmployer")), 5000);
```

### 3. Click the + icon on the bottom-right corner.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/activity_employers.xml*

```java
onView(withId(R.id.fabAddEmployer)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabAddEmployer")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/textView3")), 5000);
```

### 4. Enter "Sheffield" in the Name field (then press enter on the keyboard).

#### 4.1 Enter "Sheffield" in the Name field 

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_employer_creator.xml*

```java
onView(withId(R.id.scEditTextName)).perform(typeText("Sheffield"));
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/scEditTextName")).setText("Sheffield");
```

#### 4.2 Press enter

Option: Espresso & UIAutomator

**Espresso**

Extending the Espresso code of **4.1**.

```java
// pressing enter key
onView(withId(R.id.scEditTextName)).perform(typeText("Sheffield")).perform(ViewActions.pressKey(KeyEvent.KEYCODE_ENTER));
// pressing the current action button on the Input Method Editor, in this case, pressing enter
onView(withId(R.id.scEditTextName)).perform(typeText("Sheffield")).perform(pressImeActionButton());
```

Closing the keyboard is also feasible.

```java
closeSoftKeyboard();
```

**UIAutomator**

```java
device.pressEnter();
```

### 5. Click the ✓ icon.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/activity_employer_creator.xml*

```java
onView(withId(R.id.fabDoneEmployer)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabDoneEmployer")).click();
```

### 6. Press back to return to the homepage.

Options: Espresso & UIAutomator

**Espresso**

```java
pressBack();
```

**UIAutomator**

```java
device.pressBack();
```

### 7. Click the menu icon in the top-right corner.

Same as [Step 1](#1-click-the-menu-icon-in-the-top-right-corner).

### 8. Click "Shifts".

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withText("Employers")).perform(click());
```

**UIAutomator**

```java
device.findObject(By.text("Shifts")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabAddShift")), 5000);
```

### 9. Click the + icon on the bottom-right corner.

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withId(R.id.fabAddShift)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabAddShift")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/scEditTextName")), 5000);
```

### 10. Enter "Weekday" in the Name field (then press enter on the keyboard).

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_shift_creator.xml*

```java
onView(withId(R.id.scEditTextName)).perform(typeText("Weekday"));
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/scEditTextName")).setText("Weekday");
```

### 11. Enter "WD" in the Short Name field.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_shift_creator.xml*

```java
onView(withId(R.id.scEditTextSName)).perform(typeText("WD"));
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/scEditTextSName")).setText("WD");
```

### 12. Click the button of Start Time, click 9 then click OK.

#### 12.1 Click the button of Start Time

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_shift_creator.xml*

```java
onView(withId(R.id.btnStartTime)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/btnStartTime")).click();
device.wait(Until.findObject(By.res("android:id/time_header")), 5000);
```

#### 12.2 Click 9

Options: UIAutomator

Reason: The time widget is a customised widget(not accessible to Espresso).

**UIAutomator**

```java
device.findObject(By.desc("9")).click();
device.wait(Until.findObject(By.desc("30")), 5000);
```

#### 12.3 Click OK

Options: UIAutomator

**UIAutomator**

```java
device.findObject(By.text("OK")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/btnEndTime")), 5000);
```

### 13. Click the button of End Time, click 17 then click OK.

#### 13.1 Click the button of End Time

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_shift_creator.xml*

```java
onView(withId(R.id.btnEndTime)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/btnEndTime")).click();
device.wait(Until.findObject(By.res("android:id/time_header")), 5000);
```

#### 13.2 Click 17

Options: UIAutomator

Reason: The time widget is a customised widget(not accessible to Espresso).

**UIAutomator**

```java
device.findObject(By.desc("17")).click();
device.wait(Until.findObject(By.desc("30")), 5000);
```

#### 13.3 Click OK

Options: UIAutomator

**UIAutomator**

```java
device.findObject(By.text("OK")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabDoneShift")), 5000);
```

### 14. Click the ✓ icon.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/activity_shift_creator.xml*

```java
onView(withId(R.id.fabDoneShift)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabDoneShift")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabAddShift")), 5000);
```

### 15. Press back to return to the homepage.

Same as [Step 6](#6-press-back-to-return-to-the-homepage).

### 16. Click the Edit icon in the bottom-right corner of the homepage.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_calendar.xml*

```java
onView(withId(R.id.fabEdit)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabEdit")).click();
device.wait(Until.findObject(By.res("de.nulide.shiftcal:id/fabShiftSelector")), 5000);
```

### 17. Click the S icon.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_calendar.xml*

```java
onView(withId(R.id.fabShiftSelector)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabShiftSelector")).click();
device.wait(Until.findObject(By.text("Weekday")), 5000);
```

### 18. Click "Weekday".

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withText("Weekday")).perform(click());
```

**UIAutomator**

```java
device.findObject(By.text("Weekday")).click();
device.wait(Until.findObject(By.desc("30")), 5000);
```

### 19. Click date 30 of January 2023.

Options: UIAutomator

Reason: The time widget is a customised widget(not accessible to Espresso).

**UIAutomator**

```java
device.findObject(By.desc("30")).click();
```

### 20. Click the ✓ icon.

Options: Espresso & UIAutomator

**Espresso**

View Android resource location: *res/layout/content_calendar.xml*

```java
onView(withId(R.id.fabEdit)).perform(click());
```

**UIAutomator**

```java
device.findObject(By.res("de.nulide.shiftcal:id/fabEdit")).click();
device.wait(Until.gone(By.res("de.nulide.shiftcal:id/fabShiftSelector")), 5000);
```

### 21. Click the Edit icon in the bottom-right corner of the homepage.

Same as [Step 16](#16-click-the-edit-icon-in-the-bottom-right-corner-of-the-homepage).

### 22. Click the WD icon.

Same as [Step 17](#17-click-the-s-icon).

### 23. Click "Delete".

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withText("Delete")).perform(click());
```

**UIAutomator**

```java
device.findObject(By.text("Delete")).click();
device.wait(Until.findObject(By.desc("30")), 5000);
```

### 24. Click date 30 of January 2023.

Same as [Step 19](#19-click-date-30-of-january-2023).

### 25. Click the ✓ icon.

Same as [Step 20](#20-click-the--icon).

### 26. Click the menu icon in the top-right corner.

Same as [Step 1](#1-click-the-menu-icon-in-the-top-right-corner).

### 27. Click "Shifts".

Same as [Step 8](#8-click-shifts).

### 28. Long click "Weekday".

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withId(R.id.textViewSName)).perform(longClick());
```

**UIAutomator**

The *longClick* API of UiObject2 is buggy, you could use the following approach to perform a long click(if you want).

```java
// get the center positoin of the target view
Point x = device.findObject(By.res("de.nulide.shiftcal:id/textViewName")).getVisibleCenter();
// using swipe API, the start position and the end position is the center position of the target view
// So for a 100 steps, the swipe will take about 1/2 second to complete.
device.swipe(x.x, x.y, x.x, x.y, 200);
```

### 29. Click "Delete".

Options: Espresso & UIAutomator

**Espresso**

```java
onView(withText("Delete")).perform(click());
```

**UIAutomator**

```java
device.findObject(By.text("Delete")).click();
device.wait(Until.gone(By.text("Delete")), 5000);
```

### 30. Press back to return to the homepage.

Same as [Step 6](#6-press-back-to-return-to-the-homepage).

### 31. Click the menu icon in the top-right corner.

Same as [Step 1](#1-click-the-menu-icon-in-the-top-right-corner).

### 32. Click "Employers".

Same as [Step 2](#2-click-employers).

### 33. Long click "Sheffield".

Same as [Step 28](#28-long-click-weekday).

### 34. Click "Delete".

Same as [Step 29](#29-click-delete).

### 35. Press back to return to the homepage.

Same as [Step 6](#6-press-back-to-return-to-the-homepage).
