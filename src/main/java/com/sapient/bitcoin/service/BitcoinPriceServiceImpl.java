package com.sapient.bitcoin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.bitcoin.bean.Currency;
import com.sapient.bitcoin.bean.Price;
import com.sapient.bitcoin.bean.Response;
import com.sapient.bitcoin.repository.BitcoinApiException;
import com.sapient.bitcoin.repository.BitcoinPriceRepository;
import com.sapient.bitcoin.repository.BitcoinRepositoryRestImpl;

@Service
public class BitcoinPriceServiceImpl implements BitcoinPriceService {
	
	Logger logger = LoggerFactory.getLogger(BitcoinPriceServiceImpl.class);

	@Autowired
	private BitcoinPriceRepository bitcoinPriceRepository;

	@Override
	public Response fetchPrice(String start, String end,String targetCurrency){
		logger.debug("Fetching currency data for startDate={},endDate={},currency={}",start,end,targetCurrency);
		Currency currency;
		List<Price> prices;
		int highestIndex =0;
		double highest =0;
		int index =0;
		try {
			currency = bitcoinPriceRepository.getCurrency(targetCurrency);
			prices = bitcoinPriceRepository.getPrice(start, end);
		} catch (BitcoinApiException e) {
			logger.error("Error while fetching price , caused by : {}",e.getMessage());
			throw e;
		}
		double conversionRate = currency.getTarget() / currency.getUsd();
		for(Price p:prices){
			double price = Double.parseDouble(p.getPrice().toString())*conversionRate;
			p.setPrice(price);
			if(price>highest){
				highestIndex=index;
				highest=price;
			}
			index++;
		}
		Price highestPrice = prices.get(highestIndex);
		highestPrice.setPrice(highestPrice.getPrice()+"(highest)");
		prices.set(highestIndex, highestPrice);
		Response response = new Response();
		response.setCurrency(targetCurrency);
		response.setStartDate(start);
		response.setEndDate(end);
		response.setPrices(prices);
		return response;
	}

}
