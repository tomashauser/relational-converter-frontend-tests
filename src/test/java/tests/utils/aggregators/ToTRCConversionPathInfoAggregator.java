package tests.utils.aggregators;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import tests.utils.aggregators.pojos.ToTRCConversionPathInfo;

public class ToTRCConversionPathInfoAggregator implements ArgumentsAggregator {
    @Override
    public ToTRCConversionPathInfo aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        boolean switchToSimplifiedNotation = arguments.getString(1).equals("switch_to_simplified_notation");
        boolean enablePrenexForm = arguments.getString(2).equals("enable_prenex_form");

        return new ToTRCConversionPathInfo(switchToSimplifiedNotation, enablePrenexForm);
    }
}
