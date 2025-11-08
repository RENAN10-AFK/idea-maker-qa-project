package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class K_SliderTest extends BaseTest {

    @Test
    public void sliderMove() {

        WebUtils ui = new WebUtils(driver);

        ui.hover(By.linkText("Widgets"));
        ui.waitClick(By.linkText("Slider"));

        WebElement slider = ui.waitVisible(By.xpath("//*[@id='slider']/a"));

        Actions action = new Actions(driver);

        action.dragAndDropBy(slider, 150, 0).perform();

        WebElement sliderValue = ui.waitVisible(By.id("range"));

        String value = sliderValue.getAttribute("value");

        Assert.assertFalse(value.equals("0"), "❌ Slider não foi movido!");

        System.out.println("✅ Slider movido com sucesso! Valor atual: " + value);
    }
}