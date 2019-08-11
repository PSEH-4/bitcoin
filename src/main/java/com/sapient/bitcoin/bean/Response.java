package com.sapient.bitcoin.bean;

import java.util.List;

public class Response {

	private String currency;
	private String startDate;
	private String endDate;
	private List<Price> prices;

	public Response(String currency, String startDate, String endDate, List<Price> prices) {
		this.currency = currency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.prices = prices;
	}

	public Response() {
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

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

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

}
