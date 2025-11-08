package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class L_SummerNoteTest extends BaseTest {

    @Test
    public void summerNoteEditor() {

        WebUtils ui = new WebUtils(driver);

        ui.hover(By.linkText("More"));
        ui.waitClick(By.linkText("Summer Note"));

        WebElement frame = ui.waitVisible(By.cssSelector("iframe.note-editing-area"));
        driver.switchTo().frame(frame);

        WebElement editableArea = ui.waitVisible(By.cssSelector("body.note-editable"));

        editableArea.clear();
        editableArea.sendKeys("Teste automatizado com SummerNote!");

        Assert.assertTrue(editableArea.getText().contains("Teste automatizado"),
                " Texto NÃO foi digitado no SummerNote!");

        System.out.println(" Summer Note preenchido com sucesso!");

        driver.switchTo().defaultContent();
    }
}

