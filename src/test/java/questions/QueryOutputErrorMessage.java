package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.questions.Text;

import static pageobjects.RelationalConverterPage.QUERY_OUTPUT;

public class QueryOutputErrorMessage implements Question<String> {
    public static QueryOutputErrorMessage queryOutputErrorMessage() {
        return new QueryOutputErrorMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        if (Attribute.of(QUERY_OUTPUT).named("data-error").answeredBy(actor).equals("true")) {
            return Text.of(QUERY_OUTPUT).answeredBy(actor);
        }

        return "";
    }
}
