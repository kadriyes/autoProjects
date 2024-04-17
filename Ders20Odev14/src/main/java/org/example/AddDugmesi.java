package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddDugmesi {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            driver.get("https://demoqa.com/webtables");

            // Sayfanın tamamen yüklenmesini bekle
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            // Engelleyici element varsa kapat
            List<WebElement> closeButtons = driver.findElements(By.cssSelector(".close-button"));
            if (!closeButtons.isEmpty()) {
                for (WebElement closeButton : closeButtons) {
                    if (closeButton.isDisplayed()) {
                        closeButton.click();
                        break;  // Sadece ilk görünür olanı kapatıp devam et
                    }
                }
            }

            // "Add" düğmesine tıklama işlemi için alternatif yaklaşımlar
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton")));
            try {
                addButton.click();
            } catch (Exception e) {
                // JavaScript ile tıklama işlemi
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
            }

            // Yeni kayıt formunu doldurun
            driver.findElement(By.cssSelector("#firstName")).sendKeys("Kadriye");
            driver.findElement(By.cssSelector("#lastName")).sendKeys("Yener");
            driver.findElement(By.cssSelector("#userEmail")).sendKeys("kadriye@deneme.com");
            driver.findElement(By.cssSelector("#age")).sendKeys("35");
            driver.findElement(By.cssSelector("#salary")).sendKeys("100000");
            driver.findElement(By.cssSelector("#department")).sendKeys("TestAutomation");
            driver.findElement(By.cssSelector("#submit")).click();

            // Formun başarılı bir şekilde gönderildiğini doğrulayın
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#edit-record-4 > svg")));

            // Düzenleme butonunu bul
            driver.findElement(By.cssSelector("#edit-record-4 > svg")).click();
         //   driver.findElement(By.cssSelector("#edit-record-4")).click();
            // Düzenleme formunun açılmasını bekle
            WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='lastName']")));
            lastNameField.clear();
            lastNameField.sendKeys("Sir");
            driver.findElement(By.cssSelector("#submit")).click();

        } finally {
            driver.quit(); // Tarayıcıyı kapat
        }
    }
}
