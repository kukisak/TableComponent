package com.tablecomponent;

import com.tablecomponent.test.TableComponentTest;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TableComponentTest testComp = new TableComponentTest();
		testComp.testReadCSVTable();
		testComp.testSummaryTableQuery();
		testComp.testGetVendorShare();
		testComp.testGetVendorRowNumber();
		testComp.testSortBy();
		testComp.testExportToHtml();
		testComp.testExportToHtmlSorted();
		testComp.testExportToCSV();
	}

}
