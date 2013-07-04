package com.tablecomponent.beans;

import java.text.MessageFormat;

public class SummaryRow {

	private String vendor;
	private double units;
	private double share;
	
	public SummaryRow() {
	}
	
	
	public SummaryRow(String vendor, double units, double share) 
	{
		this.vendor = vendor;
		this.units = units;
		this.share = share;
	}


	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public double getUnits() {
		return units;
	}
	public void setUnits(double units) {
		this.units = units;
	}
	public double getShare() {
		return share;
	}
	public void setShare(double share) {
		this.share = share;
	}
	
	public double getUnitsAsIntEquivalent()
	{
		return (double) Math.rint(getUnits());
	}
	
	public String getUnitsAsString()
	{
		return MessageFormat.format("{0,number,#}", getUnitsAsIntEquivalent());
	}
	
	public String getShareAsString()
	{
		return MessageFormat.format("{0,number,#.#%}", getShare());
	}
	
	public String[] getValues()
	{
		String[] result = new String[3];
		result[0] = getVendor();
		result[1] = getUnitsAsString();
		result[2] = getShareAsString();
		return result;
	}
	
}
