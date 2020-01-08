package com.example.demo.domain;

public class Item {
	
	private Integer id;
	private String name;
	private Integer condition;
	private Integer categoryId;
	private String brand;
	private Double price;
	private Integer shipping;
	private String description;
	private Category category;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getShipping() {
		return shipping;
	}
	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(Integer id, String name, Integer condition, Integer categoryId, String brand, Double price,
			Integer shipping, String description, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.condition = condition;
		this.categoryId = categoryId;
		this.brand = brand;
		this.price = price;
		this.shipping = shipping;
		this.description = description;
		this.category = category;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", condition=" + condition + ", categoryId=" + categoryId
				+ ", brand=" + brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description
				+ ", category=" + category + "]";
	}

	
	
	
	

}
