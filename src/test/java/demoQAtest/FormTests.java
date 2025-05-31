package demoQAtest;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class FormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();


    String name = "Test";
    String lastName = "Testov";
    String email = "test@ya.ru";
    String userSex = "Male";
    String userNumber = "9000000000";

    @Test
    public void successfulRegistrationFullFormTest() {
        registrationPage
                .openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userSex)
                .setPhoneNumber(userNumber)
                .setDateOfBirth("20", "February", "1990")
                .setSubjects("ph")
                .setSubjects("mat")
                .setSubjects("ec")
                .setHobbies("Reading")
                .setHobbies("Music")
                .uploadPicture("test.jpg")
                .setAddress("Платформа 9 и 3/4")
                .setState("Rajasthan")
                .setCity("Jaipur")
                .clickSubmit()
                .checkResult("Label", "Values")
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", userSex)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", "20 February,1990")
                .checkResult("Subjects", "Physics, Maths, Economics")
                .checkResult("Hobbies", "Reading, Music")
                .checkResult("Picture", "test.jpg")
                .checkResult("Address", "Платформа 9 и 3/4")
                .checkResult("State and City", "Rajasthan Jaipur");
    }

    @Test
    public void successfulRegistrationShortFormTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM,yyyy", new Locale("en"));
        String formattedDate = LocalDate.now().format(formatter);

        registrationPage
                .openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setGender(userSex)
                .setPhoneNumber(userNumber)
                .clickSubmit()
                .checkResult("Label", "Values")
                .checkResult("Student Name", name + " " + lastName)
                .checkResultEmptyField("Student Email")
                .checkResult("Gender", userSex)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", formattedDate)
                .checkResultEmptyField("Subjects")
                .checkResultEmptyField("Hobbies")
                .checkResultEmptyField("Picture")
                .checkResultEmptyField("Address")
                .checkResultEmptyField("State and City");
    }

    @Test
    public void registrationFormInvalidPhoneNumberTest() {
        registrationPage
                .openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userSex)
                .setPhoneNumber("546564564")
                .clickSubmit()
                .checkUnsubmittedForm();
    }
}
