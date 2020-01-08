package com.example.demo.domain;

public class fizzySearchCategory {
	
	private String name;
	private Integer parent;
	private Integer childCategory;
	private Integer grandChildCategory;
	private String brand;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public Integer getChildCategory() {
		return childCategory;
	}
	public void setChildCategory(Integer childCategory) {
		this.childCategory = childCategory;
	}
	public Integer getGrandChildCategory() {
		return grandChildCategory;
	}
	public void setGrandChildCategory(Integer grandChildCategory) {
		this.grandChildCategory = grandChildCategory;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public fizzySearchCategory() {
		super();
		this.parent=0;
		this.childCategory=0;
		this.grandChildCategory=0;
		// TODO Auto-generated constructor stub
	}
	public fizzySearchCategory(String name, Integer parent, Integer childCategory, Integer grandChildCategory,
			String brand) {
		super();
		this.name = name;
		this.parent = parent;
		this.childCategory = childCategory;
		this.grandChildCategory = grandChildCategory;
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "fizzySearchCategory [name=" + name + ", parent=" + parent + ", childCategory=" + childCategory
				+ ", grandChildCategory=" + grandChildCategory + ", brand=" + brand + "]";
	}
	
	

}
