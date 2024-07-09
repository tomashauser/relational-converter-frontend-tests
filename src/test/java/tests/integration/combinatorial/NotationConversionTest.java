package tests.integration.combinatorial;

import tests.utils.enums.NotationType;
import tests.utils.enums.SchemaInputType;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.CheckCheckbox;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageobjects.RelationalConverterPage;
import tasks.EnterAQuery;
import tasks.InputSchemaTable;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.*;
import static pageobjects.RelationalConverterPage.*;
import static questions.NoErrorOccurred.noErrorOccurred;
import static questions.QueryOutputErrorMessage.queryOutputErrorMessage;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * In this test class we only test whether, given a combination of actions, there is an error message in the output.
 * It is sufficient as the actual messages are tested on backend as well as the correctness of the conversion process.
 * Furthermore, it is not feasible to read the output as it is in a form of LaTeX scribbled in HTML.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

@ExtendWith(SerenityJUnit5Extension.class)
public class NotationConversionTest {
    private static final Actor james = Actor.named("James");

    private RelationalConverterPage relationalConverterPage;

    @Managed
    private WebDriver theBrowser;

    @BeforeEach
    public void setupActor() {
        givenThat(james).can(BrowseTheWeb.with(theBrowser));
    }

    private void combinatorialConversionTestBody(NotationType notationType,
                                                 SchemaInputType schemaCorrectness,
                                                 boolean semanticCheckingEnabled,
                                                 boolean formattingEnabled) {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        String query = notationType.equals(NotationType.SIMPLIFIED) ? "A[a]" : "π_{a}(A)";
        String schema = "";

        switch (schemaCorrectness) {
            case EMPTY -> schema = "";
            case CORRECT -> schema = "A(a)";
            case INCORRECT -> schema = "A(b)";
        }

        when(james).attemptsTo(EnterAQuery.of(query).then(InputSchemaTable.ofNumber(1).withText(schema)));

        if (semanticCheckingEnabled) {
            when(james).attemptsTo(CheckCheckbox.of(SEMANTIC_CHECKING_CHECKBOX));
        }

        if (formattingEnabled) {
            when(james).attemptsTo(CheckCheckbox.of(FORMATTING_CHECKBOX));
        }

        if (notationType.equals(NotationType.SIMPLIFIED)) {
            when(james).attemptsTo(CheckCheckbox.of(SIMPLIFIED_NOTATION_BUTTON));
        }

        when(james).attemptsTo(Click.on(NOTATION_CONVERSION_BUTTON));

        if (schemaCorrectness.equals(SchemaInputType.CORRECT) || !semanticCheckingEnabled) {
            then(james).should(seeThat(noErrorOccurred()));
        } else {
            then(james).should(seeThat(queryOutputErrorMessage(), is(not(emptyString()))));
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/notationconversioncombinations/NotationConversionPairwise.csv", numLinesToSkip = 1)
    public void combinatorialConversionTestsPairwise(NotationType notationType,
                                                     SchemaInputType schemaCorrectness,
                                                     boolean semanticCheckingEnabled,
                                                     boolean formattingEnabled) {
        combinatorialConversionTestBody(notationType, schemaCorrectness, semanticCheckingEnabled, formattingEnabled);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/notationconversioncombinations/NotationConversionMixedStrength.csv", numLinesToSkip = 1)
    //@Disabled("Only pairwise combinatorial testing is enabled as not to make the test report unnecessarily long")
    public void combinatorialConversionTestsMixedStrength(NotationType notationType,
                                                     SchemaInputType schemaCorrectness,
                                                     boolean semanticCheckingEnabled,
                                                     boolean formattingEnabled) {
        combinatorialConversionTestBody(notationType, schemaCorrectness, semanticCheckingEnabled, formattingEnabled);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/notationconversioncombinations/NotationConversionThreeWay.csv", numLinesToSkip = 1)
   // @Disabled("Only pairwise combinatorial testing is enabled as not to make the test report unnecessarily long")
    public void combinatorialConversionTestsThreeWay(NotationType notationType,
                                                          SchemaInputType schemaCorrectness,
                                                          boolean semanticCheckingEnabled,
                                                          boolean formattingEnabled) {
        combinatorialConversionTestBody(notationType, schemaCorrectness, semanticCheckingEnabled, formattingEnabled);
    }
}
