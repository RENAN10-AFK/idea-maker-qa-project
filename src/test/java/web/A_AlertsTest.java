package web;

import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class A_AlertsTest extends BaseTest {

    @Test
    public void validateAlerts() {

        WebUtils ui = new WebUtils(driver);

        driver.get("https://demo.automationtesting.in/Alerts.html");

        ui.waitClick(By.xpath("//button[@onclick='alertbox()']"));

        Alert alert1 = driver.switchTo().alert();
        Assert.assertEquals(alert1.getText(), "I am an alert box!", " Mensagem incorreta no alerta!");
        alert1.accept();

        ui.waitClick(By.linkText("Alert with OK & Cancel"));
        ui.waitClick(By.xpath("//button[@onclick='confirmbox()']"));

        Alert alert2 = driver.switchTo().alert();
        alert2.dismiss(); 

        Assert.assertTrue(
                driver.findElement(By.id("demo")).getText().contains("Cancel"),
                " Texto não mudou para Cancel!"
        );

        ui.waitClick(By.linkText("Alert with Textbox"));
        ui.waitClick(By.xpath("//button[@onclick='promptbox()']"));

        Alert alert3 = driver.switchTo().alert();
        alert3.sendKeys("QA Automation");
        alert3.accept();

        Assert.assertTrue(
                driver.findElement(By.id("demo1")).getText().contains("QA Automation"),
                " Prompt não recebeu o texto!"
        );
    }
}