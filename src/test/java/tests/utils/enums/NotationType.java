package tests.utils.enums;

public enum NotationType {
    STANDARD ("STANDARD"),
    SIMPLIFIED ("SIMPLIFIED");

    public final String label;

    NotationType(String label) {
        this.label = label;
    }
}
