package com.sapient.bitcoin.bean;

public class Currency {

	private double usd;
	private double target;

	public Currency(double usd, double target) {
		this.usd = usd;
		this.target = target;
	}

	public double getUsd() {
		return usd;
	}

	public void setUsd(double usd) {
		this.usd = usd;
	}

	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "usd=" + usd + ", target=" + target + "]";
	}

}
