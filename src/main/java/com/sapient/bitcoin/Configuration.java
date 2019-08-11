package com.sapient.bitcoin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

	@Value("${bitcoin.price.endpoint}")
	private String bitcoinApiEndpoint;

	@Value("${bitcoin.currency.endpoint}")
	private String bitcoinCurrencyEndpoint;

	public String getBitcoinApiEndpoint() {
		return bitcoinApiEndpoint;
	}

	public void setBitcoinApiEndpoint(String bitcoinApiEndpoint) {
		this.bitcoinApiEndpoint = bitcoinApiEndpoint;
	}

	public String getBitcoinCurrencyEndpoint() {
		return bitcoinCurrencyEndpoint;
	}

	public void setBitcoinCurrencyEndpoint(String bitcoinCurrencyEndpoint) {
		this.bitcoinCurrencyEndpoint = bitcoinCurrencyEndpoint;
	}

}
