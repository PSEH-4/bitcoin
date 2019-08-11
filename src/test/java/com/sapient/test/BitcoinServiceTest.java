package com.sapient.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sapient.bitcoin.bean.Currency;
import com.sapient.bitcoin.bean.Price;
import com.sapient.bitcoin.bean.Request;
import com.sapient.bitcoin.bean.Response;
import com.sapient.bitcoin.repository.BitcoinRepositoryRestImpl;
import com.sapient.bitcoin.service.BitcoinPriceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BitcoinServiceTest {

	@Mock
	private BitcoinRepositoryRestImpl bitcoinRepositoryRestImpl;

	@InjectMocks
	private BitcoinPriceServiceImpl priceServiceImpl;

	@Test
	public void testWhenPrice() throws Exception {

		Request request = new Request();
		request.setStartDate("2019-08-01");
		request.setEndDate("2019-08-10");
		request.setCurrency("INR");
		List<Price> prices = new ArrayList<>();
		prices.add(new Price("2019-08-01", 10));
		prices.add(new Price("2019-08-01", 20));
		Response response = new Response();
		response.setPrices(prices);
		Currency currency = new Currency(1, 70);

		Mockito.when(bitcoinRepositoryRestImpl.getCurrency(Mockito.anyString())).thenReturn(currency);
		Mockito.when(bitcoinRepositoryRestImpl.getPrice(Mockito.anyString(), Mockito.anyString())).thenReturn(prices);

		Response returned = priceServiceImpl.fetchPrice(request);
		Assert.assertEquals("700.0", returned.getPrices().get(0).getPrice());

	}

}
