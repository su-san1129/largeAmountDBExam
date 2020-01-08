package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Category;
import com.example.demo.service.ItemService;

@RestController
public class RestCategoryController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/childCategory")
	public List<Category> childCategory(@RequestBody Integer parent){
		return itemService.showCategory(parent);
	}

}
