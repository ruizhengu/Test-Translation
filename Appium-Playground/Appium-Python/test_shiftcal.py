import unittest

from appium import webdriver
from appium.options.android import UiAutomator2Options
from appium.webdriver.common.appiumby import By, AppiumBy
import time


class TestShiftCal(unittest.TestCase):
    def setUp(self):
        desired_caps = {
            'platformName': 'Android',
            'deviceName': 'emulator-5554',
            'appPackage': 'de.nulide.shiftcal',
            'appActivity': 'de.nulide.shiftcal.CalendarActivity',
            'automationName': 'UiAutomator2'
        }
        self.capabilities = UiAutomator2Options().load_capabilities(desired_caps)
        self.driver = webdriver.Remote('http://localhost:4723', options=self.capabilities)
        self.driver.implicitly_wait(5)  # manually added

    def tearDown(self) -> None:
        if self.driver:
            self.driver.quit()

    def test_set_shifts(self):
        # 1. Click the menu icon in the top-right corner - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/btnPopup').click()
        # 2. Click "Employers" - Espresso
        self.driver.find_element(By.XPATH, '//android.widget.TextView[@text="Employers"]').click()
        # 3. Click the + icon on the bottom-right corner - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabAddEmployer').click()
        # 4. Enter "Sheffield" in the Name field (then press enter on the keyboard) - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/scEditTextName').send_keys('Sheffield')
        self.driver.press_keycode(66)  # Enter key
        # 5. Click the ✓ icon - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabDoneEmployer').click()
        # 6. Press back to return to the homepage - Espresso
        self.driver.press_keycode(4)  # Back key
        # 7. Click the menu icon in the top-right corner - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/btnPopup').click()
        # 8. Click "Shifts" - Espresso
        self.driver.find_element(By.XPATH, '//android.widget.TextView[@text="Shifts"]').click()
        # 9. Click the + icon on the bottom-right corner - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabAddShift').click()
        # 10. Enter "Weekday" in the Name field - UI Automator
        # self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/scEditTextName').set_value('Weekday')
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/scEditTextName').send_keys('Weekday')
        # 11. Enter "WD" in the Short Name field - UI Automator
        # self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/scEditTextSName').set_value('WD')
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/scEditTextSName').send_keys('WD')
        # 12. Click the button of Start Time, click 9 then click OK - UI Automator
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/btnStartTime').click()
        time.sleep(1)  # Sleep to wait for the time picker
        # self.driver.find_element(By.ID, 'android:id/numberpicker_input').click()  # Click 9
        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR, value="description(\"9\")").click()
        self.driver.find_element(By.ID, 'android:id/button1').click()  # OK
        # 13. Click the button of End Time, click 17 then click OK - UI Automator
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/btnEndTime').click()
        time.sleep(1)  # Sleep to wait for the time picker
        # self.driver.find_element(By.ID, 'android:id/numberpicker_input').click()  # Click 17
        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR, value="description(\"17\")").click()
        self.driver.find_element(By.ID, 'android:id/button1').click()  # OK
        time.sleep(1)  # Sleep to wait for the button to appear
        # 14. Click the ✓ icon - UI Automator
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabDoneShift').click()
        time.sleep(1)  # Sleep to wait for the button to disappear
        # 15. Press back to return to the homepage - UI Automator
        self.driver.press_keycode(4)  # Back key
        # 16. Click the Edit icon in the bottom-right corner of the homepage - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabEdit').click()
        # 17. Click the S icon - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabShiftSelector').click()
        # 18. Click "Weekday" - Espresso
        self.driver.find_element(By.XPATH, '//android.widget.TextView[@text="Weekday"]').click()
        # 19. Click date 30 of January 2023 - UI Automator
        # self.driver.find_element(By.DESC, '30').click()
        self.driver.find_element(AppiumBy.ANDROID_UIAUTOMATOR, value="description(\"30\")").click()
        # 20. Click the ✓ icon - Espresso
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabEdit').click()
        time.sleep(1)
        # 21. Click the Edit icon in the bottom-right corner of the homepage - UI Automator
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabEdit').click()
        time.sleep(1)  # Sleep to wait for the button to appear
        # 22. Click the WD icon - UI Automator
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabShiftSelector').click()
        # 23. Click "Delete" - UI Automator
        # self.driver.find_element(By.TEXT, 'Delete').click()
        self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="text(\"Delete\")").click()
        time.sleep(1)  # Sleep to wait for the button to appear
        # 24. Click date 30 of January 2023 - UI Automator
        # self.driver.find_element(By.DESC, '30').click()
        self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="description(\"30\")").click()
        # 25. Click the ✓ icon - UI Automator
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/fabEdit').click()
        time.sleep(1)  # Sleep to wait for the button to disappear
        # 26. Click the menu icon in the top-right corner - UI Automator
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/btnPopup').click()
        time.sleep(1)  # Sleep to wait for the menu to appear
        # 27. Click "Shifts" - UI Automator
        # self.driver.find_element(By.TEXT, 'Shifts').click()
        self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="text(\"Shifts\")").click()
        time.sleep(1)  # Sleep to wait for the button to appear
        # 28. Long click "Weekday" - Espresso
        # self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/textViewSName').click_and_hold().release()
        text_view = self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/textViewSName')
        self.driver.execute_script("mobile: longClickGesture",
                                   {"x": text_view.location["x"], "y": text_view.location["y"], "duration": 1000})
        # 29. Click "Delete" - Espresso
        # self.driver.find_element(By.TEXT, 'Delete').click()
        self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="text(\"Delete\")").click()
        # 30. Press back to return to the homepage - Espresso
        self.driver.press_keycode(4)  # Back key
        time.sleep(1)  # Sleep to wait for the button to appear
        # 31. Click the menu icon in the top-right corner - UI Automator
        self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/btnPopup').click()
        time.sleep(1)  # Sleep to wait for the menu to appear
        # 32. Click "Employers" - UI Automator
        # self.driver.find_element(By.TEXT, 'Employers').click()
        self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="text(\"Employers\")").click()
        time.sleep(1)  # Sleep to wait for the button to appear
        # 33. Long click "Sheffield" - Espresso
        # self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/textViewName').click_and_hold().release()
        text_view = self.driver.find_element(By.ID, 'de.nulide.shiftcal:id/textViewName')
        self.driver.execute_script("mobile: longClickGesture",
                                   {"x": text_view.location["x"], "y": text_view.location["y"], "duration": 1000})
        # 34. Click "Delete" - UI Automator
        # self.driver.find_element(By.TEXT, 'Delete').click()
        self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="text(\"Delete\")").click()
        time.sleep(1)  # Sleep to wait for the button to disappear
        # 35. Press back to return to the homepage - UI Automator
        self.driver.press_keycode(4)  # Back key

    # def long_click(self):
    #     action = ActionChains()


if __name__ == '__main__':
    unittest.main()
