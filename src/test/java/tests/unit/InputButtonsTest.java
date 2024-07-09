package tests.unit;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageobjects.RelationalConverterPage;
import tasks.ClickOnInputButton;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static questions.TheInputText.theInputText;

@ExtendWith(SerenityJUnit5Extension.class)
public class InputButtonsTest {
    private static final Actor james = Actor.named("James");

    private RelationalConverterPage relationalConverterPage;

    @Managed
    private WebDriver theBrowser;

    @BeforeEach
    public void setupActor() {
        givenThat(james).can(BrowseTheWeb.with(theBrowser));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/input_buttons_test_data.csv", numLinesToSkip = 1)
    public void inputSymbols_pressingSymbolButton_correctSymbolIsDisplayedInInputField(String symbol, String expectedText) {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(ClickOnInputButton.withSymbol(symbol));

        then(james).should(seeThat(theInputText(), is(equalTo(expectedText))));
    }
}
