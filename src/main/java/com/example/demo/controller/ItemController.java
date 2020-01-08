package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.domain.fizzySearchCategory;
import com.example.demo.form.CategoryForm;
import com.example.demo.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@ModelAttribute
	public CategoryForm setUpCategoryForm() {
		return new CategoryForm();
	}

	@RequestMapping("")
	public String index(Model model, Integer viewSize, Integer page) {
		if (viewSize == null)
			viewSize = 30;
		if (page == null || page == 0)
			page = 1;
		Integer maxPage = itemService.pageList(viewSize);
		model.addAttribute("maxPage", maxPage);
		List<Item> itemList = itemService.pagingItemList(viewSize, page);
		model.addAttribute("itemList", itemList);
		model.addAttribute("page", page);
		model.addAttribute("parentCategories", itemService.showParentCategory());
		return "list";
	}

	@RequestMapping("/itemSearch")
	public String fizzySearch(Model model, CategoryForm form) {

		fizzySearchCategory category = setCategory(form);

		model.addAttribute("fizzySearchCategory", category);
		model.addAttribute("itemList", itemService.fizzySearchItems(category));
		model.addAttribute("parentCategories", itemService.showParentCategory());
		if (category.getParent() != 0)
			model.addAttribute("childCategories", itemService.showCategory(form.getParseIndparent()));
		if (category.getChildCategory() != 0)
			model.addAttribute("grandChildCategories", itemService.showCategory(form.getParseIntChildCategoryId()));
		return "list";
	}

	@RequestMapping("/category")
	public String searchCategory(Model model, int on, Integer id) {
		model.addAttribute("itemList", itemService.searchParentCategory(on, id));
		model.addAttribute("parentCategories", itemService.showParentCategory());
		return "list";
	}

	private fizzySearchCategory setCategory(CategoryForm form) {

		fizzySearchCategory category = new fizzySearchCategory();

		category.setBrand(form.getBrand());
		category.setName(form.getName());

		if (form.getParent().length() < 5)
			category.setParent(form.getParseIndparent());
		if (form.getChildCategory().length() < 5)
			category.setChildCategory(form.getParseIntChildCategoryId());
		if (form.getGrandChildCategory().length() < 5)
			category.setGrandChildCategory(form.getParseIntgrandChildCategoryId());
		return category;
	}

	@RequestMapping("/detail")
	public String showDetail(Integer id, Model model) {
		model.addAttribute("item", itemService.showDetail(id));
		return "detail";
	}

}
