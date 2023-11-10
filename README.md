# Test-Translation

# Appium

## Overview

* Appium is based on [WebDriver](https://www.selenium.dev/documentation/webdriver/).

## Drivers

1. The drivers mapping the WebDriver protocol to the underlying APIs.

2. **All languages will be translated into the same REST API calls.**

#### Locator - XPATH

```python
el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Battery"]')
```

```sh
[AndroidUiautomator2Driver@3ba6 (53499867)] Calling AppiumDriver.findElement() with args: ["xpath","//*[@text=\"Battery\"]","53499867-b83b-4a55-8e39-df11c4ae8a75"]

......

[AndroidUiautomator2Driver@3ba6 (53499867)] Proxying [POST /element] to [POST http://127.0.0.1:8200/session/3e9b85a5-991d-4c6e-a3a8-9f67ac16a3e8/element] with body: {"strategy":"xpath","selector":"//*[@text=\"Battery\"]","context":"","multiple":false}
```

### Appium UiAutomator2 Driver

#### Capabilities

* **appium:automationName:** uiautomator2

#### Locator - UiSelector

```python
el = self.driver.find_element(by=AppiumBy.ANDROID_UIAUTOMATOR, value="text(\"Battery\")")
```

```sh
[AndroidUiautomator2Driver@84b1 (f947d479)] Calling AppiumDriver.findElement() with args: ["-android uiautomator","text(\"Battery\")","f947d479-e27b-4e7f-8802-fdd10cb2f006"]

......

[AndroidUiautomator2Driver@84b1 (f947d479)] Proxying [POST /element] to [POST http://127.0.0.1:8201/session/33a13a14-eaaf-48cd-8ccf-3b35f7ede44a/element] with body: {"strategy":"-android uiautomator","selector":"text(\"Battery\")","context":"","multiple":false}
```

```sh
# click action
[AndroidUiautomator2Driver@e4ff (ab5f39dc)] Calling AppiumDriver.click() with args: ["00000000-0000-0082-0000-000300000004","ab5f39dc-6efb-431f-a0fa-e33a1586a253"]
[AndroidUiautomator2Driver@e4ff (ab5f39dc)] Matched '/element/00000000-0000-0082-0000-000300000004/click' to command name 'click'
[AndroidUiautomator2Driver@e4ff (ab5f39dc)] Proxying [POST /element/00000000-0000-0082-0000-000300000004/click] to [POST http://127.0.0.1:8200/session/1e78cd56-cea9-4b22-afb2-aee4987fd94d/element/00000000-0000-0082-0000-000300000004/click] with body: {"element":"00000000-0000-0082-0000-000300000004"}
[AndroidUiautomator2Driver@e4ff (ab5f39dc)] Got response with status 200: {"sessionId":"1e78cd56-cea9-4b22-afb2-aee4987fd94d","value":null}
[AndroidUiautomator2Driver@e4ff (ab5f39dc)] Responding to client with driver.click() result: null
[HTTP] <-- POST /session/ab5f39dc-6efb-431f-a0fa-e33a1586a253/element/00000000-0000-0082-0000-000300000004/click 200 655 ms - 14
```

### Appium Espresso Drivers

* The driver (written in Node.js) ensures the communication between the Espresso server and Appium.
* The server part (written in Kotlin and Java) is running on the device under test and **transforms REST API calls into low-level Espresso commands**.

## Idea

* No matter what kind of locator each action is using, we should always translate it by a strategy that can maximise the quality of test code (e.g. fewer test LOCs, lesser execution time).

# ChatGPT Translation

## Case 1

Appium Python Test Code (XPATH locator) to Espresso Kotlin Test Code

* Prompt

  *translate this Appium call to UI Automator test code in Kotlin* 

* Original Appium Python Test Code

```python
el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Hello Android!"]')
```

* Translated Espresso Kotlin Test Code

```kotlin
onView(withText("Hello Android!")).perform(click())
```

* Results

  The test case can pass, but the generated code is not equivalent. The original code does not contain **click** action.

## Case 2

AppiumDriver API call to UI Automator Kotlin Test code

* Prompt

  *Translate this Appium test code to UI Automator or Espresso in Kotlin. If the test code does not involve cross-application interactions, translate it to Espresso, otherwise, translate it to UI Automator.*

* Original AppiumDriver API Call

  ```shell
  [AndroidUiautomator2Driver@5be1 (63a9e4f8)] Calling AppiumDriver.findElement() with args: ["xpath","//*[@text=\"Hello Android!\"]","63a9e4f8-125d-4822-8532-2a3136b4fb83"]
  ```

* Translated UI Automator Test Code

  ```kotlin
  val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
  
  // Using UiSelector to find an element by text containing "Hello Android!"
  val element = uiDevice.findObject(UiSelector().textContains("Hello Android!"))
  
  // Perform actions on the found element
  element.click()
  ```

* Results

  ChatGPT generates code in both UI Automator and Espresso.

  The test can pass, but the generated code is not equivalent. The original locator is **text** while the translated one is **textContains**.

## Case 3

AppiumDriver API call to UI Automator Kotlin Test code using **By** selector

* Prompt

​	*translate this Appium call to UI Automator test code in Kotlin using By selector*

* Original AppiumDriver API Call

  ```shell
  [AndroidUiautomator2Driver@5be1 (63a9e4f8)] Calling AppiumDriver.findElement() with args: ["xpath","//*[@text=\"Hello Android!\"]","63a9e4f8-125d-4822-8532-2a3136b4fb83"] 
  ```

* Translated UI Automator Test Code

  ```kotlin
  val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
  
  // Using By.text to find an element by text
  val element = uiDevice.findObject(By.text("Hello Android!"))
  
  // Perform actions on the found element
  element.click()
  ```

* Results

  The test can pass and it is code is equivalent.