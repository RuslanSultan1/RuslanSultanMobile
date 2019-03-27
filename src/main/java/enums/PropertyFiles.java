package enums;

public enum PropertyFiles {

    NATIVE("/src/main/resources/native.properties"),
    WEB("/src/main/resources/web.properties");

    public String appType;

    PropertyFiles(String value) {
        this.appType = value;
    }

    public String getAppType() {
        return appType;
    }
}
