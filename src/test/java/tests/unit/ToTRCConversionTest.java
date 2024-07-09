package tests.unit;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.CheckCheckbox;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pageobjects.RelationalConverterPage;
import tasks.EnterAQuery;
import tasks.InputSchemaTable;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.emptyString;
import static pageobjects.RelationalConverterPage.PRENEX_FORM_CHECKBOX;
import static pageobjects.RelationalConverterPage.TO_TRC_CONVERSION_BUTTON;
import static questions.QueryOutputErrorMessage.queryOutputErrorMessage;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class ToTRCConversionTest {
    private static final Actor james = Actor.named("James");

    private RelationalConverterPage relationalConverterPage;

    @Managed
    private WebDriver theBrowser;

    @BeforeEach
    public void setupActor() {
        givenThat(james).can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    public void toTRCConversion_correctInput_noError() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("A \\ B")
                .then(InputSchemaTable.ofNumber(1).withText("A(a1, a2)"))
                .then(InputSchemaTable.ofNumber(2).withText("B(a1, a2)"))
                .then(Click.on(TO_TRC_CONVERSION_BUTTON)));

        then(james).should(seeThat(queryOutputErrorMessage(), is((emptyString()))));
    }

    @Test
    public void toTRCConversion_correctInputWithPrenexFormEnabled_noError() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("A \\ B")
                .then(InputSchemaTable.ofNumber(1).withText("A(a1, a2)"))
                .then(InputSchemaTable.ofNumber(2).withText("B(a1, a2)"))
                .then(CheckCheckbox.of(PRENEX_FORM_CHECKBOX))
                .then(Click.on(TO_TRC_CONVERSION_BUTTON)));

        then(james).should(seeThat(queryOutputErrorMessage(), is((emptyString()))));
    }

    @Test
    public void toTRCConversion_incorrectSchema_appropriateError() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("A \\ B")
                .then(InputSchemaTable.ofNumber(1).withText("A(a1, a2)"))
                .then(InputSchemaTable.ofNumber(2).withText("B(b1, ab2)"))
                .then(CheckCheckbox.of(PRENEX_FORM_CHECKBOX))
                .then(Click.on(TO_TRC_CONVERSION_BUTTON)));

        then(james).should(seeThat(queryOutputErrorMessage(), is("Set difference requires union-compatible relations.")));
    }
}
