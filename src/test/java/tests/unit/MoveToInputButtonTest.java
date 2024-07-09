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
import questions.MoveToInputButton;
import tasks.EnterAQuery;
import tasks.InputSchemaTable;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static pageobjects.RelationalConverterPage.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class MoveToInputButtonTest {
    private static final Actor james = Actor.named("James");

    private RelationalConverterPage relationalConverterPage;

    @Managed
    private WebDriver theBrowser;

    @BeforeEach
    public void setupActor() {
        givenThat(james).can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    public void moveToInputButton_arrivingAtTheWebpage_isNotShown() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        then(james).should(seeThat(MoveToInputButton.isNotVisible()));
    }


    @Test
    public void moveToInputButton_correctNotationConversionFromStandardToSimplified_isShown() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("π_{name}(A)")
                .then(Click.on(NOTATION_CONVERSION_BUTTON)));

        then(james).should(seeThat(MoveToInputButton.isVisible()));
    }

    @Test
    public void moveToInputButton_correctNotationConversionFromSimplifiedToStandard_isShown() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("A[a]")
                .then(Click.on(SIMPLIFIED_NOTATION_BUTTON))
                .then(Click.on(NOTATION_CONVERSION_BUTTON)));

        then(james).should(seeThat(MoveToInputButton.isVisible()));
    }

    @Test
    public void moveToInputButton_incorrectNotationConversionFromStandardToSimplified_isNotShown() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("π_{name}(A)qwqdqdwq")
                .then(Click.on(NOTATION_CONVERSION_BUTTON)));

        then(james).should(seeThat(MoveToInputButton.isNotVisible()));
    }

    @Test
    public void moveToInputButton_incorrectNotationConversionFromSimplifiedToStandard_isNotShown() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("A[a")
                .then(Click.on(SIMPLIFIED_NOTATION_BUTTON))
                .then(Click.on(NOTATION_CONVERSION_BUTTON)));

        then(james).should(seeThat(MoveToInputButton.isNotVisible()));
    }

    @Test
    public void moveToInputButton_notationConversionIntoTRC_isNotShown() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("A \\ B")
                .then(InputSchemaTable.ofNumber(1).withText("A(a1, a2)"))
                .then(InputSchemaTable.ofNumber(2).withText("B(a1, a2)"))
                .then(CheckCheckbox.of(PRENEX_FORM_CHECKBOX))
                .then(Click.on(TO_TRC_CONVERSION_BUTTON)));

        then(james).should(seeThat(MoveToInputButton.isNotVisible()));
    }
}
