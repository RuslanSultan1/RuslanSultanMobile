package enums;

public enum ContactTypes {
    HOME("Home"),
    WORK("Work"),
    MOBILE("Mobile"),
    OTHER("Other");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    ContactTypes(String value) {
        this.value = value;
    }
}
