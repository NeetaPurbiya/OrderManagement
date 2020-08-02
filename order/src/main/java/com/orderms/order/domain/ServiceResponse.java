package com.orderms.order.domain;

import java.io.Serializable;

public class ServiceResponse implements Serializable {

	private static final long serialVersionUID = 2175476829866293178L;

	private String responseMessage;
	private Object resObject;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getResObject() {
		return resObject;
	}

	public void setResObject(Object resObject) {
		this.resObject = resObject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}