package com.tablecomponent.utils;

import com.tablecomponent.daos.QuartalShareDAO;
import com.tablecomponent.daos.SummaryTableDAO;

public class TableUtil 
{

	private SummaryTableDAO summaryTableDAO = null;
	private QuartalShareDAO quartalShareDAO = null;
	private final Configuration defaultQuartalShareTableCofig = 
			new Configuration("data.csv", ',');
	
	public SummaryTableDAO getSumaryTableDAO()
	{
		if (summaryTableDAO == null)
		{
			summaryTableDAO = new SummaryTableDAO();
		}
		return summaryTableDAO;
	}
	
	public QuartalShareDAO getQuartalShareDAO(Configuration config)
	{
		if (quartalShareDAO == null) 
		{
			quartalShareDAO = new QuartalShareDAO(config.getCsvFileName(), config.getSeparator());
		}
		return quartalShareDAO;
	}
	
	public QuartalShareDAO getQuartalShareDAO()
	{
		if (quartalShareDAO == null)
		{
			quartalShareDAO = new QuartalShareDAO(
					defaultQuartalShareTableCofig.getCsvFileName(), 
					defaultQuartalShareTableCofig.getSeparator()
					);
		}
		return quartalShareDAO;
	}
	
			
}
