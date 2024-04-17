package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoQATest {
    public static void main(String[] args) {

        // WebDriver yolu belirleme
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        // WebDriver'ı başlatma
        WebDriver driver = new ChromeDriver();

        // Web sayfasını açma
        driver.get("https://demoqa.com/elements");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='element-list collapse show'] li[id='item-4'] span[class='text']")));


        // Buttons seçeneğine tıklama
        WebElement buttonsOption = driver.findElement(By.cssSelector("div[class='element-list collapse show'] li[id='item-4'] span[class='text']"));
        buttonsOption.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='element-list collapse show'] li[id='item-4'] span[class='text']")));

        // Click Me düğmesine tıklama
        WebElement clickMeButton = driver.findElement(By.cssSelector("body > div:nth-child(6) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(4) > button:nth-child(1)"));
        clickMeButton.click();

        // Mesajı alıp yazdırma
        WebElement messageElement = driver.findElement(By.cssSelector("#dynamicClickMessage"));
        System.out.println("Görünen Mesaj: " + messageElement.getText());

        // Tarayıcıyı kapatma
        driver.quit();
    }
}
