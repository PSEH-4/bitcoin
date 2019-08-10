package com.sapient.bitcoin.bean;

public class Price {
	private String date;
	private double price;

	public Price(String date, double price) {
		this.date = date;
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Price [date=" + date + ", price=" + price + "]";
	}

}
