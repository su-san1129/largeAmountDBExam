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
		model.addAttribute("pageStatus", 1);
		model.addAttribute("maxPage", maxPage);
		List<Item> itemList = itemService.pagingItemList(viewSize, page);
		model.addAttribute("itemList", itemList);
		model.addAttribute("page", page);
		model.addAttribute("parentCategories", itemService.showParentCategory());
		return "list";
	}

	@RequestMapping("/itemSearch")
	public String fizzySearch(Model model, CategoryForm form, Integer viewSize, Integer page) {
		if (viewSize == null)
			viewSize = 30;
		if (page == null || page == 0)
			page = 1;
		Integer maxPage = itemService.pageList(viewSize);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("page", page);
		fizzySearchCategory category = setCategory(form);

		model.addAttribute("pageStatus", 2);
		model.addAttribute("fizzySearchCategory", category);
		model.addAttribute("itemList", itemService.fizzySearchItems(category, viewSize, page));
		model.addAttribute("parentCategories", itemService.showParentCategory());
		if (category.getParent() != 0)
			model.addAttribute("childCategories", itemService.showCategory(form.getParseIndparent()));
		if (category.getChildCategory() != 0)
			model.addAttribute("grandChildCategories", itemService.showCategory(form.getParseIntChildCategoryId()));
		return "list";
	}

	@RequestMapping("/category")
	public String searchCategory(Model model, int on, Integer id, Integer viewSize, Integer page) {
		if (viewSize == null)
			viewSize = 30;
		if (page == null || page == 0)
			page = 1;
		Integer maxPage = itemService.pageList(viewSize);
		model.addAttribute("on", on);
		model.addAttribute("pageStatus", 3);
		model.addAttribute("id", id);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("page", page);
		model.addAttribute("itemList", itemService.searchParentCategory(on, id, viewSize, page));
		model.addAttribute("parentCategories", itemService.showParentCategory());
		return "list";
	}
	
	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		int conditionCount[] = {1,2,3};
		Item item = itemService.showDetail(id);
		model.addAttribute("item",item);
		model.addAttribute("parentCategories", itemService.showParentCategory());
		if(item.getCategoryId() != 0 && item.getCategory().getParent() != 0) {
			model.addAttribute("childCategories", itemService.showCategory(item.getCategory().getParentCategory().getParent()));
		}
		if(item.getCategoryId() != 0) {
			model.addAttribute("grandChildCategories", itemService.showCategory(item.getCategory().getParent()));
		}
		model.addAttribute("conditionCount", conditionCount);
		return "edit";
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
