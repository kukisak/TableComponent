package com.tablecomponent.tables;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.tablecomponent.beans.QuartalShareRow;

public class QuartalShareTable {

	private List<QuartalShareRow> rows;
	private String[] columnNames;
	
	public List<QuartalShareRow> getRows() {
		return rows;
	}

	public void setRows(List<QuartalShareRow> rows) {
		this.rows = rows;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public QuartalShareTable() 
	{
	}
	
	public void readData(String csvFileName, char separator)
	{
		rows = null;
		columnNames = null;
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFileName), separator);
			if ((columnNames = reader.readNext()) == null)
			{
				System.err.println("CSV file is empty");
			}
			String[] nextLine = null;
			rows = new ArrayList<QuartalShareRow>();
			int index = 1;
			while ((nextLine = reader.readNext()) != null)
			{
				QuartalShareRow oneRow = new QuartalShareRow();
				oneRow.setId(index);
				oneRow.setCountry(nextLine[0]);
				oneRow.setTimescale(nextLine[1]);
				oneRow.setVendor(nextLine[2]);
				oneRow.setUnits(Double.parseDouble(nextLine[3]));
				rows.add(oneRow);
				index++;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printTable()
	{
		if (rows == null)
		{
			System.out.println("Table is empty");
			return;
		}
		for (String colName : getColumnNames()) 
		{
			System.out.print(colName + " ");
		}
		System.out.println();
		Iterator iter = getRows().iterator();
		while (iter.hasNext())
		{
			QuartalShareRow row = (QuartalShareRow) iter.next();
			System.out.println(row.getCountry() + " " + row.getTimescale() + " " + row.getVendor() + 
					" " + row.getUnits());
			
		}
	}
	
	
}
