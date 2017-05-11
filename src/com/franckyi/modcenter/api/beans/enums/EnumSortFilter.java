package com.franckyi.modcenter.api.beans.enums;

import com.franckyi.modcenter.api.beans.SortedProjectFilter;

/**
 * <p>
 * Enum used to build a {@link SortedProjectFilter}. Contains different types of
 * sorting.
 * </p>
 * 
 * @author Franckyi
 *
 */
public enum EnumSortFilter {

	UPDATED("Date Updated", "updated"),
	TOTAL_DL("Total Downloads", "totalDl"),
	NAME("Name", "name"),
	CREATED("Date Created", "projectId");

	private String text, value;

	private EnumSortFilter(String text, String value) {
		this.text = text;
		this.value = value;
	}

	/**
	 * Use {@link EnumSortFilter#getText()} instead
	 */
	@Deprecated()
	public String toString() {
		return getText();
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
