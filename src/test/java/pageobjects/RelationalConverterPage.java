package pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://localhost:3000/")
public class RelationalConverterPage extends PageObject {
    public static final Target TEXT_INPUT = Target.the("Text input").locatedBy(".public-DraftEditor-content");

    public static final Target SAVE_QUERY_BUTTON = Target.the("Save button").locatedBy("#save-query-button");

    public static final Target SAVED_QUERIES_LIST_ITEMS = Target.the("Saved queries list").locatedBy("#saved-queries-list > *");

    public static final Target TEXT_INPUT_CONTENT = Target.the("Text input content").locatedBy(TEXT_INPUT.getCssOrXPathSelector() + " [data-text=\"true\"]");

    public static final Target NOTATION_CONVERSION_BUTTON = Target.the("Notation conversion button").locatedBy("#notation-conversion-button");

    public static final Target SIMPLIFIED_NOTATION_BUTTON = Target.the("Simplified notation button").locatedBy("#simplified-notation-button");

    public static final Target TO_TRC_CONVERSION_BUTTON = Target.the("To TRC conversion button").locatedBy("#to-trc-conversion-button");

    public static final Target QUERY_OUTPUT = Target.the("Query output").locatedBy("#query-view-input-false");

    public static final Target SEMANTIC_CHECKING_CHECKBOX = Target.the("Semantic checking checkbox").locatedBy("#semanticChecking");

    public static final Target FORMATTING_CHECKBOX = Target.the("Formatting checkbox").locatedBy("#formatting");

    public static final Target PRENEX_FORM_CHECKBOX = Target.the("Prenex form checkbox").locatedBy("#prenexForm");

    public static final Target MOVE_TO_INPUT_BUTTON = Target.the("Move to input button").locatedBy("#move-to-input-button");

    public static final Target RANDOM_QUERY_BUTTON = Target.the("Random query button").locatedBy("#random-query-generation-button");

    public static Target theQueryOf(int number) {
        String nthSavedQuerySelector = "#saved-query-" + number;

        return Target.the("Saved query").locatedBy(nthSavedQuerySelector);
    }

    public static Target inputButtonWithSymbol(String symbol) {
        String selector = String.format("//button[contains(text(),'%s')]", symbol);
        return Target.the("Input button with symbol " + symbol).locatedBy(selector);
    }

    public static Target schemaInputNumber(int number) {
        // The first child is a label, so we need to seek the child element of (number + 1)
        String selector = String.format("#schema-input-panel > input:nth-child(%s)", number + 1);
        return Target.the("Schema input number " + number).locatedBy(selector);
    }
}
