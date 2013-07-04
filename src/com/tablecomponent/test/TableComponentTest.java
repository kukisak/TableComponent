package com.tablecomponent.test;

import com.tablecomponent.TableComponent;
import com.tablecomponent.utils.ColumnType;

public class TableComponentTest {

	public void testReadCSVTable()
	{
		TableComponent comp = new TableComponent();
		comp.readCSVfile("data.csv", ',');
		comp.printCSVTable();
	}
	
	public void testSummaryTableQuery()
	{
		TableComponent comp = new TableComponent("data.csv", ',');
		comp.executeQuery("Czech Republic", "2010 Q3");
		comp.printSummaryTable();
	}
	
	public void testGetVendorShare()
	{
		TableComponent comp = new TableComponent("data.csv", ',');
		String country = "Czech Republic";
		String quartal = "2010 Q3";
		String vendor = "Dell";
		String[] result = comp.executeQuery(country, quartal, vendor);
		System.out.println("Vendor " + vendor + " sold " + result[0] + " units which is " + result[1] + 
				" for quarter " + quartal + " in country " + country);
	}
	
	public void testGetVendorRowNumber()
	{
		TableComponent comp = new TableComponent("data.csv", ',');
		comp.executeQuery("Czech Republic", "2010 Q3");
		String vendor = "apple";
		String result = comp.vendorRowNumber(vendor);
		System.out.println("Informations about vendor " + vendor + " are at row:");
		System.out.println(result);
		
	}
	
	public void testSortBy()
	{
		TableComponent comp = new TableComponent("data.csv", ',');
		comp.executeQuery("Czech Republic", "2010 Q3");

		System.out.println("\nBefore sort");
		comp.printSummaryTable();
		System.out.println("\nAfter sort by Vendor");
		comp.sortBy(ColumnType.VENDOR);
		comp.printSummaryTable();
		System.out.println("\nAfter sort by Units");
		comp.sortBy(ColumnType.UNITS);
		comp.printSummaryTable();
	}
	
	public void testExportToHtml()
	{
		TableComponent comp = new TableComponent("data.csv", ',');
		comp.executeQuery("Czech Republic", "2010 Q3");
		comp.exportToHtml("table1.html");
		
		comp.executeQuery("Slovakia", "2010 Q4");
		comp.exportToHtml("table2.html");
	}
	
	public void testExportToHtmlSorted()
	{
		TableComponent comp = new TableComponent("data.csv", ',');
		comp.executeQuery("Czech Republic", "2010 Q3");
		comp.sortBy(ColumnType.VENDOR);
		comp.exportToHtml("table1sorted.html");
	}
	
	public void testExportToCSV()
	{
		TableComponent comp = new TableComponent("data.csv", ',');
		comp.executeQuery("Czech Republic", "2010 Q3");
		comp.sortBy(ColumnType.VENDOR);
		comp.exportToCSV("table1.csv");
	}
}
