package com.delta.shopping.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delta.shopping.model.Item;
import com.delta.shopping.services.ShoppingBucketService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShoppingBucketServiceImpl implements ShoppingBucketService {

	public static Map<String, Item> inventory = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	@Override
	public Map<String,Object> createBucket(List<String> input) {
		Map<String, Long> inputMap = input.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		log.info("Input",input);
		List<Item> items = new ArrayList<>();
		for (String key : inputMap.keySet()) {
			Item item = inventory.get(key);
			if (item != null) {
				item.setQuantity(inputMap.get(key));
				items.add(item);
			}
		}

		Double basketAmount = 0.0;
		
		for (Item i:items) {
			System.out.println(i.getItemName());
			if (null != i.getOffer()) {
				if (i.getOffer().equals("Buy1Get1")) {
					if(i.getQuantity()>1) {
					Long quan = i.getQuantity()/2;
					quan = quan + i.getQuantity()%2;
					
					basketAmount = basketAmount + (i.getCost() * quan);
					} else {
						basketAmount = basketAmount + i.getCost() * i.getQuantity();
					}
				} else if (i.getOffer().equals("Buy2Get1")) {
					int x = (int) ((i.getQuantity()/3)*2);
				basketAmount = basketAmount + (i.getCost() * x);
					int rem = (int) (i.getQuantity()%3);
					if(rem>0) {
						basketAmount = basketAmount + i.getCost() * rem;
					}
				}
			} else {
				basketAmount = basketAmount + i.getCost() * i.getQuantity();
			}
			System.out.println(basketAmount);
		}
		
		Map<String,Object> returnObject = new HashMap<>();
		returnObject.put("Items", items);
		returnObject.put("BasketTotal", basketAmount);
		log.info("Basket amount",basketAmount);
		return returnObject;
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
