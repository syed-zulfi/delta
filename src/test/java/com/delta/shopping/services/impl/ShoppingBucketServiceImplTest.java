package com.delta.shopping.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.delta.shopping.model.Item;

@SpringBootTest
public class ShoppingBucketServiceImplTest {

	public static Map<String, Item> inventory = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	@InjectMocks
	private ShoppingBucketServiceImpl service;
	@Test
	void testBasketCalc() {
		List<String> input = new ArrayList<>();
		input.add("lime");
		input.add("melon");
		Map<String, Object> returnValue = service.createBucket(input);
		Assert.notNull(returnValue);
	
	}
	

	static {
		Item apple = new Item();
		apple.setItemName("Apple");
		apple.setCost(35.0);
		apple.setOffer(null);

		Item banana = new Item();
		banana.setItemName("Banana");
		banana.setCost(20.0);
		banana.setOffer(null);

		Item melon = new Item();
		melon.setItemName("Melon");
		melon.setCost(50.0);
		melon.setOffer("Buy1Get1");

		Item lime = new Item();
		lime.setItemName("Lime");
		lime.setCost(15.0);
		lime.setOffer("Buy2Get1");

		inventory.put("Apple", apple);
		inventory.put("Banana", banana);
		inventory.put("Melon", melon);
		inventory.put("Lime", lime);

	}

}
