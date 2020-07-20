package com.l319.eduo2o.util;

/**
 *
 * @author likunrui
 * @version 1.0
 */
public class PageCalulator {
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
