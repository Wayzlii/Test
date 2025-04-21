package otus.exception;

public class BrowserNotSupportedException extends RuntimeException {

    public BrowserNotSupportedException(String browserName) {
        super(String.format("Browser with name %s not supported.", browserName));
    }

}
