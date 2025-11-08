package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class M_TinyMceTest extends BaseTest {

    @Test
    public void tinyMce() {

        WebUtils ui = new WebUtils(driver);

        ui.hover(By.linkText("More"));
        ui.waitClick(By.linkText("WYSIWYG Editor"));

        WebElement frame = ui.waitVisible(By.cssSelector("iframe.tox-edit-area__iframe"));
        driver.switchTo().frame(frame);

        WebElement editorBody = ui.waitVisible(By.cssSelector("body"));

        editorBody.clear();
        editorBody.sendKeys("Teste automatizado no TinyMCE!");

        Assert.assertTrue(editorBody.getText().contains("Teste automatizado"),
                " TinyMCE não recebeu o texto!");

        System.out.println(" TinyMCE preenchido com sucesso!");

        driver.switchTo().defaultContent();
    }
}


