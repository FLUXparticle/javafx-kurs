package com.example.javafx.aktien.model;

import javafx.collections.*;

import java.util.*;

public class AktienGame {

	public static final String[] AKTIEN_NAMEN = new String[] { "IBM", "RCA", "LBJ", "ABC", "AEG" };

	private Random random = new Random();

	private double geld;

	private double marktRichtung;

	private double[] preise; // S

	private double[] diff; // C

	private int[] besitz; // P

	private double alterIndex;

	private int nextTrend;

	private int indexHoch;
	private int nextHoch;
	private boolean gehtHoch;

	private int indexRunter;
	private int nextRunter;
	private boolean gehtRunter;

	private int day;

	private final List<ObservableList<DataPoint>> priceHistory = new ArrayList<>();

	public AktienGame() {
		preise = new double[]{ 100, 85, 150, 140, 110 };
		// initialize price history lists
		for (double p : preise) {
			priceHistory.add(FXCollections.observableArrayList(new DataPoint(0, p)));
		}
		besitz = new int[preise.length];
		diff = new double[preise.length];
		nextHoch = 0;
		nextRunter = 0;
		neuerMarkt();
		nextDay();

		geld = 10000;
	}

	public void nextDay() {
		day++;
		alterIndex = getIndex();

		if (nextHoch <= 0) {
			indexHoch = random.nextInt(AKTIEN_NAMEN.length);
			nextHoch = random.nextInt(5) + 1;
			gehtHoch = true;
		}

		if (nextRunter <= 0) {
			indexRunter = random.nextInt(AKTIEN_NAMEN.length);
			nextRunter = random.nextInt(5) + 1;
			gehtRunter = true;
		}

		nextHoch--;
		nextRunter--;

		for (int i = 0; i < AKTIEN_NAMEN.length; i++) {
			double x1 = random.nextInt(4) / 4.0;
			int w3 = 0;
			if (gehtHoch && indexHoch == i) {
				w3 += 10;
				gehtHoch = false;
			}
			if (gehtRunter && indexRunter == i) {
				w3 -= 10;
				gehtRunter = false;
			}

			diff[i] = (int)(marktRichtung * preise[i]) + x1 + (int)(3 - random.nextDouble() * 6) + w3;
			preise[i] += diff[i];
			if (preise[i] < 0) {
				preise[i] = 0;
				diff[i] = 0;
			}
			ObservableList<DataPoint> list = priceHistory.get(i);
			DataPoint newDataPoint = new DataPoint(day, preise[i]);
//			if (list.size() < 10) {
//				list.add(newDataPoint);
//			} else {
//				ArrayList<DataPoint> copy = new ArrayList<>(list.subList(1, list.size()));
//				copy.add(newDataPoint);
//				list.setAll(copy);
//			}
			while (list.size() >= 10) {
				list.remove(0);
			}
			list.add(newDataPoint);
		}

		nextTrend--;
		if (nextTrend <= 0) {
			neuerMarkt();
		}

	}

	public void buy(int aktie, int anzahl) {
		double wert = preise[aktie] * anzahl * 1.01;
		geld -= wert;
		besitz[aktie] += anzahl;
	}

	public void sell(int aktie, int anzahl) {
		double wert = preise[aktie] * anzahl * 0.99;
		geld += wert;
		besitz[aktie] -= anzahl;
	}

	public int maxBuy(int aktie) {
		return (int) (geld / (preise[aktie] * 1.01));
	}

	private void neuerMarkt() {
		nextTrend = random.nextInt(5) + 1;
		marktRichtung = random.nextDouble() * 0.1;
		if (random.nextInt(2) == 0) {
			marktRichtung = -marktRichtung;
		}
	}

	public double getAlterIndex() {
		return alterIndex;
	}

	public double getIndex() {
		double index = 0.0;
		for (int i = 0; i < preise.length; i++) {
			index += preise[i];
		}
		index /= preise.length;
		return index;
	}

	public double getGeld() {
		return geld;
	}

	public double getWert(int i) {
		return preise[i];
	}

	public int getBesitz(int i) {
		return besitz[i];
	}

	public double getDiff(int i) {
		return diff[i];
	}

	public List<ObservableList<DataPoint>> getPriceHistory() {
		return priceHistory;
	}

}
