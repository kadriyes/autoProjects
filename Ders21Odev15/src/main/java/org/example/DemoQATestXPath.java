package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DemoQATestXPath {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20); // Bekleme süresini 20 saniye olarak ayarladım

        try {
            //Test1: buttona tıklama ve mesajı görüntüleme
            driver.get("https://demoqa.com/elements");

            WebElement buttonsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='item-4']")));
            buttonsTab.click();
            WebElement clickMeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Click Me']"))); // Buton metniyle doğru seçim
            clickMeButton.click();
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dynamicClickMessage']"))); // id değeri düzeltildi
            System.out.println("Görünen Mesaj: " + messageElement.getText());

            // Test 2: Add düğmesi ve form doldurma
            driver.get("https://demoqa.com/webtables");
            WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='addNewRecordButton']")));
            addButton.click();

            // form doldurma
            driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Kadriye");
            driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Yener");
            driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("kadriye@example.com");
            driver.findElement(By.xpath("//input[@id='age']")).sendKeys("30");
            driver.findElement(By.xpath("//input[@id='salary']")).sendKeys("50000");
            driver.findElement(By.xpath("//input[@id='department']")).sendKeys("Quality Assurance");
            driver.findElement(By.xpath("//button[@id='submit']")).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            // denemeler
            //WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Kadriye')]/following-sibling::div//button[contains(@id, 'edit-record')]")));
            // editButton.click();
           // WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='edit-record-4']")));
            //editButton.click();

            WebElement editButton = driver.findElement(By.id("edit-record-4"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", editButton);


            WebElement editLastName = driver.findElement(By.xpath("//input[@id='lastName']"));
            editLastName.clear();
            editLastName.sendKeys("Sir");
            driver.findElement(By.xpath("//button[@id='submit']")).click();
        } finally {

          //  driver.quit(); // Tarayıcıyı kapat
        }
    }
}

