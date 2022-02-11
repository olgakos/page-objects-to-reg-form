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

    @BeforeAll
    static void openPage() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form/");
        $(byText("Student Registration Form")); //проверка заголовка страницы
    }

    @Test
    void studentRegistrationFormTests() {
        //$("[id=firstName]").setValue("Olga");
        new RegistrationPage().setFirstName("Olga");
        $("[id=lastName]").setValue("Kos");
        $("#userEmail").setValue("ok@yandex.ru");
        $(byText("Female")).click(); //Gender // todo возможны вараинты, подумать
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

/*
        //2 X-вариант, тоже рабочий
        $x("//*[@id ='state']").click(); // попали в поле
        $x("//*[text() = 'Haryana']").hover().click();
        $x("//*[@id ='city']").click();
        $x("//*[text() ='Panipat']").hover().click();
*/
        //button
        //$("#submit").click();
        $("#submit").scrollTo().click(); //скролл на случай если кнопка перекрыта баннером

        //Checking table/checkForm
       $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        /*
        //было ранее:
        $(".table-responsive").shouldHave(
                text("Olga Kos"),
                text("ok@yandex.ru"),
                text("Female"),
                text("8125560781"),
                text("23 April,2000"),
                text("English, History"),
                text("Sports, Reading, Music"),
                text("pytpng.png"),
                text("Moskovskoe 1"),
                text("Haryana Panipat")
        );
        */

        //Label / Values
        new RegistrationPage().checkForm("Student Name", "Olga Kos");
        new RegistrationPage().checkForm("Student Email", "ok@yandex.ru");
        new RegistrationPage().checkForm("Gender", "Female");
        new RegistrationPage().checkForm("Mobile", "8125560781");
        new RegistrationPage().checkForm("Date of Birth", "23 April,2000");
        new RegistrationPage().checkForm("Subjects", "English, History");
        new RegistrationPage().checkForm("Hobbies", "Sports, Reading, Music");
        new RegistrationPage().checkForm("Picture", "pytpng.png");
        new RegistrationPage().checkForm("Address", "Moskovskoe 1");
        new RegistrationPage().checkForm("State and City", "Haryana Panipat");

        $("#closeLargeModal").click(); //button
    }

}



