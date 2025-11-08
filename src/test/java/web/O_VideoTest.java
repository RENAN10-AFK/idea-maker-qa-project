package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class O_VideoTest extends BaseTest {

    @Test
    public void youtubeAndVimeo() {

        WebUtils ui = new WebUtils(driver);

        ui.hover(By.linkText("More"));
        ui.waitClick(By.linkText("Video"));

        // YouTube
        WebElement youtube = ui.waitVisible(By.linkText("YouTube"));
        youtube.click();
        Assert.assertTrue(driver.getPageSource().contains("YouTube"), "❌ YouTube não abriu!");

        driver.navigate().back();

        // Vimeo
        ui.hover(By.linkText("More"));
        ui.waitClick(By.linkText("Video"));
        WebElement vimeo = ui.waitVisible(By.linkText("Vimeo"));
        vimeo.click();
        Assert.assertTrue(driver.getPageSource().contains("Vimeo"), "❌ Vimeo não abriu!");

        System.out.println("✅ Teste de YouTube e Vimeo finalizado com sucesso!");
    }
}




