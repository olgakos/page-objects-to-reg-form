package guru.qa.pages;

//import guru.qa.pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    // components

    // locators
    SelenideElement firstNameInput = $("#firstName");

    // actions
    //”Olga”
    public void setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
    }
}
