package com.franckyi.modcenter.api.beans;

import com.franckyi.modcenter.api.beans.enums.EnumCategory;

/**
 * <p>
 * The filter used in API queries on {@link Project}s that doesn't need a sort
 * order. It has informations about a query, a version and a category.
 * </p>
 * 
 * @author Franckyi
 *
 */
public class ProjectFilter {

	private String query;
	private String version;
	private EnumCategory category;

	/**
	 * The ProjectFilter constructor.
	 * 
	 * @param query
	 * @param version
	 * @param category
	 */
	public ProjectFilter(String query, String version, EnumCategory category) {
		this.query = query;
		this.version = version;
		this.category = category;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the category
	 */
	public EnumCategory getCategory() {
		return category;
	}

}
