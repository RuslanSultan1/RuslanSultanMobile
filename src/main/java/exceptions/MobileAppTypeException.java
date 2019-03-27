package exceptions;

public class MobileAppTypeException extends Exception {

    public MobileAppTypeException() {
        super("Unknown type of mobile app");
    }
}
