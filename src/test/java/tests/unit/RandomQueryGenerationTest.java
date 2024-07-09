package tests.unit;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pageobjects.RelationalConverterPage;
import tasks.EnterAQuery;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static pageobjects.RelationalConverterPage.*;
import static questions.TheInputText.theInputText;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class RandomQueryGenerationTest {
    private static final Actor james = Actor.named("James");

    private RelationalConverterPage relationalConverterPage;

    @Managed
    private WebDriver theBrowser;

    @BeforeEach
    public void setupActor() {
        givenThat(james).can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    public void randomQueryGeneration_withoutTextInInputField_addsTextIntoInputField() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(Click.on(RANDOM_QUERY_BUTTON));

        then(james).should(seeThat(theInputText(), is(not(emptyString()))));
    }

    @Test
    public void randomQueryGeneration_withTextInInputField_replacesTheTextInInputField() {
        String someText = "qwdbqwujdbqwjb";

        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of(someText).then(Click.on(RANDOM_QUERY_BUTTON)));

        then(james).should(seeThat(theInputText(), is(not(someText))));
    }
}
