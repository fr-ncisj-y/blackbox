package com.jayplusplus.blackbox.exception;

/**
 * The type Global exception.
 */
public class GlobalException extends RuntimeException {
    private int code;

    /**
     * Instantiates a new Global exception.
     *
     * @param code    the code
     * @param message the message
     */
    public GlobalException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }
}
