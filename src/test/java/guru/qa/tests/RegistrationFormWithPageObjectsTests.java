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
    //final private String firstName = "Olga"; // так тоже можно, но избыточно
    String firstName = "Olga";
    String lastName = "Kos";
    String userEmail = "test@test.ru";

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
            .setUserEmail(userEmail);
        //$("#genderWrapper").$(byText("Female")).click();  todo: 2 вариант локатора
        $(byText("Female")).click(); //Gender
        $("#userNumber").setValue("8125560781"); //Mobile(10 Digits)

        //Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("3"); //it's April
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $$(".react-datepicker__day").find(text("23")).click();

        //Subjects (мульти-список)
        //$("#subjectsInput").scrollTo().setValue("English"); // не сработал если >1
        $("#subjectsInput").setValue("English").pressEnter();
        $("#subjectsInput").setValue("History").pressEnter();

        //Hobbies (check-boxes)
        $("#hobbies-checkbox-1").scrollTo().parent().click();
        $(byText("Reading")).click();
        $("#hobbies-checkbox-3").parent().click(); // todo возможны вараинты, подумать

        //Picture Select picture
        $("#uploadPicture").uploadFromClasspath("pytpng.png");

        $("#currentAddress").setValue("Moskovskoe 1");

        //State and City
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Panipat")).click();

        //button
        //$("#submit").click();
        $("#submit").scrollTo().click(); //скролл на случай если кнопка перекрыта баннером

        //Checking table/checkForm
       $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //Label / Values
        //registrationPage.openPage()
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



