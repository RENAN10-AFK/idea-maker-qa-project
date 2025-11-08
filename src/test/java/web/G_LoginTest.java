package web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

public class G_LoginTest extends BaseTest {

    @Test
    public void skipLogin() {

        WebUtils ui = new WebUtils(driver);

        ui.waitClick(By.id("btn2"));

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("Register.html"),
                " ERRO: Não navegou para a página principal após clicar em Skip Sign In!"
        );

        System.out.println(" Login / Skip Sign In validado com sucesso!");
    }
}



