package ponomarev.andrei;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;


public class ParamTest {
    @BeforeAll
    static void config() {

        Configuration.browserSize = "1920x1080";

    }

    @ValueSource(strings = {"iphone", "ipad"})
    @ParameterizedTest(name = "Checking the display of a block with delivery times for {0}")
    void deliveryPeriodTest(String testValue) {
        open("https://ozon.ru/");
        //срабатывает капча, обходной вариант нашел только такой..
        clearBrowserCookies();
        clearBrowserLocalStorage();
        //
        $(byName("text")).setValue(testValue);
        $(".aja4").click();
        //срабатывает капча, обходной вариант нашел только такой..
        clearBrowserCookies();
        clearBrowserLocalStorage();
        //
        $(".gaa9")
                .shouldHave(text("Неважно"))
                .shouldHave(text("Express — за час!"))
                .shouldHave(text("Сегодня"))
                .shouldHave(text("Сегодня или завтра"))
                .shouldHave(text("До 2 дней"))
                .shouldHave(text("До 5 дней"));
        //срабатывает капча, обходной вариант нашел только такой..
        clearBrowserCookies();
        clearBrowserLocalStorage();
        //layoutPage
    }

    @CsvSource(value = {
            "iphone, Смартфон Apple iPhone",
            "ipad, Планшет Apple iPad",
    })
    @ParameterizedTest(name = "Checking the display of the search {1} in the window {0}")
    void searchResultTest(String testValue, String expectedValueSearch) {
        open("https://ozon.ru/");
        //срабатывает капча, обходной вариант нашел только такой..
        clearBrowserCookies();
        clearBrowserLocalStorage();
        //
        $(byName("text")).setValue(testValue);
        $(".aja4").click();
        //срабатывает капча, обходной вариант нашел только такой..
        clearBrowserCookies();
        clearBrowserLocalStorage();
        //
        $(".widget-search-result-container")
                .should(text(expectedValueSearch));

        //срабатывает капча, обходной вариант нашел только такой..
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }


    @EnumSource(Product.class)
    @ParameterizedTest(name = "Checking the display of currency in the product price for {0}")
    void currencyProductTest(Product product) {
        open("https://ozon.ru/");
        //срабатывает капча, обходной вариант нашел только такой..
        clearBrowserCookies();
        clearBrowserLocalStorage();
        //
        $(byName("text")).setValue(product.name());
        $(".aja4").click();
        //срабатывает капча, обходной вариант нашел только такой..
        clearBrowserCookies();
        clearBrowserLocalStorage();
        //
        $(".ui-p4").shouldHave(text("₽"));
    }
}