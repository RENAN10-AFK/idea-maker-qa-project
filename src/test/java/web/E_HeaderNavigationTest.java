package web;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class E_HeaderNavigationTest extends BaseTest {

    @Test
    public void validateHeaderMenus() {

        WebUtils ui = new WebUtils(driver);

        ui.waitClick(By.id("btn2"));

        ui.waitClick(By.linkText("SwitchTo"));
        ui.waitClick(By.linkText("Alerts"));
        ui.waitVisible(By.id("OKTab"));
        driver.navigate().back();

        ui.waitClick(By.linkText("SwitchTo"));
        ui.waitClick(By.linkText("Windows"));
        ui.waitVisible(By.id("Tabbed"));
        driver.navigate().back();

        ui.waitClick(By.linkText("SwitchTo"));
        ui.waitClick(By.linkText("Frames"));
        ui.waitVisible(By.linkText("Single Iframe"));
        driver.navigate().back();

        System.out.println("✅ Validação completa do menu SWITCH TO executada com sucesso!");
    }
}
