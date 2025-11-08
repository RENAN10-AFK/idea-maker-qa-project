package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public WebUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);
    }

    public WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitClick(By locator) {
        waitVisible(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = waitVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /** Mouse hover */
    public void hover(By locator) {
        actions.moveToElement(waitVisible(locator)).perform();
    }

    /** Scroll até o elemento */
    public void scroll(By locator) {
        WebElement element = waitVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}

