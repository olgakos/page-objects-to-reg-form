package guru.qa.tests;

import com.codeborne.selenide.Configuration;

import guru.qa.pages.RegistrationPage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    //String subjects = "English", "History";
    //String hobbies = "Reading";
    String address = "Moskovskoe 1";
    String addImg = "pytpng.png";
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
            //.setGender(gender)
            .setUserNumber(userNumber)
            //.setSubjects(subjects1, subjects2)
            //.setHobbies(hobbies)
            .setUploadPicture(addImg)
            .setAddress(address)
            .setState(state)
            .setCity(city);

        //$("#genderWrapper").$(byText("Female")).click();  todo: 2 вариант локатора
        $(byText("Female")).click(); //Gender

        registrationPage.setBirthDate("23", "3","2000"); //3 it's Apri

        //Subjects (мульти-список)
        $("#subjectsInput").setValue("English").pressEnter();
        $("#subjectsInput").setValue("History").pressEnter();
        //Hobbies (check-boxes)
        $("#hobbies-checkbox-1").scrollTo().parent().click();
        $(byText("Reading")).click();
        $("#hobbies-checkbox-3").parent().click(); // todo возможны вараинты, подумать

        /*
        //Picture Select picture
        $("#uploadPicture").uploadFromClasspath("pytpng.png");
        //Picture Address"
        $("#currentAddress").setValue("Moskovskoe 1");
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
            .checkForm("Gender", "Female")
            .checkForm("Mobile", "8125560781")
            .checkForm("Date of Birth", "23 April,2000")
            .checkForm("Subjects", "English, History")
            .checkForm("Hobbies", "Sports, Reading, Music")
            .checkForm("Picture", "pytpng.png")
            .checkForm("Address", "Moskovskoe 1")
            .checkForm("State and City", "Haryana Panipat");

        $("#closeLargeModal").click(); //button
    }
}



