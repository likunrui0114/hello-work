package com.l319.eduo2o.mapper.split;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		
		return DynamicDataSourceHolder.getDbType();
	}
	
}
