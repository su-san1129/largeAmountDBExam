package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.domain.fizzySearchCategory;
import com.example.demo.form.CategoryForm;
import com.example.demo.form.ItemForm;
import com.example.demo.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;
	
	private final Integer VIEW_SIZE = 30;

	@ModelAttribute
	public CategoryForm setUpCategoryForm() {
		return new CategoryForm();
	}

	@ModelAttribute
	public ItemForm setUpItemForm() {
		return new ItemForm();
	}

	@RequestMapping("")
	public String index(Model model, Integer page) {
		if (page == null || page == 0)
			page = 1;
		Integer maxPage = itemService.pageList(VIEW_SIZE);
		model.addAttribute("pageStatus", 1);
		model.addAttribute("maxPage", maxPage);
		List<Item> itemList = itemService.pagingItemList(VIEW_SIZE, page);
		model.addAttribute("itemList", itemList);
		model.addAttribute("page", page);
		model.addAttribute("parentCategories", itemService.showParentCategory());
		return "list";
	}

	@RequestMapping("/itemSearch")
	public String fizzySearch(Model model, CategoryForm form,Integer page) {
		if (page == null || page == 0)
			page = 1;
		Integer maxPage = itemService.pageList(VIEW_SIZE);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("page", page);
		fizzySearchCategory category = setCategory(form);

		model.addAttribute("pageStatus", 2);
		model.addAttribute("fizzySearchCategory", category);
		model.addAttribute("itemList", itemService.fizzySearchItems(category, VIEW_SIZE, page));
		model.addAttribute("parentCategories", itemService.showParentCategory());
		if (category.getParent() != 0)
			model.addAttribute("childCategories", itemService.showCategory(form.getParseIndparent()));
		if (category.getChildCategory() != 0)
			model.addAttribute("grandChildCategories", itemService.showCategory(form.getParseIntChildCategoryId()));
		return "list";
	}

	@RequestMapping("/category")
	public String searchCategory(Model model, int on, Integer id, Integer page) {
		if (page == null || page == 0)
			page = 1;
		Integer maxPage = itemService.pageList(VIEW_SIZE);
		model.addAttribute("on", on);
		model.addAttribute("pageStatus", 3);
		model.addAttribute("id", id);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("page", page);
		model.addAttribute("itemList", itemService.searchParentCategory(on, id, VIEW_SIZE, page));
		model.addAttribute("parentCategories", itemService.showParentCategory());
		return "list";
	}
	
	@RequestMapping("/add")
	public String toAdd(Model model) {
		model.addAttribute("parentCategories", itemService.showParentCategory());
		return "add";
	}
	
	@RequestMapping("/addItem")
	public String addItem(@Validated ItemForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return toAdd(model);
		}
		itemService.itemSave(form);
		return "redirect:/";
	}

	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		getRefererUrl();
		int conditionCount[] = { 1, 2, 3 };
		Item item = itemService.showDetail(id);
		model.addAttribute("item", item);
		model.addAttribute("parentCategories", itemService.showParentCategory());
		if (item.getCategoryId() != 0 && item.getCategory().getParent() != 0) {
			model.addAttribute("childCategories",
					itemService.showCategory(item.getCategory().getParentCategory().getParent()));
		}
		if (item.getCategoryId() != 0) {
			model.addAttribute("grandChildCategories", itemService.showCategory(item.getCategory().getParent()));
		}
		model.addAttribute("conditionCount", conditionCount);
		return "edit";
	}

	@RequestMapping("update")
	public String update(ItemForm form, Model model) {
		itemService.itemSave(form);
		return "redirect:" + (String) session.getAttribute("editReferer");

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

	private void getRefererUrl() {
		String url = request.getHeader("referer");
		if (session.getAttribute("referer") != null) {
			String referer = (String) session.getAttribute("referer");
			Integer isRefererIndex = url.indexOf("detail");
			if (!url.equals(referer) && isRefererIndex == -1 && url.indexOf("edit") == -1) {
				session.setAttribute("referer", url);
			} else if (isRefererIndex != -1) {
				session.setAttribute("editReferer", url);
				System.out.println(session.getAttribute("editReferer"));
			}
		} else {
			session.setAttribute("referer", url);
		}
	}

	@RequestMapping("/detail")
	public String showDetail(Integer id, Model model) {
		getRefererUrl();
		model.addAttribute("item", itemService.showDetail(id));
		return "detail";
	}

}
