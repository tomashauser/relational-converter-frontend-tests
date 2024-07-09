package tests.utils.aggregators;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import tests.utils.aggregators.pojos.NotationConversionPathInfo;

public class NotationConversionPathInfoAggregator implements ArgumentsAggregator {
    @Override
    public NotationConversionPathInfo aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
        boolean switchToSimplifiedNotation = arguments.getString(1).equals("switch_to_simplified_notation");
        boolean enableFormatting = arguments.getString(2).equals("do_format");
        boolean enableSemanticChecking = arguments.getString(3).equals("enable_checking");

        return new NotationConversionPathInfo(switchToSimplifiedNotation, enableFormatting, enableSemanticChecking);
    }
}
