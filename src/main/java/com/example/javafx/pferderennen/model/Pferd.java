package com.example.javafx.pferderennen.model;

public class Pferd {
	
	private String name;
	
	private int staerke;
	
	private double quote;
	
	private int meter;
	
	public Pferd(String name) {
		this.name = name;
		
		staerke = (int)(Math.random() * 10 + 1);
		
		meter = 0;
	}
	
	public void berechneQuote(int summeStaerken) {
		quote = (double) summeStaerken / staerke;
	}
	
	public void lauf() {
		meter += (int) (Math.random() * 100 - quote) / 20 + 1;
	}
	
	public String toString() {
		// name + " Stärke: " + staerke + " Quote: " + quote;
		return String.format("%20s Stärke: %2d Quote: %5.2f", name, staerke, quote);
	}
	
	public String getName() {
		return name;
	}

	public int getStaerke() {
		return staerke;
	}
	
	public int getMeter() {
		return meter;
	}
	
}
