package com.example.demo.domain;


public class Category {
	
	private Integer id;
	private Integer parent;
	private String name;
	private String nameAll;
	// 親のカテゴリーのオブジェクト
	private Category parentCategory;
	// 子のカテゴリーオブジェクト
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameAll() {
		return nameAll;
	}
	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(Integer id, Integer parent, String name, String nameAll, Category parentCategory) {
		super();
		this.id = id;
		this.parent = parent;
		this.name = name;
		this.nameAll = nameAll;
		this.parentCategory = parentCategory;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", parent=" + parent + ", name=" + name + ", nameAll=" + nameAll
				+ ", parentCategory=" + parentCategory + ", childCategroy=" + "]";
	}
	
}
