package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static pageobjects.RelationalConverterPage.inputButtonWithSymbol;

public class ClickOnInputButton implements Task {
    private final String symbol;

    protected ClickOnInputButton(String symbol) {
        this.symbol = symbol;
    }

    public static ClickOnInputButton withSymbol(String symbol) {
        return Instrumented.instanceOf(ClickOnInputButton.class).withProperties(symbol);
    }

    @Override
    @Step("{0} clicks on an input button")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(inputButtonWithSymbol(symbol)));
    }
}
