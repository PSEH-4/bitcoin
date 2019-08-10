package com.sapient.bitcoin.repository;

import java.util.List;

import com.sapient.bitcoin.bean.Currency;
import com.sapient.bitcoin.bean.Price;

public interface BitcoinPriceRepository {

	Currency getCurrency(String targetCurrency);

	List<Price> getPrice(String start, String end);

}
