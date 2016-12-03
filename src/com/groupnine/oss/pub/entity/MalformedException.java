package com.groupnine.oss.pub.entity;

public class MalformedException extends RuntimeException {

    // Constructors

    public MalformedException() {
        super();
    }

    public MalformedException(String message) {
        super(message);
    }

    public MalformedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedException(Throwable cause) {
        super(cause);
    }

    // No new methods
}
