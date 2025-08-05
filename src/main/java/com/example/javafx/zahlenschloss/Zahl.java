package com.example.javafx.zahlenschloss;

public enum Zahl {
	
	EINS("1"), ZWEI("2"), DREI("3"), VIER("4"), FUENF("5"), SECHS("6"), SIEBEN("7"), ACHT("8"), NEUN("9"), NULL("0");
	
	private String text;
	
	private Zahl(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}

}