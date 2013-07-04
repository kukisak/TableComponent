package com.tablecomponent.tables;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import com.tablecomponent.beans.SummaryRow;

public class SummaryTable {

	private List<SummaryRow> rows = null;
	private SummaryRow totalRow = null;
	private String[] columnNames;
	
	public SummaryRow getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(SummaryRow totalRow) {
		this.totalRow = totalRow;
	}

	public String getTotalUnitsAsString()
	{
		if (totalRow == null)
		{
			return null;
		}
		return String.valueOf(Math.rint(totalRow.getUnits()));
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public List<SummaryRow> getRows() {
		return rows;
	}

	public void setRows(List<SummaryRow> rows) {
		this.rows = rows;
	}
	
	public String[] getTableFooter()
	{
		if (totalRow == null)
		{
			return null;
		}
		String[] result = new String[3];
		result[0] = totalRow.getVendor();
		result[1] = totalRow.getUnitsAsString();
		result[2] = totalRow.getShareAsString();
		return result;
	}
	
	public void printTable()
	{
		if (rows == null || totalRow == null)
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
			SummaryRow row = (SummaryRow) iter.next();
			System.out.print(row.getVendor() + " " + row.getUnitsAsString() + " " + row.getShareAsString());
			System.out.println();
		}
		System.out.println(totalRow.getVendor() + " " + totalRow.getUnitsAsString() + " " + totalRow.getShareAsString());
	}
	

}
