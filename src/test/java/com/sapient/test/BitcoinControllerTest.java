///*package com.sapient.test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sapient.bitcoin.bean.Price;
//import com.sapient.bitcoin.bean.Response;
//import com.sapient.bitcoin.controller.BitcoinPriceController;
//import com.sapient.bitcoin.service.BitcoinPriceServiceImpl;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(BitcoinPriceController.class)
//public class BitcoinControllerTest {
//
//	@Autowired
//	private MockMvc mvc;
//
//	@MockBean
//	private BitcoinPriceServiceImpl bitcoinPriveServiceImpl;
//
//	@Test
//	public void testWhenGetCurrency() throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//		List<Price> prices = new ArrayList<>();
//		prices.add(new Price("2019-08-1", 10));
//		Response response = new Response();
//		response.setPrices(prices);
//
//		Mockito.when(
//				bitcoinPriveServiceImpl.fetchPrice(Mockito.anyString(),
//						Mockito.anyString(), Mockito.anyString())).thenReturn(
//				response);
//
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/bitcoin/a/b/c").accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//
//		System.out.println(result.getResponse());
//		String expected = mapper.writeValueAsString(response);
//
//		JSONAssert.assertEquals(expected, result.getResponse()
//				.getContentAsString(), false);
//
//	}
//}
*/