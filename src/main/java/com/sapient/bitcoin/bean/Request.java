package com.sapient.bitcoin.bean;

import javax.validation.constraints.NotEmpty;

public class Request {

	@NotEmpty
	private String startDate;
	@NotEmpty
	private String endDate;
	@NotEmpty
	private String currency;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Request [startDate=" + startDate + ", endDate=" + endDate + ", currency=" + currency + "]";
	}

}
