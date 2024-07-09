package questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.CurrentVisibility;

import static pageobjects.RelationalConverterPage.MOVE_TO_INPUT_BUTTON;

public class MoveToInputButton implements Question<Boolean> {
    private final boolean invertOutput;

    public MoveToInputButton(boolean invertOutput) {
        this.invertOutput = invertOutput;
    }

    public static MoveToInputButton isVisible() {
        return Instrumented.instanceOf(MoveToInputButton.class).withProperties(false);
    }

    public static MoveToInputButton isNotVisible() {
        return Instrumented.instanceOf(MoveToInputButton.class).withProperties(true);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean isVisible = actor.asksFor(CurrentVisibility.of(MOVE_TO_INPUT_BUTTON));

        if (invertOutput) {
            return !isVisible;
        }

        return isVisible;
    }
}
