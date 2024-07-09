package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static pageobjects.RelationalConverterPage.TEXT_INPUT_CONTENT;

public class TheInputText implements Question<String> {
    public static TheInputText theInputText() {
        return new TheInputText();
    }

    @Override
    public String answeredBy(Actor actor) {
        return String.join("", Text.ofEach(TEXT_INPUT_CONTENT).answeredBy(actor));
    }
}
