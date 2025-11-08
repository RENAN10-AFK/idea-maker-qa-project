package web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

import java.io.File;

public class U_UploadTest extends BaseTest {

    @Test
    public void uploadFile() {

        WebUtils ui = new WebUtils(driver);

        // Navega até o menu correto
        ui.waitClick(By.linkText("More"));
        ui.waitClick(By.linkText("File Upload"));

        // Caminho do arquivo no projeto
        File arquivo = new File("src/test/resources/sample.txt");

        // Valida se o arquivo existe antes de tentar fazer upload
        Assert.assertTrue(arquivo.exists(), "❌ Arquivo sample.txt não encontrado!");

        // Envia o arquivo para o input type file
        ui.waitVisible(By.id("input-4")).sendKeys(arquivo.getAbsolutePath());

        // Clica no upload
        ui.waitClick(By.id("uploadButton"));

        System.out.println("✅ Upload realizado com sucesso!");
    }
}
