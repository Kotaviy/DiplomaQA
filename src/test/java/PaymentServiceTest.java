import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

public class PaymentServiceTest {
    @Test

    public void ShouldBuyTour() throws InterruptedException {
        open("http://localhost:8080");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        ElementsCollection field = $$(By.className("input__control"));
        form.$(By.cssSelector(".button_size_m")).click();
        field.get(0).setValue("4444 4444 4444 4441");
        field.get(1).setValue("04");
        field.get(2).setValue("25");
        field.get(3).setValue("Иванова Ольга");
        field.get(4).setValue("894");
        ElementsCollection button = $$(By.className("button_size_m"));
        button.get(2).click();
        SelenideElement page = $(By.id("root"));
        page.scrollIntoView(true);
        Thread.sleep(3000);
        SelenideElement notification = $(By.className("notification"));
        notification.shouldBe(Condition.visible).shouldHave(Condition.text("Успешно"));
    }

    @Test

    public void ShouldBuyTourOnCredit() throws InterruptedException {
        open("http://localhost:8080");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        ElementsCollection field = $$(By.className("input__control"));
        form.$(By.cssSelector(".button_view_extra ")).click();
        field.get(0).setValue("4444 4444 4444 4441");
        field.get(1).setValue("04");
        field.get(2).setValue("25");
        field.get(3).setValue("Иванова Ольга");
        field.get(4).setValue("894");
        ElementsCollection button = $$(By.className("button_size_m"));
        button.get(2).click();
        SelenideElement page = $(By.id("root"));
        page.scrollIntoView(true);
        Thread.sleep(3000);
        SelenideElement notification = $(By.className("notification"));
        notification.shouldBe(Condition.visible).shouldHave(Condition.text("Успешно"));
    }
    @Test
    public void OtherCard() throws InterruptedException {
        open("http://localhost:8080");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        ElementsCollection field = $$(By.className("input__control"));
        form.$(By.cssSelector(".button_view_extra ")).click();
        field.get(0).setValue("4444 4444 4444 4442");
        field.get(1).setValue("02");
        field.get(2).setValue("27");
        field.get(3).setValue("Иванова Ольга");
        field.get(4).setValue("204");
        ElementsCollection button = $$(By.className("button_size_m"));
        button.get(2).click();
        SelenideElement page = $(By.id("root"));
        page.scrollIntoView(true);
        Thread.sleep(3000);
        SelenideElement notification = $(By.className("notification"));
        notification.shouldBe(Condition.visible).shouldHave(Condition.text("Ошибка"));
    }
    @ParameterizedTest
    @CsvSource({
            "00,23",
            "01,24",
            "11,25",
            "12,29",
            "13,30",
    })
    public void LimitValues(String month, String year) throws InterruptedException {
        open("http://localhost:8080");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        ElementsCollection field = $$(By.className("input__control"));
        form.$(By.cssSelector(".button_view_extra ")).click();
        field.get(0).setValue("4444 4444 4444 4441");
        field.get(1).setValue(month);
        field.get(2).setValue(year);
        field.get(3).setValue("Иванова Ольга");
        field.get(4).setValue("894");
        ElementsCollection button = $$(By.className("button_size_m"));
        button.get(2).click();
        SelenideElement page = $(By.id("root"));
        page.scrollIntoView(true);
        Thread.sleep(3000);
        SelenideElement notification = $(By.className("notification"));
        notification.shouldBe(Condition.visible);
    }
    @ParameterizedTest
    @CsvSource({
            "Ольгаиванова",
            "ОльгаИванова",
            "ОЛЬГА ИВАНОВА",
            "оЛЬГАИВАНОВА",
            "OLGA IVANOVA",
            "OL'GA IVANOVA",
            "olga ivanova",
            "Ольга-Мария Иванова",
            "Ольга12 Иванова",
            "Ольга Ёлкина",
            "Ольга Иванова-Петрова"
    })
    public void Names(String name) {
        open("http://localhost:8080");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        ElementsCollection field = $$(By.className("input__control"));
        form.$(By.cssSelector(".button_view_extra ")).click();
        field.get(0).setValue("4444 4444 4444 4441");
        field.get(1).setValue("04");
        field.get(2).setValue("25");
        field.get(3).setValue(name);
        field.get(4).setValue("894");
        ElementsCollection button = $$(By.className("button_size_m"));
        button.get(2).click();
        SelenideElement page = $(By.id("root"));
        page.scrollIntoView(true);
        SelenideElement notification = $(By.className("notification"));
        notification.shouldBe(Condition.visible).shouldHave(Condition.text("Успешно"));
    }
    @Test
    public void EmptyFields() throws InterruptedException {
        open("http://localhost:8080");
        SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        ElementsCollection field = $$(By.className("input__control"));
        form.$(By.cssSelector(".button_view_extra ")).click();
        ElementsCollection button = $$(By.className("button_size_m"));
        button.get(2).click();
        Thread.sleep(3000);
    }
}