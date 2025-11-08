package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class N_UploadFileTest extends BaseTest {

    @Test
    public void uploadFromMoreMenu() {

        WebUtils ui = new WebUtils(driver);

        ui.hover(By.linkText("More"));
        ui.waitClick(By.linkText("File Upload"));

        WebElement uploadInput = ui.waitVisible(By.id("input-4"));

        String filePath = "C:\\Users\\Renan.ribeiro\\Desktop\\LIVE\\eyJwYXRoIjoic3VwZXJjZWxsXC9maWxlXC9jQlhFMmFDcjVKVUtWYUdZUDhzNi5wbmcifQ_supercell_QGk6sjuPRVqW8gMgRi7ivnLJF8lXMj3-e287DjBu1uo.png";

        uploadInput.sendKeys(filePath);

        Assert.assertTrue(driver.getPageSource().contains(".png") ||
                        driver.getPageSource().contains(".jpg") ||
                        driver.getPageSource().contains(".jpeg"),
                " Upload não confirmado na página!");

        System.out.println(" Upload realizado com sucesso!");
    }
}


