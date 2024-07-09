package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.targets.Target;
import pageobjects.RelationalConverterPage;

import java.util.List;

public class ListOfSavedQueries implements Question<List<String>> {
    private final Target savedQueriesList = RelationalConverterPage.SAVED_QUERIES_LIST_ITEMS;

    public static Question<List<String>> theListOfSavedQueries() {
        return new ListOfSavedQueries();
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        return (List<String>) Attribute.ofEach(savedQueriesList).named("data-content").answeredBy(actor);
    }
}
