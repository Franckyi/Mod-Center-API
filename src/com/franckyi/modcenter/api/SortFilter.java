package com.franckyi.modcenter.api;


/**
 * <p>
 * The filter used in most API queries. It defines how the results are sorted and in which direction, where :
 * </p>
 * <ul>
 * <li><code>order = true</code> means ASC</li>
 * <li><code>order = false</code> means DESC</li>
 * </ul>
 * @author franck
 *
 */
public class SortFilter {
	
	private EnumSortFilter filter;
	private boolean order;
	
	/**
	 * The SortFilter constructor.
	 * @param filter
	 * @param order
	 */
	public SortFilter(EnumSortFilter filter, boolean order) {
		this.filter = filter;
		this.order = order;
	}
	/**
	 * @return the filter
	 */
	public EnumSortFilter getFilter() {
		return filter;
	}
	/**
	 * @return the order
	 */
	public boolean getOrder() {
		return order;
	}
	
	

}
