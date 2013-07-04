package com.tablecomponent.utils;

import java.util.Comparator;

import com.tablecomponent.beans.SummaryRow;

public class VendorComparator implements Comparator<SummaryRow> {

	@Override
	public int compare(SummaryRow o1, SummaryRow o2) 
	{
		return o1.getVendor().compareToIgnoreCase(o2.getVendor());
	}

}
