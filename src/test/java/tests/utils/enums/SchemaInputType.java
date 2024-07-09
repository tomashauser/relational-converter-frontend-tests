package tests.utils.enums;

public enum SchemaInputType {
    CORRECT("CORRECT"),
    INCORRECT("INCORRECT"),
    EMPTY("EMPTY");

    public final String label;

    SchemaInputType(String label) {
        this.label = label;
    }
}
