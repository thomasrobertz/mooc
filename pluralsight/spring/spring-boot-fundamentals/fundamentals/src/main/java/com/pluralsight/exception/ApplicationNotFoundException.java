package com.pluralsight.exception;

public class ApplicationNotFoundException extends RuntimeException {

    /**
	 * svuid
	 */
	private static final long serialVersionUID = -3975577793806778390L;

	public ApplicationNotFoundException(String exception) {
        super(exception);
    }
}
