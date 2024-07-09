package questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;

import static pageobjects.RelationalConverterPage.schemaInputNumber;

public class InputSchemaCorrectness implements Question<Boolean> {
    private final int number;

    public InputSchemaCorrectness(int number) {
        this.number = number;
    }

    public static InputSchemaCorrectness number(int number) {
        return Instrumented.instanceOf(InputSchemaCorrectness.class).withProperties(number);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Attribute.of(schemaInputNumber(number)).named("data-wrong-input").answeredBy(actor).equals("false");
    }
}
