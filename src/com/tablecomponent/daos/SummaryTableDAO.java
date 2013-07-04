package com.tablecomponent.daos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

import com.tablecomponent.beans.SummaryRow;
import com.tablecomponent.tables.SummaryTable;
import com.tablecomponent.transformers.HTMLtransformer;
import com.tablecomponent.utils.ColumnType;
import com.tablecomponent.utils.TableUtil;
import com.tablecomponent.utils.UnitsComparator;
import com.tablecomponent.utils.VendorComparator;

/**
 * Data Access Object class for Summary table. It is able to perform query execution
 * @author Petr
 *
 */
public class SummaryTableDAO 
{
	private SummaryTable summaryTable = null;
	private String queryCountry = null;
	private String queryQuartal = null;

	private String getQueryCountry() {
		return queryCountry;
	}

	private void setQueryCountry(String queryCountry) {
		this.queryCountry = queryCountry;
	}

	private String getQueryQuartal() {
		return queryQuartal;
	}

	private void setQueryQuartal(String queryQuartal) {
		this.queryQuartal = queryQuartal;
	}

	public SummaryTableDAO() 
	{
		summaryTable = new SummaryTable();
		summaryTable.setColumnNames(new String[] {"Vendor", "Units", "Share"});
	}

	/**
	 * Perform execution of query to select all vendors, units and share for selected 
	 * state and quartal. 
	 * @param country state where you are interested in
	 * @param quartal quartal which you are interested in
	 */
	public void executeQuery(String country, String quartal, QuartalShareDAO quartalShareDAO)
	{
//		QuartalShareDAO quartalShareDAO = TableUtil.getQuartalShareDAO();
		List<SummaryRow> results = quartalShareDAO.getSummaryRows(country, quartal);
		if (results.isEmpty())
		{
			summaryTable.setRows(null);
			summaryTable.setTotalRow(null);
			queryCountry = null;
			queryQuartal = null;
			return;
		}
		// round units and compute total units
		double tempTotalUnits = 0;
		Iterator iter = results.iterator();
		while (iter.hasNext())
		{
			SummaryRow summRow = (SummaryRow) iter.next();
			tempTotalUnits += summRow.getUnitsAsIntEquivalent();
		}
		summaryTable.setTotalRow( new SummaryRow("Total", tempTotalUnits, 1));
		// compute share in percentage for each row
		iter = results.iterator();
		while (iter.hasNext())
		{
			SummaryRow summRow = (SummaryRow) iter.next();
			summRow.setShare(summRow.getUnitsAsIntEquivalent() / summaryTable.getTotalRow().getUnitsAsIntEquivalent());
		}
		// assign result rows to summary table
		this.summaryTable.setRows(results);
		setQueryCountry(country);
		setQueryQuartal(quartal);
	}
	
	/**
	 * Perform execution of query to select given vendor's share of units in percentage for
	 * specified quartal and country 
	 * @param country given country where vendor sold units
	 * @param quartal given quartal where vendor sold units
	 * @param vendor given vendor which we interested in
	 * @return array with two items where first is amount of sold units and second is vendor's share 
	 */
	public String[] executeQuery(String country, String quartal, String vendor, QuartalShareDAO quartalShareDAO)
	{
		executeQuery(country, quartal, quartalShareDAO);
		String[] result = null;
		List<SummaryRow> sumRows = this.summaryTable.getRows();
		Iterator iter = sumRows.iterator();
		boolean found = false;
		while (iter.hasNext() && !found)
		{
			SummaryRow row = (SummaryRow) iter.next();
			if (row.getVendor().equalsIgnoreCase(vendor))
			{
				result = new String[2];
				result[0] = row.getUnitsAsString();
				result[1] = row.getShareAsString();
				found = true;
			}
		}
		return result;
		
	}
	
	/**
	 * Return number of row which contains information about given vendor.
	 * If the table is empty then method returns non existent vendor.
	 * @param vendor Given vendor which we are interested in
	 * @return String value with number of row that contains vendor's informations
	 */
	public String getRowNumber(String vendor)
	{
		String result = new String("Vendor " + vendor + " is not present in current table.");
		if (this.summaryTable.getRows() == null)
		{
			return result;
		}
		List<SummaryRow> rows = this.summaryTable.getRows();
		boolean found = false;
		Iterator iter = rows.iterator();
		int index = 0;
		while (iter.hasNext() && !found)
		{
			index++;
			SummaryRow row = (SummaryRow) iter.next();
			if (row.getVendor().equalsIgnoreCase(vendor))
			{
				found = true;
			}
		}
		if (found)
		{
			switch (index)
			{
			case 1:
				result = new String("the first row");
				break;
			case 2:
				result = new String("the second row");
				break;
			case 3:
				result = new String("the third row");
				break;
			case 4:
				result = new String("the fourth row");
				break;
			case 5:
				result = new String("the fifth row");
				break;
			case 6:
				result = new String("the sixth row");
				break;
			case 7:
				result = new String("the seventh row");
				break;
			default:
				result = new String("the " + index + "th row");
			}
		}
		return result;
			
	}
	
	/**
	 * Sort existing table by given Column type.
	 * @param type Column type e.g. VENDOR, UNITS 
	 */
	public void sortTableBy(ColumnType type)
	{
		if (this.summaryTable.getRows() == null)
		{
			return;
		}
		List<SummaryRow> rows = this.summaryTable.getRows();
		switch (type)
		{
			case VENDOR:
				Collections.sort(rows, new VendorComparator());
				break;
			case UNITS:
				Collections.sort(rows, new UnitsComparator());
				break;
			default:
				break;
		}
	}

	/**
	 * Export current table to HTML format with given filename.
	 * @param fileName Filename for destination html file
	 */
	public void exportToHTML(String fileName)
	{
		if (this.summaryTable.getRows() == null)
		{
			return;
		}
			
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		HTMLtransformer transformer = new HTMLtransformer();
		out.println(transformer.transformHtmlBodyBegin());
		out.println(transformer.transformTableBegin());
		out.println(transformer.transformTableCaption(getQueryCountry(), getQueryQuartal()));
		out.println(transformer.transformTableHeader(summaryTable.getColumnNames()));
		List<SummaryRow> rows = summaryTable.getRows();
		Iterator iter = rows.iterator();
		while (iter.hasNext())
		{
			SummaryRow row = (SummaryRow) iter.next();
			out.println(transformer.transformTableRow(row.getValues()));
		}
		out.println(transformer.transformTableFooter(summaryTable.getTotalRow().getValues()));
		out.println(transformer.transformTableEnd());
		out.println(transformer.transformHtmlBodyEnd());
		out.close();
	}
	
	/**
	 * Export current table to CSV file with given filename
	 * @param fileName Filename for destination CSV file
	 * @throws IOException 
	 */
	public void exportToCSV(String fileName) throws IOException
	{
		if (summaryTable.getRows() == null)
		{
			return;
		}
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new BufferedWriter(new FileWriter(fileName)), ';', CSVWriter.NO_QUOTE_CHARACTER);
			writer.writeNext(summaryTable.getColumnNames());
			List<SummaryRow> rows = summaryTable.getRows();
			Iterator iter = rows.iterator();
			while (iter.hasNext())
			{
				SummaryRow row = (SummaryRow) iter.next();
				writer.writeNext(row.getValues());
			}
			writer.writeNext(summaryTable.getTableFooter());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		} 
		finally
		{
			writer.close();
		}
		
		
	}
	
	/**
	 * Print current table to standard output
	 */
	public void printTable()
	{
		if (this.summaryTable == null)
		{
			System.out.println("Table is empty");
			return;
		}
		this.summaryTable.printTable();
	}

}
