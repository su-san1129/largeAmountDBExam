package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.domain.fizzySearchCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Item> itemList() {
		return itemRepository.findAll();
	}

	public Integer pageList(Integer viewSize) {
		Integer count = itemRepository.itemCount();
		return (count / viewSize) + 1;
	}

	public List<Item> pagingItemList(Integer viewSize, Integer pageIndex) {
		if (pageIndex * viewSize - (viewSize + 1) < 0) {
			pageIndex = 0;
		} else {
			pageIndex = pageIndex * viewSize - (viewSize + 1);
		}
		List<Item> itemList = itemRepository.findByPage(viewSize, pageIndex);
		return itemList;
	}

	/**
	 * 同親IDを持つカテゴリーを取得.
	 * 
	 * @param parent 親ID
	 * @return 同じ親IDをもつカテゴリー全部
	 */
	public List<Category> showCategory(Integer parent) {
		return categoryRepository.findByParent(parent);
	}

	/**
	 * 大カテゴリー全件.
	 * 
	 * @return 大カテゴリー.
	 */
	public List<Category> showParentCategory() {
		return categoryRepository.parentCategoryAll();
	}

	public Item showDetail(Integer id) {
		return itemRepository.load(id);
	}

	public List<Item> fizzySearchItems(fizzySearchCategory category) {
		if (category.getParent() == 0) {
			return itemRepository.findByNameAndBrand(category.getName(), category.getBrand());
		} else if (category.getChildCategory() == 0) {
			return itemRepository.findByNameAndParentCategoryAndBrand(category.getName(), category.getParent(), category.getBrand());
		} else if (category.getGrandChildCategory() == 0) {
			return itemRepository.findByNameAndChildCategoryAndBrand(category.getName(), category.getChildCategory(), category.getBrand());
		} else {
			return itemRepository.findByNameAndCategoryAndBrand(category.getName(), category.getGrandChildCategory(), category.getBrand());
		}
	}

}
