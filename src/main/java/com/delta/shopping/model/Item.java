package com.delta.shopping.model;

import lombok.Data;

@Data
public class Item {

	private String itemName;
	private Long quantity;
	private Double cost;
	private String offer;
}
