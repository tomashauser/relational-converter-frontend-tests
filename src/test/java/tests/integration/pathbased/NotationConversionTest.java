package tests.integration.pathbased;

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
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageobjects.RelationalConverterPage;
import tasks.EnterAQuery;
import tasks.InputSchemaTable;
import tests.utils.aggregators.pojos.NotationConversionPathInfo;
import tests.utils.aggregators.NotationConversionPathInfoAggregator;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static pageobjects.RelationalConverterPage.*;
import static questions.NoErrorOccurred.noErrorOccurred;

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

    public void notationConversionTestBody(NotationConversionPathInfo pathInfo) {
        givenThat(james).wasAbleTo(Open.browserOn(relationalConverterPage));

        if (pathInfo.enableFormatting) {
            when(james).attemptsTo(CheckCheckbox.of(SEMANTIC_CHECKING_CHECKBOX)
                    .then(InputSchemaTable.ofNumber(1).withText("A(a)")));
        }

        if (pathInfo.switchToSimplifiedNotation) {
            when(james).attemptsTo(Click.on(SIMPLIFIED_NOTATION_BUTTON).then(EnterAQuery.of("A[a]")));
        } else {
            when(james).attemptsTo(EnterAQuery.of("π_{a}(A)"));
        }

        if (pathInfo.enableFormatting) {
            when(james).attemptsTo(CheckCheckbox.of(FORMATTING_CHECKBOX));
        }

        then(james).should(seeThat(noErrorOccurred()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/oxygen/notationconversion/TDL1.csv")
    public void notationConversion_onlyValidPathsTDL1_noError(@AggregateWith(NotationConversionPathInfoAggregator.class) NotationConversionPathInfo notationConversionPathInfo) {
        notationConversionTestBody(notationConversionPathInfo);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/oxygen/notationconversion/TDL2.csv")
    public void notationConversion_onlyValidPathsTDL2_noError(@AggregateWith(NotationConversionPathInfoAggregator.class) NotationConversionPathInfo notationConversionPathInfo) {
        notationConversionTestBody(notationConversionPathInfo);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/oxygen/notationconversion/TDL3.csv")
    public void notationConversion_onlyValidPathsTDL3_noError(@AggregateWith(NotationConversionPathInfoAggregator.class) NotationConversionPathInfo notationConversionPathInfo) {
        notationConversionTestBody(notationConversionPathInfo);
    }
}
