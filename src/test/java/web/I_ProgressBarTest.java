package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

import java.time.Duration;

public class I_ProgressBarTest extends BaseTest {

    private static final By BTN_DOWNLOAD   = By.id("downloadButton");
    private static final By PROGRESS_LABEL = By.cssSelector(".progress-label");
    private static final By PROGRESS_BAR   = By.id("progressbar");
    private static final By CLOSE_IN_DIALOG= By.xpath("//div[contains(@class,'ui-dialog')]//button[.//span[text()='Close']]");

    @Test
    public void progressBarDownload() {
        WebUtils ui = new WebUtils(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

        ui.hover(By.linkText("More"));
        ui.waitClick(By.linkText("JQuery ProgressBar"));

        WebElement btn = ui.waitVisible(BTN_DOWNLOAD);
        Assert.assertTrue(btn.isDisplayed(), " Botão de Download não visível.");
        btn.click();

        boolean finished = waitUntilComplete(wait);
        Assert.assertTrue(finished, " O progresso não chegou a 'Complete!' dentro do tempo.");

        try {
            WebElement close = wait.until(ExpectedConditions.elementToBeClickable(CLOSE_IN_DIALOG));
            close.click();
        } catch (TimeoutException ignore) {
        }

        System.out.println("✅ JQuery ProgressBar validado com sucesso (Complete!).");
    }

    private boolean waitUntilComplete(WebDriverWait wait) {
        long end = System.currentTimeMillis() + 35000;
        while (System.currentTimeMillis() < end) {
            try {
                WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(PROGRESS_LABEL));
                if (label.getText() != null && label.getText().trim().equalsIgnoreCase("Complete!")) {
                    return true;
                }
            } catch (TimeoutException ignored) {}

            try {
                WebElement bar = wait.until(ExpectedConditions.visibilityOfElementLocated(PROGRESS_BAR));
                String cls = bar.getAttribute("class");
                if (cls != null && cls.contains("ui-progressbar-complete")) {
                    return true;
                }
            } catch (TimeoutException ignored) {}

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(@class,'ui-dialog') and contains(.,'Complete!')]")
                ));
                return true;
            } catch (TimeoutException ignored) {}
        }
        return false;
    }
}



