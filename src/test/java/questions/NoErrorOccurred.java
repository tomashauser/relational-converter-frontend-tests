package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static questions.QueryOutputErrorMessage.queryOutputErrorMessage;

public class NoErrorOccurred implements Question<Boolean> {
    public static NoErrorOccurred noErrorOccurred() {
        return new NoErrorOccurred();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return actor.asksFor(queryOutputErrorMessage()).equals("");
    }
}
