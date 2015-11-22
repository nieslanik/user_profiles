package cz.muni.fi.pa165.exceptions;

public class LibraryServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LibraryServiceException() {
    }

    public LibraryServiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LibraryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LibraryServiceException(String message) {
        super(message);
    }

    public LibraryServiceException(Throwable cause) {
        super(cause);
    }
}
