package com.sapient.bitcoin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.bitcoin.bean.Currency;
import com.sapient.bitcoin.bean.Price;
import com.sapient.bitcoin.bean.Request;
import com.sapient.bitcoin.bean.Response;
import com.sapient.bitcoin.exception.BitcoinApiException;
import com.sapient.bitcoin.repository.BitcoinPriceRepository;

@Service
public class BitcoinPriceServiceImpl implements BitcoinPriceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BitcoinPriceServiceImpl.class);

	@Autowired
	private BitcoinPriceRepository bitcoinPriceRepository;

	@Override
	public Response fetchPrice(Request request) {
		LOGGER.debug("Fetching currency data for :{}", request);
		Currency currency = null;
		List<Price> prices = null;
		
		String targetCurrency = request.getCurrency();
		String start = request.getStartDate();
		String end = request.getEndDate();
		try {
			currency = bitcoinPriceRepository.getCurrency(targetCurrency);
			prices = bitcoinPriceRepository.getPrice(start, end);
		} catch (BitcoinApiException e) {
			LOGGER.error("Error while fetching price , caused by : {}", e.getMessage());
			throw e;
		}
		LOGGER.debug("converting price by currency conversion rate");
		doConversion(currency, prices, targetCurrency);
		Response response = new Response(targetCurrency,start,end,prices);
		return response;
	}

	private void doConversion(Currency currency, List<Price> prices, String targetCurrency) {
		double conversionRate = currency.getTarget() / currency.getUsd();
		LOGGER.debug("conversion rate for currency :{} is {}",targetCurrency,conversionRate);
		int highestIndex = 0;
		double highest = 0;
		int index = 0;
		for (Price p : prices) {
			double price = Double.parseDouble(p.getPrice().toString()) * conversionRate;
			p.setPrice(Double.toString(price));
			if (price > highest) {
				highestIndex = index;
				highest = price;
			}
			index++;
		}
		Price highestPrice = prices.get(highestIndex);
		highestPrice.setPrice(highestPrice.getPrice() + " (highest)");
		prices.set(highestIndex, highestPrice);
	}

}
