import unittest
from appium import webdriver
from appium.webdriver.common.appiumby import AppiumBy
from appium.options.android import UiAutomator2Options

capabilities = dict(
    platformName='Android',
    automationName='uiautomator2',
    # automationName='espresso',
    deviceName='emulator-5554',
    appPackage='com.example.toyaut',
    # appPackage='com.android.settings',
    appActivity='.MainActivity',
    # appActivity='.Settings',
    language='en',
    locale='US'
)

appium_server_url = 'http://localhost:4723'

capabilities = UiAutomator2Options().load_capabilities(capabilities)


class TestAppium(unittest.TestCase):
    def setUp(self) -> None:
        self.driver = webdriver.Remote(appium_server_url, options=capabilities)

    def tearDown(self) -> None:
        if self.driver:
            self.driver.quit()

    def test_find_battery(self) -> None:
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Hello Android!"]')
        # el = self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="text(\"Hello Android\")")
        # el = self.driver.find_element(by=AppiumBy.ANDROID_VIEW_MATCHER, value="text(\"Hello Android!\")")
        el.click()


if __name__ == '__main__':
    unittest.main()
