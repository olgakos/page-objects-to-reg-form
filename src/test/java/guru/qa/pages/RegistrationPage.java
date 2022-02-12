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
    public void openPage(){
        open("/automation-practice-form/"); // старница после baseUrl
        $(byText("Student Registration Form")); //проверка заголовка страницы
    }

    //private SelenideElement firstNameInput = $("#firstName"); // так тоже можно, но избыточно
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement userEmailInput = $("#userEmail");
    SelenideElement resultsTable = $(".table-responsive");

    // actions
    //”Olga”
    public void setFirstName(String firstName) {firstNameInput.setValue(firstName);
    }
    //”Kos”
    public void setLastName(String lastName) {lastNameInput.setValue(lastName);
    }
    //”test@test.ru”
    public void setUserEmail (String userEmail) {userEmailInput.setValue(userEmail);
    }

    //...

    //Checking table/checkForm
    //Label and Values:
    public void checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName)) //ячейка "Как называется поле"
            .parent().shouldHave(text(value)); //ячейка "пользователськое Значение"
    }
}
