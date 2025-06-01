package demoQAtest;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class TestData {

    public static String[] genders = {"Male", "Female", "Other"};
    public static String[] hobbies = {"Sports", "Reading", "Music"};
    public static String[] subjects = {"English", "Maths", "Arts"};
    public static String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    public static String[] citiesNcr = {"Delhi", "Gurgaon", "Noida"};
    public static String[] citiesUttarPradesh = {"Agra", "Merrut", "Lucknow"};
    public static String[] citiesHaryana = {"Karnal", "Panipat"};
    public static String[] citiesRajasthan = {"Jaipur", "Jaiselmer"};

    public static Faker faker = new Faker(new Locale("en"));
    public static Random random = new Random();

    public static String name = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String email = faker.internet().emailAddress();
    public static String userSex = genders[random.nextInt(genders.length)];
    public static String userNumber = faker.numerify("77########");;
    public static String favoriteSubject = subjects[random.nextInt(subjects.length)];
    public static String favoriteHobby = hobbies[random.nextInt(hobbies.length)];
    public static String imgPath = "test.jpg";
    public static String userAddress = faker.address().cityName();
    public static String userState = states[random.nextInt(states.length)];
    public static String userCity = getRandomUserCity(userState, random);
    public static String userMonthOfBirthday = getRandomBirthMonth();
    public static String userDayOfBirthday = getRandomBirthDay();
    public static String userYearOfBirthday = String.valueOf(faker.number().numberBetween(1950,2001));

    public static String getRandomBirthDay() {
        return String.format("%02d", faker.number().numberBetween(1, 28));
    }

    public static String getRandomBirthMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }


    public static String getRandomUserCity(String state, Random random){
        return switch (state) {
            case "NCR" -> citiesNcr[random.nextInt(citiesNcr.length)];
            case "Uttar Pradesh" -> citiesUttarPradesh[random.nextInt(citiesUttarPradesh.length)];
            case "Haryana" -> citiesHaryana[random.nextInt(citiesHaryana.length)];
            case "Rajasthan" -> citiesRajasthan[random.nextInt(citiesRajasthan.length)];
            default -> "Invalid state";
        };
    }
}
