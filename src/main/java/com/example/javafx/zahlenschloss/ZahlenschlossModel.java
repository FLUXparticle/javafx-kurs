package com.example.javafx.zahlenschloss;

public class ZahlenschlossModel {
	
	private static final String CODE = "735";

	private StringBuilder eingabe = new StringBuilder();

	public LockState play(Zahl zahl) {
		eingabe.append(zahl);
		System.out.println(eingabe);
		if (eingabe.length() < CODE.length()) {
			return LockState.WAITING;
		} else if (eingabe.toString().equals(CODE)) {
			eingabe = new StringBuilder();
			return LockState.OPEN;
		} else {
			eingabe = new StringBuilder();
			return LockState.CLOSED;
		}
	}

}
