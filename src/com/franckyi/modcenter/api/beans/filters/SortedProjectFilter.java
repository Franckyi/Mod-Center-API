package com.franckyi.modcenter.api.beans.filters;

import com.franckyi.modcenter.api.beans.Project;
import com.franckyi.modcenter.api.beans.enums.EnumCategory;
import com.franckyi.modcenter.api.beans.enums.EnumSortFilter;

/**
 * <p>
 * The filter used in API queries on {@link Project}s that needs a sort order.
 * It has all the informations from a {@link ProjectFilter}, and it also defines
 * how the results are sorted and in which direction, where :
 * </p>
 * <ul>
 * <li><code>order = true</code> means ASC</li>
 * <li><code>order = false</code> means DESC</li>
 * </ul>
 * 
 * @author Franckyi
 *
 */
public class SortedProjectFilter extends ProjectFilter {

	private EnumSortFilter sortFilter;
	private boolean order;

	/**
	 * <p>
	 * The SortedProjectFilter constructor.
	 * </p>
	 * 
	 * @param query
	 * @param version
	 * @param category
	 * @param sortFilter
	 * @param order
	 */
	public SortedProjectFilter(String query, String version, EnumCategory category, EnumSortFilter sortFilter,
			boolean order) {
		super(query, version, category);
		this.sortFilter = sortFilter;
		this.order = order;
	}

	/**
	 * @return the sortFilter
	 */
	public EnumSortFilter getSortFilter() {
		return sortFilter;
	}

	/**
	 * @return the order
	 */
	public boolean getOrder() {
		return order;
	}

}
