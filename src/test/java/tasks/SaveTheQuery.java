package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import pageobjects.RelationalConverterPage;

public class SaveTheQuery implements Task {
    private final Target saveQueryButton = RelationalConverterPage.SAVE_QUERY_BUTTON;

    private int numberOfTimes;

    protected SaveTheQuery(int numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    public static SaveTheQuery inTheTextInput() {
        return Instrumented.instanceOf(SaveTheQuery.class).withProperties(1);
    }

    public static SaveTheQuery fortyTimes() {
        return Instrumented.instanceOf(SaveTheQuery.class).withProperties(40);
    }

    @Override
    @Step("{0} saves a query")
    public <T extends Actor> void performAs(T actor) {
        while(numberOfTimes --> 0) {
            actor.attemptsTo(Click.on(saveQueryButton));
        }
    }
}
