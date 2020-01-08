package com.example.demo.form;

public class CategoryForm {

	private String name;
	private String parent;
	private String childCategory;
	private String grandChildCategory;
	private String brand;
	
	public Integer getParseIndparent() {
		return Integer.parseInt(this.parent);
	}
	
	public Integer getParseIntChildCategoryId() {
		return Integer.parseInt(this.childCategory);
	}
	public Integer getParseIntgrandChildCategoryId() {
		return Integer.parseInt(this.grandChildCategory);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}

	public String getGrandChildCategory() {
		return grandChildCategory;
	}
	

	public void setGrandChildCategory(String grandChildCategory) {
		this.grandChildCategory = grandChildCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public CategoryForm(String name, String parent, String childCategory, String grandChildCategory, String brand) {
		super();
		this.name = name;
		this.parent = parent;
		this.childCategory = childCategory;
		this.grandChildCategory = grandChildCategory;
		this.brand = brand;
	}
	
	public CategoryForm() {
		super();
	}

	@Override
	public String toString() {
		return "CategoryForm [name=" + name + ", parent=" + parent + ", childCategory=" + childCategory
				+ ", grandChildCategory=" + grandChildCategory + ", brand=" + brand + "]";
	}
	
	

}
