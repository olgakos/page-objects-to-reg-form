package guru.qa.pages;

//import guru.qa.pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    // components

    // locators
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement resultsTable = $(".table-responsive");

    // actions
    //”Olga”
    public void setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
    }

    //...

    //Checking table/checkForm
    //Label and Values
    public void checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName)) //ячейка "Как называется поле"
            .parent().shouldHave(text(value)); //ячейка "пользователськое Значение"
    }
}
