package demoQAtest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SuccessfulFormTest {

    @BeforeAll
    public static void setUpBrowser() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void test() {
        open("/automation-practice-form");

        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Test");
        $("#userEmail").setValue("alex.test@test.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("899000000000");


        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2015");
        $(".react-datepicker__month-select").selectOption("March");
        $$("div.react-datepicker__day").findBy(text("4")).click();


        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("test.jpg");
        $("#currentAddress").setValue("Some street 1");

        $("#state").click();
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Panipat").pressEnter();

        $("#submit").click();

        $$("table tr").findBy(text("Label")).shouldHave(text("Values"));
        $$("table tr").findBy(text("Student Name")).shouldHave(text("Alex Test"));
        $$("table tr").findBy(text("Student Email")).shouldHave(text("alex.test@test.com"));
        $$("table tr").findBy(text("Gender")).shouldHave(text("Male"));
        $$("table tr").findBy(text("Mobile")).shouldHave(text("8990000000"));
        $$("table tr").findBy(text("Date of Birth")).shouldHave(text("04 March,2015"));
        $$("table tr").findBy(text("Subjects")).shouldHave(text("Arts"));
        $$("table tr").findBy(text("Hobbies")).shouldHave(text("Sports"));
        $$("table tr").findBy(text("Picture")).shouldHave(text("test.jpg"));
        $$("table tr").findBy(text("Address")).shouldHave(text("Some street 1"));
        $$("table tr").findBy(text("State and City")).shouldHave(text("Haryana Panipat"));

    }
}
