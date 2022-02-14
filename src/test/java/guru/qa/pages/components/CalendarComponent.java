package guru.qa.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {

    public void setDate (String day, String month, String year){
        //$(".react-datepicker__month-select").selectOptionByValue(month); //3 it's April
        $(".react-datepicker__month-select").selectOption(month); //April
        $(".react-datepicker__year-select").selectOption(year);
        $$(".react-datepicker__day").find(text(day)).click();
    }
}
