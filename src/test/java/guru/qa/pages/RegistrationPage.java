package guru.qa.pages;

import guru.qa.pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    // components
    CalendarComponent calendarComponent = new CalendarComponent();

    // locators
    private SelenideElement
            headerTitle = $(".practice-form-wrapper"), // заголовок страницы регистрации
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $(".practice-form-wrapper #genderWrapper"),
            userNumberInput = $("#userNumber"),
            //subjectsInput = $("#subjects1", "#subjects2"),
            //hobbiesInput = $("#hobbies"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),

            submitButton = $("#submit"),
            resultsTable = $(".table-responsive");

    // actions
    public RegistrationPage openPage(){
        open("/automation-practice-form/"); // страница после baseUrl
        headerTitle.shouldHave(text("Student Registration Form"));

        return this;
    }
    //”Olga”
    public RegistrationPage setFirstName(String firstName) {firstNameInput.setValue(firstName);

        return this;
    }
    //”Kos”
    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }
    //”test@test.ru”
    public RegistrationPage setUserEmail (String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }


    //Gender
    public RegistrationPage set Gender (String gender) {
        //genderInput.setValue(gender);
        $(byText(gender)).click();
    return this;
            }



    //userNumber
    public RegistrationPage setUserNumber (String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }


    //Date of Birth
    public void setBirthDate (String day, String month, String year){
        $("#dateOfBirthInput").click(); //клик по кнопке "заполнить ДР"
        calendarComponent.setDate(day, month, year); //универсальный компонент "календарь"
    }
    //...
    public RegistrationPage setUploadPicture(File addImg) {
        uploadPictureInput.uploadFile(addImg);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        //stateInput.click().setValue(state);
        //$(byText("Haryana")).click();
        //$("#state").click();
        $(stateInput).click();
        $(byText(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        //cityInput.click();
        $(cityInput).click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    //Checking table/checkForm
    //Label and Values:
    public RegistrationPage checkForm(String fieldName, String value) {
             //ячейка "Как называется поле
             resultsTable.$(byText(fieldName))
            // ячейка "пользователськое Значение"
            .parent().shouldHave(text(value));
        return this;
    }
}