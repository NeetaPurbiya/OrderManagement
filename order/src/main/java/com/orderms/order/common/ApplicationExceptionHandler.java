package com.orderms.order.common;

public class ApplicationExceptionHandler extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3502804114818676613L;

	public ApplicationExceptionHandler(String message) {
        super(message);
    }

}
