package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Это страница Practice Form (v.4)")

public class RegistrationFormWithPageObjectsTests {

    RegistrationPage registrationPage = new RegistrationPage();
    String firstName = "Olga";
    String lastName = "Kos";
    String userEmail = "test@test.ru";
    String gender = "Female";
    String userNumber = "8125560781";
    String year = "2000";
    String month = "April";
    String day = "23";
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
                .setBirthDate(day, month, year)
                .setSubjects(subjectsEnglish, subjectsHistory)
                .setHobbies(hobbyReading)
                .setUploadPicture(fileName)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit(); // кнопка загрузить

        //Checking table/checkForm
        //Label / Values
        registrationPage
                .checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", userEmail)
                .checkForm("Gender", gender)
                .checkForm("Mobile", userNumber)
                .checkForm("Date of Birth", day + " " + month + "," + year)
                .checkForm("Subjects", "English, History")
                .checkForm("Hobbies", hobbyReading)
                .checkForm("Picture", fileName)
                .checkForm("Address", address)
                .checkForm("State and City", state + " " + city)
                .closeModal(); //закрыть модальнео окно
    }
}