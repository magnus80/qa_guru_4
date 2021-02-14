package io.qaguru;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

class DemoqaFakerTest {

    Faker faker = new Faker();

    final String FIRST_NAME = faker.name().firstName(),
            LAST_NAME = faker.name().lastName(),
            USER_EMAIL = faker.internet().emailAddress(),
            USER_NUMBER = faker.phoneNumber().subscriberNumber(10),
            CURRENT_ADDRESS = faker.address().streetAddress(),
            GENDER = "Male",
            DATE = "05 February,2021",
            SUBJECT = "Biology",
            HOBBIES = "Sports",
            PICTURE = "guy.jpeg",
            STATE_AND_CITY = "Uttar Pradesh Agra";

    @BeforeAll
    static void setUp() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void iCanFillAndCheckFormTest() {

        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(USER_EMAIL);
        $("[for='gender-radio-1']").click();
        $("#userNumber").setValue(USER_NUMBER);
        $("#dateOfBirthInput").click();
        $$(".react-datepicker__day")
                .find(exactText("5")).click();
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("Bio");
        $(".subjects-auto-complete__menu-list").click();
        $("[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("src/test/java/resources/guy.jpeg"));
        $("#currentAddress").setValue(CURRENT_ADDRESS);
        $("#state").scrollTo().click();
        $("#react-select-3-option-1").click();
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();

        $$x("//tbody//td[2]").shouldHave(exactTexts(FIRST_NAME + " " + LAST_NAME, USER_EMAIL, GENDER,
                USER_NUMBER, DATE, SUBJECT, HOBBIES, PICTURE, CURRENT_ADDRESS, STATE_AND_CITY));
    }
}
