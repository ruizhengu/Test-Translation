import unittest
from appium import webdriver
from appium.webdriver.common.appiumby import AppiumBy
from appium.options.android import UiAutomator2Options
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.actions import interaction
from selenium.webdriver.common.actions.action_builder import ActionBuilder
from selenium.webdriver.common.actions.pointer_input import PointerInput

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
        # el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Hello Android!"]')
        el = self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="text(\"Hello Android!\")")
        # el = self.driver.find_element(by=AppiumBy.ANDROID_VIEW_MATCHER, value="text(\"Hello Android!\")")
        # el.send_keys()
        self.long_click(el)

    def long_click(self, element):
        actions = ActionChains(self.driver)
        x = element.location["x"]
        y = element.location["y"]
        touch_input = PointerInput(interaction.POINTER_TOUCH, 'touch')
        actions.w3c_actions = ActionBuilder(self.driver, mouse=touch_input)
        actions.w3c_actions.pointer_action.move_to_location(x, y)
        actions.w3c_actions.pointer_action.pointer_down()
        actions.w3c_actions = ActionBuilder(self.driver, mouse=touch_input, duration=1000)
        actions.w3c_actions.pointer_action.move_to_location(x, y)
        actions.w3c_actions.pointer_action.release()
        actions.perform()


if __name__ == '__main__':
    unittest.main()
