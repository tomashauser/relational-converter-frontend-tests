package tests.integration;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pageobjects.RelationalConverterPage;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;
import static pageobjects.RelationalConverterPage.*;
import static pageobjects.RelationalConverterPage.MOVE_TO_INPUT_BUTTON;
import static questions.TheInputText.theInputText;

@ExtendWith(SerenityJUnit5Extension.class)
public class RandomQueryGenerationRepeatedTest {
    private static final Actor james = Actor.named("James");

    private RelationalConverterPage relationalConverterPage;

    @Managed
    private WebDriver theBrowser;

    @BeforeEach
    public void setupActor() {
        givenThat(james).can(BrowseTheWeb.with(theBrowser));
    }

    @RepeatedTest(3)
    public void randomQueryGeneration_generateConvertToSimplifiedNotationConvertBackAndCompare_theResultsAreIdentical() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(Click.on(RANDOM_QUERY_BUTTON));

        String textGeneratedInTheBeginning = james.asksFor(theInputText());

        when(james).attemptsTo(Click.on(NOTATION_CONVERSION_BUTTON)
                .then(Click.on(SIMPLIFIED_NOTATION_BUTTON))
                .then(Click.on(MOVE_TO_INPUT_BUTTON))
                .then(Click.on(NOTATION_CONVERSION_BUTTON))
                .then(Click.on(MOVE_TO_INPUT_BUTTON))
        );

        then(james).can(seeThat(theInputText(), is(textGeneratedInTheBeginning)));
    }
}
