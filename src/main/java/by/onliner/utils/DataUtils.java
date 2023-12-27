package by.onliner.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.javafaker.Faker;

public final class DataUtils {
    private static final Faker FAKER = new Faker();

    public static String getRandomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String getRandomPassword() {
        String digits = FAKER.numerify("#####");
        String lowercase = FAKER.letterify("?");
        String uppercase = FAKER.letterify("?").toUpperCase();
        String specialChar = FAKER.bothify("!");
        return digits + lowercase + uppercase + specialChar + FAKER.funnyName();
    }

    public static String getRandomFirstName() {
        String name;
        do {
            name = FAKER.name().firstName();
        } while (name.contains("'"));
        return name;
    }

    public static String getRandomLastName() {
        String lastName;
        do {
            lastName = FAKER.name().lastName();
        } while (lastName.contains("'"));
        return lastName;
    }

    public static String getRandomBirthDate() {
        LocalDate randomDate = FAKER.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return formatter.format(randomDate);
    }

    public static String getRandomAddress() {
        return FAKER.address().streetAddress();
    }

    public static String getRandomCity(){
        return FAKER.address().city();
    }

    public static String getRandomZip(){
        return FAKER.number().digits(5);
    }
}
