package com.sapient.bitcoin.service;

import com.sapient.bitcoin.bean.Response;
import com.sapient.bitcoin.repository.BitcoinApiException;

public interface BitcoinPriceService {

	Response fetchPrice(String start, String end, String targetCurrency);

}
