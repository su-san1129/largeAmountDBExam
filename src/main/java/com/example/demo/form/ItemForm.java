package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemForm {
	
	private Integer id;
	@NotBlank(message="名前を入力してください。")
	private String name;
	@NotNull(message="コンディションを設定してください")
	private Integer condition;
	@NotNull(message="カテゴリーを選択してください")
	private String categoryId;
	@NotBlank(message="ブランド名を入力してください")
	private String brand;
	@NotNull(message="金額を入力してください")
	private Double price;
	private Integer shipping;
	@NotBlank(message="紹介文を入力してください")
	private String description;
	
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
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
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
	public ItemForm() {
		super();
	}
	public ItemForm(Integer id, String name, Integer condition, String categoryId, String brand, Double price,
			Integer shipping, String description) {
		super();
		this.id = id;
		this.name = name;
		this.condition = condition;
		this.categoryId = categoryId;
		this.brand = brand;
		this.price = price;
		this.shipping = shipping;
		this.description = description;
	}
	@Override
	public String toString() {
		return "ItemForm [id=" + id + ", name=" + name + ", condition=" + condition + ", categoryId=" + categoryId
				+ ", brand=" + brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description
				+ ", category=" + "]";
	}
	
	
	

}
