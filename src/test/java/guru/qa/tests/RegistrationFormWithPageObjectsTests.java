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

    @BeforeAll
    static void openPage() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void studentRegistrationFormTests() {
        registrationPage.openPage() // перешли на страницу /automation-practice-form/
            .setFirstName(firstName)
            .setLastName(lastName);
        //$("#userEmail").setValue("test@test.ru");
        new RegistrationPage().setUserEmail("test@test.ru");
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
        //было ранее. Вариант 1 :
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
        registrationPage.checkForm("Student Name", firstName + " Kos");
        registrationPage.checkForm("Student Email", "test@test.ru");
        registrationPage.checkForm("Gender", "Female");
        registrationPage.checkForm("Mobile", "8125560781");
        registrationPage.checkForm("Date of Birth", "23 April,2000");
        registrationPage.checkForm("Subjects", "English, History");
        registrationPage.checkForm("Hobbies", "Sports, Reading, Music");
        registrationPage.checkForm("Picture", "pytpng.png");
        registrationPage.checkForm("Address", "Moskovskoe 1");
        registrationPage.checkForm("State and City", "Haryana Panipat");

        $("#closeLargeModal").click(); //button
    }
}



