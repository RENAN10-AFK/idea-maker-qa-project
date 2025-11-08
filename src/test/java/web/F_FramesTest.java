package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class F_FramesTest extends BaseTest {

    @Test
    public void singleFrame() {

        WebUtils ui = new WebUtils(driver);

        ui.waitClick(By.linkText("SwitchTo"));
        ui.waitClick(By.linkText("Frames"));

        ui.waitClick(By.partialLinkText("Single Iframe"));

        WebElement frame = ui.waitVisible(By.id("singleframe"));

        driver.switchTo().frame(frame);

        driver.findElement(By.tagName("input")).sendKeys("Teste dentro do iframe!");

        driver.switchTo().defaultContent();

        System.out.println("✅ Single Iframe testado com sucesso!");
    }


    @Test
    public void multipleFrames() {

        WebUtils ui = new WebUtils(driver);

        driver.switchTo().defaultContent(); 
        
        ui.waitClick(By.linkText("SwitchTo"));
        ui.waitClick(By.linkText("Frames"));

        ui.waitClick(By.partialLinkText("Iframe with in an Iframe"));

        WebElement outerFrame = ui.waitVisible(By.cssSelector("iframe[src='MultipleFrames.html']"));
        driver.switchTo().frame(outerFrame);

        WebElement innerFrame = ui.waitVisible(By.cssSelector("iframe[src='SingleFrame.html']"));
        driver.switchTo().frame(innerFrame);

        driver.findElement(By.tagName("input")).sendKeys("Texto no iframe interno!");

        driver.switchTo().defaultContent();

        System.out.println("✅ Multiple Iframes testado com sucesso!");
    }
}