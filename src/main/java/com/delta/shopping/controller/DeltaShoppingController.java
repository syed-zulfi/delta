package com.delta.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delta.shopping.services.ShoppingBucketService;


@RestController
@CrossOrigin(origins = "http://localhost:8080/")
@RequestMapping("/delta")

public class DeltaShoppingController {

	@Autowired
	ShoppingBucketService bucketService;
	@PostMapping
	public ResponseEntity<?> createNewBucket(@RequestBody List<String> payload) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bucketService.createBucket(payload));
	}
}
