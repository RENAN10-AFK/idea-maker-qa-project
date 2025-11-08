package web;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class H_HeaderMenuTest extends BaseTest {

    @Test
    public void validateHeaderMenus() {

        WebUtils ui = new WebUtils(driver);

        ui.hover(By.linkText("SwitchTo"));
        ui.waitClick(By.linkText("Alerts"));
        ui.waitVisible(By.id("OKTab"));
        driver.navigate().back();

        ui.hover(By.linkText("SwitchTo"));
        ui.waitClick(By.linkText("Windows"));
        ui.waitVisible(By.id("Tabbed"));
        driver.navigate().back();

        ui.hover(By.linkText("Widgets"));
        ui.waitClick(By.linkText("Accordion"));
        ui.waitVisible(By.id("headingOne"));
        driver.navigate().back();

        ui.hover(By.linkText("More"));
        ui.waitClick(By.linkText("File Upload"));
        ui.waitVisible(By.id("input-4"));
        driver.navigate().back();

        System.out.println("✅ Menus do Header validados com sucesso!");
    }
}