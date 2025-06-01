package demoQAtest;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static demoQAtest.TestData.*;


public class FormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    public void successfulRegistrationFullFormTest() {
        registrationPage
                .openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(userSex)
                .setPhoneNumber(userNumber)
                .setDateOfBirth(userDayOfBirthday, userMonthOfBirthday, userYearOfBirthday)
                .setSubjects(favoriteSubject)
                .setHobbies(favoriteHobby)
                .uploadPicture(imgPath)
                .setAddress(userAddress)
                .setState(userState)
                .setCity(userCity)
                .clickSubmit()
                .checkResult("Label", "Values")
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", userSex)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", userDayOfBirthday + " " + userMonthOfBirthday + "," + userYearOfBirthday)
                .checkResult("Subjects", favoriteSubject)
                .checkResult("Hobbies", favoriteHobby)
                .checkResult("Address", userAddress).checkResult("State and City", userState + " " + userCity);
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
