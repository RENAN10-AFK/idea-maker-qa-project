package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;
import utils.WebUtils;

import java.time.Duration;
import java.util.List;

public class H_ModalsTest extends BaseTest {

    private final By MODAL_VISIBLE = By.cssSelector(".modal.in, .modal.show");
    private final By CLOSE_BUTTON_IN_MODAL = By.cssSelector(
            ".modal.in .close, .modal.show .close, " +
            ".modal.in .btn,   .modal.show .btn");

    @Test
    public void testSingleAndMultipleModals() {

        WebUtils ui = new WebUtils(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        ui.hover(By.linkText("More"));
        ui.waitClick(By.linkText("Modals"));
        ui.waitVisible(By.xpath("//*[contains(.,'Modal') and (self::h1 or self::h2 or self::h3)]"));

        ui.waitClick(By.xpath("//a[contains(.,'Single Modal')] | //button[contains(.,'Single Modal')]"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_VISIBLE));
        Assert.assertTrue(driver.findElements(MODAL_VISIBLE).size() > 0, "❌ Single Modal não abriu.");

        closeAllOpenModals(wait);

        ui.waitClick(By.xpath("//a[contains(.,'Multiple')] | //button[contains(.,'Multiple')]"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_VISIBLE));
        Assert.assertTrue(driver.findElements(MODAL_VISIBLE).size() > 0, "❌ Multiple Modal (primeiro) não abriu.");

        maybeOpenInnerModal(wait);

        closeAllOpenModals(wait);

        System.out.println("✅ Modals (Single e Multiple) validados com sucesso!");
    }

    private void closeAllOpenModals(WebDriverWait wait) {
        for (int i = 0; i < 3; i++) {
            List<WebElement> modals = driver.findElements(MODAL_VISIBLE);
            if (modals.isEmpty()) break;

            List<WebElement> closers = driver.findElements(CLOSE_BUTTON_IN_MODAL);
            if (!closers.isEmpty()) {
                closers.get(0).click();
            }

            wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_VISIBLE));
        }
        Assert.assertEquals(driver.findElements(MODAL_VISIBLE).size(), 0, "❌ Ainda há modal aberto.");
    }

    private void maybeOpenInnerModal(WebDriverWait wait) {
        By innerOpen = By.xpath(
                "(//div[contains(@class,'modal') and (contains(@class,'in') or contains(@class,'show'))]" +
                "//button[contains(.,'Launch') or contains(.,'Open') or contains(.,'Next')])[1]");

        List<WebElement> btns = driver.findElements(innerOpen);
        if (!btns.isEmpty()) {
            btns.get(0).click();
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(MODAL_VISIBLE, 1));
        }
    }
}

