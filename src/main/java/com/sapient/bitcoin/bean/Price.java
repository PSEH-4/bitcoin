package com.sapient.bitcoin.bean;

public class Price {
	private String date;
	private Object price;

	public Price(String date, Object price) {
		this.date = date;
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Object getPrice() {
		return price;
	}

	public void setPrice(Object price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Price [date=" + date + ", price=" + price + "]";
	}

}
