package com.example.javafx.northwind.database;

import java.sql.*;
import java.util.*;

public class Database {
	
	private static Database INSTANCE; // = null;
	
	// Es wird erst bei Bedarf eine Instanz erzeugt
	public static Database getInstance() throws SQLException {
		if (INSTANCE == null) {
			INSTANCE = new Database();
		}
		return INSTANCE;
	}
	
	// Verbindung zur Datenbank
	private final Connection conn;
	
	// Konstruktor ist private, damit kein Anderer Instanzen von dieser Klasser erzeugen kann
	private Database() throws SQLException {
		// URL mit allen Informationen zum Verbinden zusammenbauen
		String url = "jdbc:sqlite::resource:northwind.db";
		System.out.println(url);
		// Verbindung herstellen
		conn = DriverManager.getConnection(url);
	}
	
	public Collection<String> getProductNames() throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT ProductName FROM Products");
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<String> productNames = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String name = rs.getString("ProductName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			productNames.add(name);
		}
		
		return productNames;
	}

	public Collection<String> getProductNamesLike(String text) throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT ProductName FROM Products WHERE ProductName LIKE ?");
		
		ps.setString(1, "%" + text + "%");
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<String> productNames = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String name = rs.getString("ProductName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			productNames.add(name);
		}
		
		return productNames;
	}

	public Collection<String> getCustomerNames() throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT CompanyName FROM Customers");
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<String> customerNames = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String name = rs.getString("CompanyName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			customerNames.add(name);
		}
		
		return customerNames;
	}

	public String getTofuSupplier() throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT CompanyName FROM Suppliers JOIN Products USING (SupplierID) WHERE ProductName = 'Tofu'");
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Zeile für Zeile über das Ergebnis iterieren
		if (rs.next()) {
			// Name auslesen
			String name = rs.getString("CompanyName");
			// Name als Ergebnis zurück geben
			return name;
		}
		
		return null;
	}

	public String getSupplier(String product) throws SQLException {
		// Statement vorbereiten (anfüllig für SQL-Injection)
		// PreparedStatement ps = conn.prepareStatement("SELECT CompanyName FROM Suppliers JOIN Products USING (SupplierID) WHERE ProductName = '" + product + "'");
		
		// Statement mit Platzhalter vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT CompanyName FROM Suppliers JOIN Products USING (SupplierID) WHERE ProductName = ?");
		
		// 1. Platzhalter auf sichere Weise mit String ersetzen (wird automatisch in Hochkommata gesetzt)
		ps.setString(1, product);
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Zeile für Zeile über das Ergebnis iterieren
		if (rs.next()) {
			// Name auslesen
			String name = rs.getString("CompanyName");
			// Name als Ergebnis zurück geben
			return name;
		}
		
		return null;
	}

	public Collection<String> getSeafoodProductNames() throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT ProductName FROM Products JOIN Categories USING (CategoryID) WHERE CategoryName = 'SeaFood'");
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<String> productNames = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String name = rs.getString("ProductName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			productNames.add(name);
		}
		
		return productNames;
	}

	public Collection<String> getExoticLiquids() throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT ProductName FROM Products JOIN Suppliers USING (SupplierID) WHERE CompanyName = 'Exotic Liquids'");
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<String> productNames = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String name = rs.getString("ProductName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			productNames.add(name);
		}
		
		return productNames;
	}

	public Collection<String> getKingsCustomers() throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT CompanyName FROM Customers JOIN Orders USING (CustomerID) JOIN Employees USING (EmployeeID) WHERE LastName = 'King' ORDER BY CompanyName");
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<String> customerNames = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String name = rs.getString("CompanyName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			customerNames.add(name);
		}
		
		return customerNames;
	}
	
	public Collection<String> getCategories() throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT CategoryName FROM Categories");

		// Statement ausführen
		ResultSet rs = ps.executeQuery();

		// Collection für Antwort erzeugen
		Collection<String> categories = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String name = rs.getString("CategoryName");
			// den Kategorie-Name aus dieser Zeile in Collection speichern
			categories.add(name);
		}
		
		return categories;
	}

	public Collection<String> getProductsInCategory(String category) throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement("SELECT ProductName FROM Products JOIN Categories USING (CategoryID) WHERE CategoryName = ?");
		
		ps.setString(1, category);
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<String> productNames = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String name = rs.getString("ProductName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			productNames.add(name);
		}
		
		return productNames;
	}

	public int countProductsInCategory(String category) throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement(
				"SELECT COUNT(*) AS Anzahl " +
				"FROM Products " +
				"JOIN Categories USING (CategoryID) " +
				"WHERE CategoryName = ?");
		
		ps.setString(1, category);
//		ps.setInt(1, id);
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Zeile für Zeile über das Ergebnis iterieren
		if (rs.next()) {
			// Namen auslesen
			int anzahl = rs.getInt("Anzahl");
			return anzahl;
		}
		
		return -1;
	}

	public Collection<Employee> getEmployees() throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement(
				"SELECT EmployeeID, LastName, FirstName FROM Employees");
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<Employee> employees = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			int id = rs.getInt("EmployeeID");
			String firstname = rs.getString("FirstName");
			String lastname = rs.getString("LastName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			employees.add(new Employee(id, firstname, lastname));
		}
		
		return employees;
	}

	public Collection<Employee> getEmployeesLike(String text) throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement(
				"SELECT EmployeeID, LastName, FirstName FROM Employees WHERE LastName LIKE ?");
		
		ps.setString(1, '%' + text + '%');
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<Employee> employees = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			int id = rs.getInt("EmployeeID");
			String firstname = rs.getString("FirstName");
			String lastname = rs.getString("LastName");
			// den Produktnamen aus dieser Zeile in Collection speichern
			employees.add(new Employee(id, firstname, lastname));
		}
		
		return employees;
	}

	public Collection<Product> getProductsLike(String text) throws SQLException {
		// Statement vorbereiten
		PreparedStatement ps = conn.prepareStatement(
				"SELECT ProductName, UnitPrice FROM Products WHERE ProductName LIKE ?");
		
		ps.setString(1, '%' + text + '%');
		
		// Statement ausführen
		ResultSet rs = ps.executeQuery();
		
		// Collection für Antwort erzeugen
		Collection<Product> products = new LinkedList<>();
		
		// Zeile für Zeile über das Ergebnis iterieren
		while (rs.next()) {
			// Namen auslesen
			String productName = rs.getString("ProductName");
			double price = rs.getDouble("UnitPrice");
			// das Produkt aus dieser Zeile in Collection speichern
			products.add(new Product(productName, price));
		}
		
		return products;
	}

	public static void main(String[] args) throws SQLException {
		Database database = new Database();

		Collection<String> categories = database.getCategories();
		System.out.println("categories = " + categories);

		Collection<Product> products = database.getProductsLike("Tofu");
		System.out.println("products = " + products);
	}

}
