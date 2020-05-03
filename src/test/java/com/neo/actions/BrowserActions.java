package com.neo.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.neo.base.CommonPage;
import com.neo.utilities.ExtentReportUtility;

public class BrowserActions {

	static WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions actions;
	boolean shouldExecute = true;
	public static final String PASS = "PASS";
	public static final String FAIL = "FAIL";
	public static final String INFO = "INFO";

	public BrowserActions(WebDriver webDriver) {
		driver = webDriver;
		wait = new WebDriverWait(driver, Integer.parseInt(CommonPage.getProperty("elementTimeOut")));
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);
	}

	/**
	 * @description This method will launch the application and maximize the window
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param URL
	 * @throws
	 */
	public void LaunchApplication(String URL) {
		try {
			driver.get(URL);
			driver.manage().window().maximize();
			ExtentReportUtility.get().setReport(PASS, "URL Launched : "+URL);
		} catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Fail to Launch URL : "+URL);
			throw e;
		}
	}

	/**
	 * @description This method will quite the driver session
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param
	 * @throws
	 */
	public static void quitApplication() {
		try {
		driver.quit();
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to quit session");
			throw e;
		}
	}

	/**
	 * @description Close the current window
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param
	 * @throws
	 */
	public void closeWindow() {
		try {
		driver.close();
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to close the browser");
			throw e;

		}
	}

	/**
	 * @description Click the element using the WebDriver click method
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element,elementName 
	 * @throws
	 */
	public void click(WebElement element, String elementName) {
		try {
			if (shouldExecute) {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.click();
				ExtentReportUtility.get().setReport(PASS, "Clicked the element "+elementName);
			}
		} catch (Exception e) {
			shouldExecute = false;
			ExtentReportUtility.get().setReport(FAIL, "Failed to click element "+elementName);
			throw e;
		}

	}

	/**
	 * @description Click the dynamic element (object of By class) using WebDriver click method
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element,elementName 
	 * @throws
	 */
	public void clickDynamic(By element, String elementName) {
		try {
			if (shouldExecute) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();
				ExtentReportUtility.get().setReport(PASS, "Clicked the element "+elementName);
			}
		} catch (Exception e) {
			shouldExecute = false;
			ExtentReportUtility.get().setReport(FAIL, "Failed to click element "+elementName);
			throw e;
		}

	}

	/**
	 * @description Click the element using the click method provided by Actions class
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element,elementName 
	 * @throws
	 */
	public void clickActions(WebElement element, String elementName) {
		try {
		actions.click(element).build().perform();
		ExtentReportUtility.get().setReport(PASS, "Clicked the element "+elementName);
	} catch (Exception e) {
		shouldExecute = false;
		ExtentReportUtility.get().setReport(FAIL, "Failed to click element "+elementName);
		throw e;
	}
	}

	/**
	 * @description Click the element using the JavaScript Executor
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element,elementName 
	 * @throws
	 */
	public void clickElementJS(WebElement element, String elementName) {
		try {
		js.executeScript("arguments[0].click();", element);
		ExtentReportUtility.get().setReport(PASS, "Clicked the element "+elementName);
	} catch (Exception e) {
		shouldExecute = false;
		ExtentReportUtility.get().setReport(FAIL, "Failed to click element "+elementName);
	}
		
	}

	/**
	 * @description Click, clear and sendkeys to the element
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element,elementName, textToSend
	 * @throws
	 */
	public void sendKeys(WebElement element, String elementName, String textToSend) {
		try {
			if (shouldExecute) {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.click();
				element.clear();
				element.sendKeys(textToSend);
				ExtentReportUtility.get().setReport(PASS, "sent text "+textToSend+" on element "+elementName);
			}
		} catch (Exception e) {
			shouldExecute = false;
			ExtentReportUtility.get().setReport(FAIL, "Failed to send text "+textToSend+" on element "+elementName);
		}

	}

	/**
	 * @description Verifies if the element is visible and displayed on a webpage
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element,elementName 
	 * @throws
	 */
	public void verifyElementPresent(WebElement element, String elementName) {
		try {
			if (shouldExecute) {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.isDisplayed();
				ExtentReportUtility.get().setReport(PASS, "Element "+elementName+" present and verified");
			}
		} catch (Exception e) {
			shouldExecute = false;
			ExtentReportUtility.get().setReport(FAIL, "Element "+elementName+" not present");
			throw e;
		}

	}

	/**
	 * @description Verifies if the element is visible on the current webpage. 
	 * 				This method does not report fail to reports.
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return boolean
	 * @param element
	 * @throws
	 */
	public boolean verifyVisibilityOfElement(WebElement element) {

		try {
			if (shouldExecute) {
				wait.until(ExpectedConditions.visibilityOf(element));
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * @description Verify if the element is displayed on the page DOM.
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element
	 * @throws
	 */
	public boolean isElementPresent(WebElement element) {
		boolean isPresent = false;
		try {
			if (shouldExecute) {
				wait.until(ExpectedConditions.visibilityOf(element));
				element.isDisplayed();
				isPresent = true;
			}
		} catch (Exception e) {
			isPresent = false;
		}
		return isPresent;
	}

	/**
	 * @description Mouse over the element using the Actions class method
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element,elementName 
	 * @throws
	 */
	public void mouseOverElement(WebElement element, String elementName) {
		try {
		actions.moveToElement(element).build().perform();
		ExtentReportUtility.get().setReport(PASS, "Mouse over element "+elementName);
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Mouse over element "+elementName);
			throw e;
			}
	}

	/**
	 * @description Returns the CSS value of a property provided.
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return String
	 * @param element,cssKey 
	 * @throws
	 */
	public String getCSSValue(By element, String cssKey) {
		
		String cssVal = null;
		try {
		cssVal = driver.findElement(element).getCssValue(cssKey);
		ExtentReportUtility.get().setReport(INFO, "Captured CSS Value : "+cssVal);
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to capture CSS Value for element "+element);
			throw e;
		}
		return cssVal;
	}

	/**
	 * @description Returns the list of WebElements with same locators(By)
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return List<WebElement>
	 * @param element 
	 * @throws
	 */
	public List<WebElement> getElementList(By element) {
		try {
		return driver.findElements(element);
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to get the WebElement List");
		}
		return null;
	}

	/**
	 * @description get the text from the Dynamic element
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return String
	 * @param element 
	 * @throws
	 */
	public String getDynamicElementText(By element) {
		try {
		return driver.findElement(element).getText();
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to get the text from Dynamic element");
		}
		return null;
	}

	/**
	 * @description Scroll down the WebPage using JavaScript Executor
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param 
	 * @throws
	 */
	public void scrollDown() {
		try {
		js.executeScript("window.scrollBy(0,450)", "");
		ExtentReportUtility.get().setReport(PASS, "Scrolled Web Page");
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to scroll the webpage");
		}
	}

	/**
	 * @description Scroll the WebPage to element using JavaScriptExecutor
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param elementName 
	 * @throws
	 */
	public void scrollToElement(WebElement elementName) {
		try {
		js.executeScript("arguments[0].scrollIntoView();", elementName);
		ExtentReportUtility.get().setReport(PASS, "Scrolled to element "+elementName);
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to scroll to element "+elementName);
		}
	}

	/**
	 * @description Switch to the next Window
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param 
	 * @throws
	 */
	public void switchToNextWindow() {
		Set<String> window = driver.getWindowHandles();
		Iterator<String> windowIterator = window.iterator();
		while (windowIterator.hasNext()) {
			driver.switchTo().window(windowIterator.next());
		}
	}

	/**
	 * @description Returns the current URL of the current Window
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return String
	 * @param 
	 * @throws
	 */
	public String getURL() {
		
		String currentURL = null;
		
		try {
		currentURL =  driver.getCurrentUrl();
		ExtentReportUtility.get().setReport(PASS, "The current URL is  :"+currentURL);
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to get the current URL");
			throw e;
		}
		
		return currentURL;
	}

	/**
	 * @description Switch to the frame
	 * @Date 03/05/2020
	 * @return 
	 * @param element 
	 * @throws
	 */
	public void switchToFrame(WebElement element) {
		try {
		driver.switchTo().frame(element);
		ExtentReportUtility.get().setReport(INFO, "Switching to the frame");
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to switch to frame");
			throw e;
		}

	}

	/**
	 * @description Switch back to the parent frame
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param 
	 * @throws
	 */
	public void switchToParentFrame() {
		try {
		driver.switchTo().parentFrame();
		ExtentReportUtility.get().setReport(INFO, "Switched to Parent Frame");
		}catch (Exception e) {
			ExtentReportUtility.get().setReport(FAIL, "Failed to switch to parent  frame");
			throw e;
		}

	}
}
