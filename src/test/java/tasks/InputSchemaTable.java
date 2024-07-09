package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

import static pageobjects.RelationalConverterPage.schemaInputNumber;

public class InputSchemaTable implements Task {
    private final int number;
    private final String text;

    public InputSchemaTable(int number, String text) {
        this.number = number;
        this.text = text;
    }

    public static InputSchemaTable ofNumber(int number) {
        return new InputSchemaTable(number, "");
    }

    public InputSchemaTable withText(String text) {
        return Instrumented.instanceOf(InputSchemaTable.class).withProperties(this.number, text);
    }

    @Override
    @Step("{0} enters a table schema")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(schemaInputNumber(number)).then(Enter.theValue(text).into(schemaInputNumber(number))));
    }
}
