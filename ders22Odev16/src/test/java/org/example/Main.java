package org.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class Main {
    @BeforeAll
    public static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000; // Bekleme
    }

    @Test
    public void testButtonAndMessage() {
        open("https://demoqa.com/elements");
        // Buttons a tıkla
        $(byText("Buttons")).shouldBe(visible).click();

        // click me tıklaması
        $(byText("Click Me")).shouldBe(visible, enabled).scrollTo().click();

        // Mesajı doğrulama
        String message = $("#dynamicClickMessage").text();
        assert message.equals("You have done a dynamic click");
        System.out.println("Test 1 Passed: Mesaj göründü ve doğru- " + message);
    }

    @Test
    public void testAddAndEditWebTable() {
        open("https://demoqa.com/webtables");

        // ADd tıklama
        $("#addNewRecordButton").shouldBe(visible).click();

        // form doldurma
        $("#firstName").setValue("Kadriye");
        $("#lastName").setValue("Yener");
        $("#userEmail").setValue("kadriye@example.com");
        $("#age").setValue("30");
        $("#salary").setValue("50000");
        $("#department").setValue("QA");

        // Kaydı submit etme
        $("#submit").click();

        // kayıt düzenle
        $("#edit-record-4").lastChild().shouldBe(visible).scrollTo().click();

        // güncelle
        $("#lastName").setValue("Sir");
        $("#submit").click();

        // güncellenen bilgiyi dogrula
        String updatedLastName = $("#lastName").getValue();
        assert updatedLastName.equals("Sir");
        System.out.println("Test 2 Passed: Güncellenen Soyadı Alanı " + updatedLastName);
    }
}
