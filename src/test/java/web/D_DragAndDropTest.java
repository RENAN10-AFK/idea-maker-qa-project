package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class D_DragAndDropTest extends BaseTest {

    @Test
    public void dragAndDropTest() {

        WebUtils ui = new WebUtils(driver);

        ui.waitClick(By.linkText("Interactions"));
        ui.waitClick(By.linkText("Drag and Drop"));
        ui.waitClick(By.linkText("Static"));

        WebElement origem = driver.findElement(By.id("angular"));
        WebElement destino = driver.findElement(By.id("droparea"));

        Actions actions = new Actions(driver);
        
        actions.clickAndHold(origem)
               .moveToElement(destino)
               .release(destino)
               .perform();

        String dropText = destino.getText();
        Assert.assertTrue(dropText.contains("angular"),
                " O texto não foi encontrado dentro da área de drop!");

        System.out.println(" Drag and Drop executado com sucesso!");
    }
}