package com.example.javafx.pferderennen;

import com.example.javafx.pferderennen.model.*;

import java.util.*;

public class Pferderennen {

	public static void main(String[] args) {

		Pferd[] pferde = {
				new Pferd("Joe Law"),
				new Pferd("L. B. J."),
				new Pferd("Herr Washburn"),
				new Pferd("Frl. Karen"),
				new Pferd("Fröhlich"),
				new Pferd("Pferd Nr. 6"),
				new Pferd("Versteife nicht"),
				new Pferd("Mitternacht")
		};

		int summeStaerken = 0;
		for (int i = 0; i < pferde.length; i++) {
			summeStaerken += pferde[i].getStaerke();
		}
		
		for (int i = 0; i < pferde.length; i++) {
			pferde[i].berechneQuote(summeStaerken);
		}
		
		for (int i = 0; i < pferde.length; i++) {
			System.out.println(pferde[i]);
		}
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Los?");
		sc.nextLine();
		
		int gewinner = -1;
		while (gewinner < 0) {
			
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i < pferde.length; i++) {
				pferde[i].lauf();
			}
			
			for (int i = 0; i < pferde.length; i++) {
				if (pferde[i].getMeter() >= 1000) {
					gewinner = i;
				}
			}
			
			for (int i = 0; i < pferde.length; i++) {
				System.out.printf("%20s ", pferde[i].getName());
				for (int m = 0; m < pferde[i].getMeter() / 20; m++) {
					System.out.print('.');
				}
				System.out.println();
			}

		}
		
		System.out.println("Gewinner:\n" + pferde[gewinner]);

	}

}
