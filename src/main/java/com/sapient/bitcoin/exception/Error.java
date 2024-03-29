package com.sapient.bitcoin.exception;

public class Error {

	private String cause;
	private int status;
	private String possibleReason = "Make sure you have supplied correct currency / dates in format [yyyy-MM-dd]";

	public Error(String cause, int status) {
		this.cause = cause;
		this.status = status;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPossibleReason() {
		return possibleReason;
	}

	public void setPossibleReason(String possibleReason) {
		this.possibleReason = possibleReason;
	}

}
