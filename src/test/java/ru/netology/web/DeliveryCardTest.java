package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    LocalDate today = LocalDate.now();
    LocalDate newDate = today.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        SelenideElement form = $("form[class='form form_size_m form_theme_alfa-on-white']");
        form.$("[placeholder='Город']").setValue("Москва");
        form.$("[placeholder='Дата встречи']").doubleClick().sendKeys(formatter.format(newDate));
        form.$("[name=name]").setValue("Имя Фамилия");
        form.$("[name=phone]").setValue("+79815463321");
        form.$(".checkbox__box").click();
        $$(".button__content").find(exactText("Забронировать")).click();
        $(withText("Успешно")).waitUntil(visible, 15000);
    }

    @Test
    void shouldSubmitRequestWithDropDownList() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Мос");
        $$(".menu-item").find(exactText("Москва")).click();
        $("[placeholder='Дата встречи']").click();
        $(".calendar__day_state_current").click();
        $("[name=name]").setValue("Имя Фамилия");
        $("[name=phone]").setValue("+79815463321");
        $(".checkbox__box").click();
        $$(".button__content").find(exactText("Забронировать")).click();
        $(withText("Успешно")).waitUntil(visible, 15000);
    }
}
