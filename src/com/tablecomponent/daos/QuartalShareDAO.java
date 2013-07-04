package com.tablecomponent.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.tablecomponent.beans.QuartalShareRow;
import com.tablecomponent.beans.SummaryRow;
import com.tablecomponent.tables.QuartalShareTable;

public class QuartalShareDAO 
{
	private QuartalShareTable quartalShareTable = null;

	public QuartalShareDAO(String csvFileName, char separator) 
	{
		this.quartalShareTable = new QuartalShareTable();
		this.quartalShareTable.readData(csvFileName, separator);
	}
	
	public List<SummaryRow> getSummaryRows(String country, String quartal)
	{
		List<SummaryRow> results = new ArrayList<SummaryRow>();
		
		List<QuartalShareRow> tempSelect = new ArrayList<QuartalShareRow>();
		List<QuartalShareRow> quartalShareRows = quartalShareTable.getRows();
		Iterator iter = quartalShareRows.iterator();
		// get all rows which satisfies country and quartal values
		while (iter.hasNext())
		{
			QuartalShareRow row = (QuartalShareRow) iter.next();
			if (row.getCountry().equalsIgnoreCase(country) && 
					row.getTimescale().equalsIgnoreCase(quartal))
			{
				tempSelect.add(row);
			}
		}
		// TODO: group rows by same vendor and add them to result set
		// create SummaryRows from selected rows and add them to result
		iter = tempSelect.iterator();
		while (iter.hasNext())
		{
			QuartalShareRow tempSelectRow = (QuartalShareRow)iter.next();
			SummaryRow sumRow = new SummaryRow();
			sumRow.setVendor(tempSelectRow.getVendor());
			sumRow.setUnits(tempSelectRow.getUnits());
			sumRow.setShare(0);
			results.add(sumRow);
		}
		return results;
		
	}
	
	public void printTable()
	{
		if (this.quartalShareTable == null)
		{
			System.out.println("Table is empty");
			return;
		}
		this.quartalShareTable.printTable();
	}
	
	
	

}
