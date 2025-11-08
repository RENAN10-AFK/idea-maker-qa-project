package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;
import utils.WebUtils;

public class C_WidgetsTest extends BaseTest {

    @Test
    public void testWidgetsAccordion() {

        WebUtils ui = new WebUtils(driver);
        Actions actions = new Actions(driver);

        WebElement widgetsMenu = ui.waitVisible(By.linkText("Widgets"));
        actions.moveToElement(widgetsMenu).perform();

        WebElement accordionLink = ui.waitVisible(By.linkText("Accordion"));
        String accordionUrl = accordionLink.getAttribute("href");
        
        driver.get(accordionUrl);

        ui.waitVisible(By.id("accordion"));

        ui.waitClick(By.id("headingOne"));
        WebElement collapseOne = ui.waitVisible(By.id("collapseOne"));

        Assert.assertTrue(collapseOne.isDisplayed(), "Accordion (headingOne) não abriu.");
    }
}