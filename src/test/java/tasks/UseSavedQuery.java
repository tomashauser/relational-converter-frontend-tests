package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static pageobjects.RelationalConverterPage.theQueryOf;

public class UseSavedQuery implements Task {
    private final int theGivenQueryNumber;

    protected UseSavedQuery(int queryNumber) {
        this.theGivenQueryNumber = queryNumber;
    }

    public static UseSavedQuery number(int queryNumber) {
        return Instrumented.instanceOf(UseSavedQuery.class).withProperties(queryNumber);
    }

    @Override
    @Step("{0} uses a saved query")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(theQueryOf(theGivenQueryNumber)));
    }
}
