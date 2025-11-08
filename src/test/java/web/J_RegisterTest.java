package web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

import java.io.File;

public class J_RegisterTest extends BaseTest {

    @Test
    public void testFullRegisterForm() throws InterruptedException {

        WebUtils ui = new WebUtils(driver);

        ui.type(By.xpath("//input[@placeholder='First Name']"), "Renan");
        ui.type(By.xpath("//input[@placeholder='Last Name']"), "QA Teste");
        ui.type(By.xpath("//textarea[@ng-model='Adress']"), "Rua Selenium Automação");
        ui.type(By.xpath("//input[@type='email']"), "teste.automation@email.com");
        ui.type(By.xpath("//input[@type='tel']"), "9876543210");

        ui.waitClick(By.xpath("//input[@value='Male']"));

        ui.waitClick(By.id("checkbox1"));
        ui.waitClick(By.id("checkbox2"));

        WebElement skillsElement = ui.waitVisible(By.id("Skills"));
        Select skills = new Select(skillsElement);
        skills.selectByVisibleText("Java");
        
        // Ignora o dropdown 'countries' (o que dá erro)

        // Preenche o dropdown de busca (o que é bloqueado pelo anúncio)
        WebElement countryDropdown = ui.waitVisible(By.xpath("//span[@role='combobox']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", countryDropdown);

        WebElement countrySearch = ui.waitVisible(By.xpath("//input[@type='search']"));
        countrySearch.sendKeys("Japan"); 
        countrySearch.sendKeys(Keys.ENTER);

        new Select(ui.waitVisible(By.id("yearbox"))).selectByVisibleText("1994");
        new Select(ui.waitVisible(By.cssSelector("select[ng-model='monthbox']")))
                .selectByVisibleText("February");
        new Select(ui.waitVisible(By.id("daybox"))).selectByVisibleText("20");

        ui.type(By.id("firstpassword"), "Senha@123");
        ui.type(By.id("secondpassword"), "Senha@123");

        File file = new File("src/test/resources/sample.txt");
        Assert.assertTrue(file.exists(), "Arquivo não encontrado: src/test/resources/sample.txt");
        
        driver.findElement(By.id("imagesrc")).sendKeys(file.getAbsolutePath());

        // --- CORREÇÃO FINAL AQUI ---
        // Clica no Submit usando Javascript para ignorar o anúncio
        WebElement submitButton = ui.waitVisible(By.id("submitbtn"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        
        Thread.sleep(2000); 

        // Esta asserção agora deve passar
        Assert.assertTrue(driver.getCurrentUrl().contains("WebTable"),
                "Registro não foi concluído com sucesso! URL não mudou.");

        System.out.println("Formulário preenchido e enviado com sucesso!");
    }
}