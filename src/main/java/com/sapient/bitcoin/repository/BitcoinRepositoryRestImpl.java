package com.sapient.bitcoin.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.bitcoin.Configuration;
import com.sapient.bitcoin.bean.Currency;
import com.sapient.bitcoin.bean.Price;

@Component
public class BitcoinRepositoryRestImpl implements BitcoinPriceRepository {

	Logger logger = LoggerFactory.getLogger(BitcoinRepositoryRestImpl.class);

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private Configuration configuration = new Configuration();

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();;

	@Override
	public Currency getCurrency(String targetCurrency) {
		logger.debug("Fetching currency data for :{}", targetCurrency);
		Currency currency = null;
		try {
			String content = content(configuration.getBitcoinCurrencyEndpoint(), targetCurrency);
			JsonNode node = OBJECT_MAPPER.readTree(content);
			JsonNode bpi = node.get("bpi");
			double usd = bpi.get("USD").get("rate_float").asDouble();
			double target = bpi.get(targetCurrency.toUpperCase()).get("rate_float").asDouble();
			currency = new Currency(usd, target);
		} catch (BitcoinApiException e) {
			logger.error("Error while fetching price , caused by : {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("Error while fetching price , caused by : {}", e.getMessage());
			throw new BitcoinApiException(e.getMessage());
		}
		return currency;

	}

	@Override
	public List<Price> getPrice(String start, String end) {
		logger.debug("Fetching Price for date range :{}-{}", start, end);
		List<Price> prices = new ArrayList<>();
		try {
			String content = content(configuration.getBitcoinApiEndpoint(), start, end);
			JsonNode node = new ObjectMapper().readTree(content);
			JsonNode bpi = node.get("bpi");
			bpi.fields().forEachRemaining(e -> {
				prices.add(new Price(e.getKey(), e.getValue().asDouble()));
			});
		} catch (BitcoinApiException e) {
			logger.error("Error while fetching price , caused by : {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("Error while fetching price , caused by : {}", e.getMessage());
			throw new BitcoinApiException(e.getMessage());
		}
		return prices;

	}

	public String content(String url, Object... param) throws BitcoinApiException {
		String content = "";
		try {
			ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, param);
			if (entity.getStatusCodeValue() == 200) {
				content = entity.getBody();
			}

		} catch (Exception e) {
			throw new BitcoinApiException("Unable to get resonse");
		}
		return content;
	}

}
