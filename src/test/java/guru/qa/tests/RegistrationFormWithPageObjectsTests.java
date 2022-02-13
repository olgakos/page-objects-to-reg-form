package guru.qa.tests;

import com.codeborne.selenide.Configuration;

import guru.qa.pages.RegistrationPage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Это страница Practice Form (v.3)")

public class RegistrationFormWithPageObjectsTests {

    RegistrationPage registrationPage = new RegistrationPage();
    String firstName = "Olga";
    String lastName = "Kos";
    String userEmail = "test@test.ru";
    String gender = "Female";
    String userNumber = "8125560781";
    //date?
    String subjectsEnglish = "English";
    String subjectsHistory = "History";
    String hobbyReading = "Reading";
    String fileName = "pytpng.png";
    String address = "Moskovskoe 1";
    String state = "Haryana";
    String city = "Panipat";

    @BeforeAll
    static void openPage() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void studentRegistrationFormTests() {

        registrationPage.openPage() // перешли на страницу /automation-practice-form/
            .setFirstName(firstName)
            .setLastName(lastName)
            .setUserEmail(userEmail)
            .setGender(gender)
            .setUserNumber(userNumber)
            .setSubjects(subjectsEnglish, subjectsHistory)
            .setHobbies(hobbyReading)
            .setUploadPicture(fileName)
            .setAddress(address)
            .setState(state)
            .setCity(city);

             registrationPage.setBirthDate("23", "3","2000"); //3 it's Apri
            //.submit(); // кнопка загрузить

        /*
        //State and City
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Panipat")).click();
        */

        //button
        //$("#submit").click();
        $("#submit").scrollTo().click(); //скролл на случай если кнопка перекрыта баннером

        //Checking table/checkForm
       $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //Label / Values
        registrationPage
            .checkForm("Student Name", firstName + " " + lastName)
            .checkForm("Student Email", userEmail)
            .checkForm("Gender", gender)
            .checkForm("Mobile", userNumber)
            .checkForm("Date of Birth", "23 April,2000")
            .checkForm("Subjects", "English, History")
            .checkForm("Hobbies", "Sports, Reading, Music")
            .checkForm("Picture", fileName)
            .checkForm("Address", address)
            .checkForm("State and City", "Haryana Panipat");

        $("#closeLargeModal").click(); //button
    }
}



