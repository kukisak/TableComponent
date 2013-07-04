package com.tablecomponent.transformers;

public class HTMLtransformer 
{
	private String tableHeaderBgColor = "bgcolor=\"#D9D9D9\"";
	private String tableFooterBgColor = "bgcolor=\"#FFFF99\"";
	private String cellAlignCenter = "align=\"center\"";

	public String transformHtmlBodyBegin()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n<body>\n");
		return sb.toString();		
	}
	
	public String transformHtmlBodyEnd()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("</body>\n</html>\n");
		return sb.toString();		
	}
	
	public String transformTableBegin()
	{
		return "<table border=\"1\" width=\"500\">";
	}
	
	public String transformTableEnd()
	{
		return "</table>";
	}
	
	public String transformTableCaption(String country, String quartal)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<caption>");
		sb.append("Table 1, PC Quarterly Market Share, the " + country + ", " + quartal);
		sb.append("</caption>");
		return sb.toString();
	}
	
	public String transformTableHeader(String[] array) 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		for (String string : array) 
		{
			sb.append("<th "+ tableHeaderBgColor + ">" + string + "</th>");
		}
		sb.append("</tr>");
		return sb.toString();
	}

	public String transformTableRow(String[] array) 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		for (String string : array) 
		{
			sb.append("<td " + cellAlignCenter + ">" + string + "</td>");
		}
		sb.append("</tr>");
		return sb.toString();
	}

	public String transformTableFooter(String[] array) 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		for (String string : array) 
		{
			sb.append("<td "+ tableFooterBgColor + cellAlignCenter + ">" + string + "</td>");
		}
		sb.append("</tr>");
		return sb.toString();
	}

}
