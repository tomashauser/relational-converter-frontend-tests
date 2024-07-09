package tests.utils.aggregators.pojos;

public class NotationConversionPathInfo {
    public final boolean switchToSimplifiedNotation;
    public final boolean enableFormatting;
    public final boolean enableSemanticChecking;

    public NotationConversionPathInfo(boolean switchToSimplifiedNotation, boolean enableFormatting, boolean enableSemanticChecking) {
        this.switchToSimplifiedNotation = switchToSimplifiedNotation;
        this.enableFormatting = enableFormatting;
        this.enableSemanticChecking = enableSemanticChecking;
    }
}
