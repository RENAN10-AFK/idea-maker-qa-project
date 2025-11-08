package web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

import java.time.Duration;

public class C_CodeMirrorTest extends BaseTest {

    @Test
    public void validateCodeMirrorEditor() {

        WebUtils ui = new WebUtils(driver);

        ui.hover(By.linkText("WYSIWYG"));
        ui.waitClick(By.linkText("CodeMirror"));

        WebElement codeArea = ui.waitVisible(By.cssSelector(".CodeMirror"));
        codeArea.click();
        
        By textAreaLocator = By.cssSelector(".CodeMirror textarea");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        
        WebElement hiddenTextArea = wait.until(ExpectedConditions.presenceOfElementLocated(textAreaLocator));

        hiddenTextArea.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE); 
        hiddenTextArea.sendKeys("Automation test using CodeMirror editor!");

        String text = hiddenTextArea.getAttribute("value");
        Assert.assertTrue(
                text.contains("Automation test"),
                " Não foi possível digitar no editor CodeMirror!"
        );

        System.out.println(" CodeMirror validado com sucesso!");
    }
}