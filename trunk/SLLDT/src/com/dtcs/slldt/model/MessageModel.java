package com.dtcs.slldt.model;

public class MessageModel {
	private String smsContent;
	private String phoneNumber;
	private String contactName;
	boolean isMine;
	boolean isStatusMessage;

	public MessageModel(String message, boolean isMine) {
		super();
		this.smsContent = message;
		this.isMine = isMine;
		this.isStatusMessage = false;
	}

	public MessageModel(boolean status, String message) {
		super();
		this.smsContent = message;
		this.isMine = false;
		this.isStatusMessage = status;
	}

	public String getMessage() {
		return smsContent;
	}

	public void setMessage(String message) {
		this.smsContent = message;
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isStatusMessage() {
		return isStatusMessage;
	}

	public void setStatusMessage(boolean isStatusMessage) {
		this.isStatusMessage = isStatusMessage;
	}

}
