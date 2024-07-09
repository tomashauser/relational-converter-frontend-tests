package tests.integration.combinatorial;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import pageobjects.RelationalConverterPage;
import questions.InputSchemaCorrectness;
import tasks.InputSchemaTable;
import tests.utils.InvalidTableNamesGenerator;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(SerenityJUnit5Extension.class)
public class SchemaInputTest {
    private static final Actor james = Actor.named("James");

    private RelationalConverterPage relationalConverterPage;

    @Managed
    private WebDriver theBrowser;

    @BeforeEach
    public void setupActor() {
        givenThat(james).can(BrowseTheWeb.with(theBrowser));
    }

    @ParameterizedTest
    @EmptySource
    @ArgumentsSource(InvalidTableNamesGenerator.class)
    public void invalidTableNamesTest(String invalidTableDeclaration) {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(InputSchemaTable.ofNumber(1).withText(invalidTableDeclaration));

        then(james).should(seeThat(InputSchemaCorrectness.number(1), is(false)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A(a1)", "A(a)", "Abc(A-)", "Abc(A_, b12-_)", "A(a1, a2), A(a, b, c)"})
    public void validTableNameTest() {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        when(james).attemptsTo(InputSchemaTable.ofNumber(1).withText("A(a1, a2)"));

        then(james).should(seeThat(InputSchemaCorrectness.number(1), is(true)));
    }
}
