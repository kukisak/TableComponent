package com.tablecomponent;

import java.io.IOException;

import com.tablecomponent.utils.ColumnType;
import com.tablecomponent.utils.Configuration;
import com.tablecomponent.utils.TableUtil;

/**
 * Class TableComponent is able to read CSV data, perform query operations on them, 
 * sorting query results and also export results into formats like HTML and CSV.
 * @author Petr
 *
 */
public class TableComponent 
{
	private TableUtil tableUtil = null;

	/**
	 * Constructor create new table component.
	 */
	public TableComponent() 
	{
		tableUtil = new TableUtil();
	}

	/**
	 * Constructor create new table component and read CSV file with given separator.
	 * @param csvFileName Given CSV filename
	 * @param csvSeparator Given CSV file separator
	 */
	public TableComponent(String csvFileName, char csvSeparator)
	{
		tableUtil = new TableUtil();
		readCSVfile(csvFileName, csvSeparator);
	}

	/**
	 * Read given CSV file with its separator.
	 * @param csvFileName Given CSV filename
	 * @param csvSeparator Given CSV file separator
	 */
	public void readCSVfile(String csvFileName, char csvSeparator)
	{
		tableUtil.getQuartalShareDAO(new Configuration(csvFileName, csvSeparator));
	}
	
	/**
	 * Execute query on CSV file with given criteria where Country = country and
	 * Quartal = quartal.
	 * @param country Given country
	 * @param quartal Given quartal
	 */
	public void executeQuery(String country, String quartal)
	{
		tableUtil.getSumaryTableDAO().
		executeQuery(country, quartal, tableUtil.getQuartalShareDAO());		
	}
	
	/**
	 * Execute query on CSV file with given criteria where Country = country, Quartal = quartal and
	 * Vendor = vendor. Result will contain given vendor's share of units in percentage for
	 * specified quartal and country. 
	 * @param country Given country where vendor sold units
	 * @param quartal Given quartal where vendor sold units
	 * @param vendor Given vendor
	 * @return Array with two items where first is amount of sold units and second is 
	 * vendor's share for given quartal and country  
	 */
	public String[] executeQuery(String country, String quartal, String vendor)
	{
		return tableUtil.getSumaryTableDAO().
				executeQuery(country, quartal, vendor, tableUtil.getQuartalShareDAO());
		
	}

	/**
	 * Returns row number for given vendor in result summary table. 
	 * @param vendor Given vendor
	 * @return String sentence with given vendor row number in result summary table. 
	 */
	public String vendorRowNumber(String vendor)
	{
		return tableUtil.getSumaryTableDAO().getRowNumber(vendor);
	}
	
	/**
	 * Sort current result summary table by given column type.
	 * @param type Column type (e.g. VENDOR, UNITS) 
	 */
	public void sortBy(ColumnType type)
	{
		tableUtil.getSumaryTableDAO().sortTableBy(type);
	}
	
	/**
	 * Export current summary table to HTML file.  
	 * @param fileName Given HTML filename 
	 */
	public void exportToHtml(String fileName)
	{
		tableUtil.getSumaryTableDAO().exportToHTML(fileName);
	}

	/**
	 * Export current summary table to CSV file with default ';' separator for each cell
	 * @param fileName Given CSV filename
	 */
	public void exportToCSV(String fileName)
	{
		try {
			tableUtil.getSumaryTableDAO().exportToCSV(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Print current summary table to standard output.
	 */
	public void printSummaryTable()
	{
		tableUtil.getSumaryTableDAO().printTable();
	}

	/**
	 * Print loaded CSV file as table to standard output.
	 */
	public void printCSVTable()
	{
		tableUtil.getQuartalShareDAO().printTable();
	}
	



}
