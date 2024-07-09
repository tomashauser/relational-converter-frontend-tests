package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Hit;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;

import static pageobjects.RelationalConverterPage.TEXT_INPUT;

public class ClearTheInput implements Task {
    public static ClearTheInput text() {
        return new ClearTheInput();
    }

    @Override
    @Step("{0} clears the input")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(TEXT_INPUT)
                .then(Hit.the(Keys.SHIFT, Keys.HOME).into(TEXT_INPUT))
                .then(Hit.the(Keys.BACK_SPACE).into(TEXT_INPUT)));
    }
}
