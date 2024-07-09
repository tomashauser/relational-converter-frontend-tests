package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import pageobjects.RelationalConverterPage;

public class EnterAQuery implements Task {
    private final Target textInput = RelationalConverterPage.TEXT_INPUT;

    private final String query;

    protected EnterAQuery(String query) {
        this.query = query;
    }

    public static EnterAQuery of(String query) {
        return Instrumented.instanceOf(EnterAQuery.class).withProperties(query);
    }

    @Override
    @Step("{0} enters a query")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(textInput).then(SendKeys.of(query).into(textInput)));
    }
}
