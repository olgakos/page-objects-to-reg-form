package guru.qa.pages;

//import guru.qa.pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    // components

    // locators
    private SelenideElement
            headerTitle = $(".practice-form-wrapper"), // заголовок страницы регистрации
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            resultsTable = $(".table-responsive");

    // actions
    public RegistrationPage openPage(){
        open("/automation-practice-form/"); // страница после baseUrl
        //вариант 2 проверка заголовка страницы (стало)

        headerTitle.shouldHave(text("Student Registration Form"));

        return this;
    }
    //”Olga”
    public RegistrationPage setFirstName(String firstName) {firstNameInput.setValue(firstName);

        return this;
    }
    //”Kos”
    public RegistrationPage setLastName(String lastName) {lastNameInput.setValue(lastName);

        return this;
    }
    //”test@test.ru”
    public RegistrationPage setUserEmail (String userEmail) {userEmailInput.setValue(userEmail);

        return this;
    }

    //Date of Birth
    //почему надо void а не RegistrationPage ???
    public void setBirthDate (String day, String month, String year){
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue(month); //3 it's April
        $(".react-datepicker__year-select").selectOption(year);
        $$(".react-datepicker__day").find(text(day)).click();
    }

    //...

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
