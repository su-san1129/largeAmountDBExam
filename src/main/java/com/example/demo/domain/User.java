package com.example.demo.domain;

public class User {
	
	private Integer id;
	private String name;
	private String password;
	private String registerDate;  // 仮
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, String password, String registerDate) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.registerDate = registerDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", registerDate=" + registerDate + "]";
	}
	
	

}
