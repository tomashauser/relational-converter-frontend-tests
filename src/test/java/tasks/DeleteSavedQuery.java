package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.RightClick;
import net.thucydides.core.annotations.Step;

import static pageobjects.RelationalConverterPage.theQueryOf;

public class DeleteSavedQuery implements Task {
    private final int theGivenQueryNumber;

    protected DeleteSavedQuery(int queryNumber) {
        this.theGivenQueryNumber = queryNumber;
    }

    public static DeleteSavedQuery number(int queryNumber) {
        return Instrumented.instanceOf(DeleteSavedQuery.class).withProperties(queryNumber);
    }

    @Override
    @Step("{0} deletes a query")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(RightClick.on(theQueryOf(theGivenQueryNumber)));
    }
}
