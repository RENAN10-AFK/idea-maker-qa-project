package web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

import java.time.Duration;

public class B_AutoCompleteTest extends BaseTest {

    @Test
    public void validateAutoCompleteCountries() {

        WebUtils ui = new WebUtils(driver);

        ui.hover(By.linkText("Widgets"));
        ui.waitClick(By.linkText("AutoComplete"));

        WebElement input = driver.findElement(By.id("searchbox"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = 'Portugal';", input);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeToBe(input, "value", "Portugal"));
        
        String value = input.getAttribute("value");

        Assert.assertEquals(value, "Portugal", "❌ Autocomplete não selecionou Portugal!");

        System.out.println("✅ AutoComplete validado com sucesso!");
    }
}