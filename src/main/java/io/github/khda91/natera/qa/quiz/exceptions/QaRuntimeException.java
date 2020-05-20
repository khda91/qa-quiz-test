package io.github.khda91.natera.qa.quiz.exceptions;

public class QaRuntimeException extends RuntimeException {

    public QaRuntimeException() {
        super();
    }

    public QaRuntimeException(String message) {
        super(message);
    }

    public QaRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public QaRuntimeException(Throwable cause) {
        super(cause);
    }

    protected QaRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void throwIfNull(Object object) {
        if (object == null) {
            throw this;
        }
    }

    public void throwIfTrue(boolean condition) {
        if (condition) {
            throw this;
        }
    }

    public void throwIfFalse(boolean condition) {
        if (!condition) {
            throw this;
        }
    }
}
