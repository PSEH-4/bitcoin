package com.sapient.bitcoin.service;

import com.sapient.bitcoin.bean.Request;
import com.sapient.bitcoin.bean.Response;

public interface BitcoinPriceService {

	Response fetchPrice(Request request);

}
