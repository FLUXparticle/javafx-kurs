package com.example.javafx.northwind.database;

public class Product {
	
	private String name;
	
	private double price;

	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Product{");
		sb.append("name='").append(name).append('\'');
		sb.append(", price=").append(price);
		sb.append('}');
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

}
