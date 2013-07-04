package com.tablecomponent.utils;

import java.util.Comparator;

import com.tablecomponent.beans.SummaryRow;

public class UnitsComparator implements Comparator<SummaryRow> {

	@Override
	public int compare(SummaryRow o1, SummaryRow o2) {
		if (o1.getUnits() < o2.getUnits())
		{
			return -1;
		}
		else if (o1.getUnits() == o2.getUnits())
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

}
