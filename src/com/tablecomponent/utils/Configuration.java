package com.tablecomponent.utils;

public class Configuration {
	private String csvFileName = null;
	private char separator;
	public String getCsvFileName() {
		return csvFileName;
	}
	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}
	public char getSeparator() {
		return separator;
	}
	public void setSeparator(char separator) {
		this.separator = separator;
	}
	
	public Configuration(String csvFileName, char separator)
	{
		this.setCsvFileName(csvFileName);
		this.setSeparator(separator);
	}
	
}
