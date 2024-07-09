package tests.unit;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.HtmlAlert;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pageobjects.RelationalConverterPage;
import tasks.*;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static questions.ListOfSavedQueries.theListOfSavedQueries;
import static questions.TheInputText.theInputText;

@ExtendWith(SerenityJUnit5Extension.class)
public class SavingQueriesTest {
    private static final Actor james = Actor.named("James");

    private RelationalConverterPage relationalConverterPage;

    @Managed
    private WebDriver theBrowser;

    @BeforeEach
    public void setupActor() {
        givenThat(james).can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    public void saveQueryButton_saveQuery_queryIsSaved() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("π_{surname}(A) ⋈ B").then(SaveTheQuery.inTheTextInput()));

        then(james).should(seeThat(theListOfSavedQueries(), contains("π_{surname}(A) ⋈ B")));
    }

    @Test
    public void saveQueryDeletion_deletingSavedQuery_queryIsDeleted() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage), EnterAQuery.of("π_{surname}(A) ⋈ B").then(SaveTheQuery.inTheTextInput()));

        when(james).attemptsTo(DeleteSavedQuery.number(1));

        then(james).should(seeThat(theListOfSavedQueries(), is(empty())));
    }

    @Test
    public void savingQueryButton_savingFortyQueries_allQueriesAreSaved() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("π_{surname}(A) ⋈ B")
                .then(SaveTheQuery.fortyTimes())
                .then(EnterAQuery.of("abc"))
                .then(SaveTheQuery.inTheTextInput()));

        then(james).should(seeThat(theListOfSavedQueries(), hasSize(40)));
    }

    @Test
    public void savingQueryButton_savingFortyOneQueries_alertForExceedingFortyQueriesIsShownAndHasCorrectText() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("π_{surname}(A) ⋈ B")
                .then(SaveTheQuery.fortyTimes())
                .then(EnterAQuery.of("abc"))
                .then(SaveTheQuery.inTheTextInput()));

        assertThat(james.asksFor(HtmlAlert.text()), is("You can only save 40 queries."));
    }

    @Test
    public void savedQueryButton_clickingOnTheSavedQueryButton_replacesTheTextInTheInput() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(EnterAQuery.of("π_{surname}(A) ⋈ B")
                .then(SaveTheQuery.inTheTextInput())
                .then(ClearTheInput.text())
                .then(UseSavedQuery.number(1)));

        then(james).should(seeThat(theInputText(), is(equalTo("π_{surname}(A) ⋈ B"))));
    }
}
