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
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        try {
            driver.get("https://demoqa.com/webtables");

            // sayfa yükleme
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            // engeleyicileri kapa
            List<WebElement> closeButtons = driver.findElements(By.cssSelector(".close-button"));
            if (!closeButtons.isEmpty()) {
                for (WebElement closeButton : closeButtons) {
                    if (closeButton.isDisplayed()) {
                        closeButton.click();
                        break;  // Sadece ilk görünür olanı kapatıp devam et
                    }
                }
            }

            // "add tıklama
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton")));
            try {
                addButton.click();
            } catch (Exception e) {
                // JavaScript ile tıklama işlemi
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
            }

            // form doldurma
            driver.findElement(By.cssSelector("#firstName")).sendKeys("Kadriye");
            driver.findElement(By.cssSelector("#lastName")).sendKeys("Yener");
            driver.findElement(By.cssSelector("#userEmail")).sendKeys("kadriye@deneme.com");
            driver.findElement(By.cssSelector("#age")).sendKeys("35");
            driver.findElement(By.cssSelector("#salary")).sendKeys("100000");
            driver.findElement(By.cssSelector("#department")).sendKeys("TestAutomation");
            driver.findElement(By.cssSelector("#submit")).click();

            // formu dogrulama
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#edit-record-4 > svg")));

            // duzenle butonuna tıkla
            driver.findElement(By.cssSelector("#edit-record-4 > svg")).click();
         //   driver.findElement(By.cssSelector("#edit-record-4")).click();
            // Düzenleme formu aç ve güncelle
            WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='lastName']")));
            lastNameField.clear();
            lastNameField.sendKeys("Sir");
            driver.findElement(By.cssSelector("#submit")).click();

        } finally {
           // driver.quit(); // Tarayıcıyı kapat
        }
    }
}
