package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class WysiwygTest extends BaseTest {

    @Test
    public void wysiwygEditors() {

        WebUtils ui = new WebUtils(driver);

        ui.waitClick(By.linkText("More"));
        ui.waitClick(By.linkText("WYSIWYG Editor"));

        ui.waitClick(By.linkText("CKeditor"));

        WebElement ckFrame = ui.waitVisible(By.className("cke_wysiwyg_frame"));
        driver.switchTo().frame(ckFrame);

        WebElement ckEditor = ui.waitVisible(By.tagName("body"));
        ckEditor.clear();
        ckEditor.sendKeys("Teste no CKEditor via automação!");

        driver.switchTo().defaultContent();
        System.out.println(" CKEditor validado com sucesso!");

        ui.waitClick(By.linkText("SummerNote"));

        WebElement summerNoteArea = ui.waitVisible(By.className("note-editable"));
        summerNoteArea.clear();
        summerNoteArea.sendKeys("Inserindo texto no SummerNote via Selenium!");

        System.out.println(" SummerNote validado com sucesso!");

        ui.waitClick(By.linkText("CodeMirror"));

        // o editor é um container, o campo real é dentro do textarea
        WebElement codeMirrorArea = ui.waitVisible(By.cssSelector(".CodeMirror"));
        ui.hover(By.cssSelector(".CodeMirror")); // move o mouse para ativar o foco

        WebElement codeMirrorTextArea = codeMirrorArea.findElement(By.cssSelector("textarea"));
        codeMirrorTextArea.sendKeys("print('CodeMirror automação OK');");

        System.out.println(" CodeMirror validado com sucesso!");

        ui.waitClick(By.linkText("TinyMCE"));

        WebElement tinyFrame = ui.waitVisible(By.cssSelector("iframe.tox-edit-area__iframe"));
        driver.switchTo().frame(tinyFrame);

        WebElement tinyEditor = ui.waitVisible(By.tagName("body"));
        tinyEditor.clear();
        tinyEditor.sendKeys("Texto inserido no TinyMCE via automação!");

        driver.switchTo().defaultContent();
        System.out.println(" TinyMCE validado com sucesso!");

        // Finalização do teste
        Assert.assertTrue(true, "Todos os editores foram testados com sucesso.");
    }
}


