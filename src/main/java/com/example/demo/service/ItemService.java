package com.example.demo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.domain.fizzySearchCategory;
import com.example.demo.form.ItemForm;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Integer pageList(Integer viewSize) {
		Integer count = itemRepository.itemCount();
		return (count / viewSize) + 1;
	}

	public List<Item> pagingItemList(Integer viewSize, Integer pageIndex) {
		pageIndex = setPageIndex(viewSize, pageIndex);
		List<Item> itemList = itemRepository.findByPage(viewSize, pageIndex);
		return itemList;
	}
	
	private Integer setPageIndex(Integer viewSize, Integer pageIndex) {
		if (pageIndex * viewSize - (viewSize + 1) < 0) {
			pageIndex = 0;
		} else {
			pageIndex = pageIndex * viewSize - (viewSize + 1);
		}
		return pageIndex;
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
	
	public void itemSave(ItemForm form) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		if(form.getCategoryId().length() < 5) {
			item.setCategoryId(Integer.parseInt(form.getCategoryId()));
		} else {
			item.setCategoryId(0);
		}
		itemRepository.save(item);
	}

	public List<Item> fizzySearchItems(fizzySearchCategory category, Integer viewSize, Integer pageIndex) {
		pageIndex = setPageIndex(viewSize, pageIndex);
		if (category.getParent() == 0) { // 検索欄に、大カテゴリーが入っていない時
			return itemRepository.findByNameAndBrand(category.getName(), category.getBrand(), viewSize, pageIndex);
		} else if (category.getChildCategory() == 0) { // 検索欄
			return itemRepository.findByNameAndParentCategoryAndBrand(category.getName(), category.getParent(), category.getBrand(), viewSize, pageIndex);
		} else if (category.getGrandChildCategory() == 0) {
			return itemRepository.findByNameAndChildCategoryAndBrand(category.getName(), category.getChildCategory(), category.getBrand(), viewSize, pageIndex);
		} else {
			return itemRepository.findByNameAndCategoryAndBrand(category.getName(), category.getGrandChildCategory(), category.getBrand(), viewSize, pageIndex);
		}
	}
	
	public List<Item> searchParentCategory(int on, Integer id, Integer viewSize, Integer pageIndex){
		pageIndex = setPageIndex(viewSize, pageIndex);
		if(on == 1) {
			return itemRepository.findByNameAndParentCategoryAndBrand("", id, "", viewSize, pageIndex);
		} else if ( on == 2) {
			return itemRepository.findByNameAndChildCategoryAndBrand("", id, "", viewSize, pageIndex);
		} else if ( on == 3) {
			return itemRepository.findByNameAndCategoryAndBrand("", id, "", viewSize, pageIndex);
		} else {
			return null;
		}
	}

}
