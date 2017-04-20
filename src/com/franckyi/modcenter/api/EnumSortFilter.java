package com.franckyi.modcenter.api;


/**
 * <p>
 * Enum used to build a {@link SortFilter}. Contains different types of sorting.
 * </p>
 * @author Franckyi
 *
 */
public enum EnumSortFilter {

	UPDATED("Date Updated", "updated"), TOTAL_DL("Total Downloads", "totalDl"), NAME("Name", "name"), CREATED("Date Created", "projectId");

	public String text;
	public String value;

	EnumSortFilter(String text, String value) {
		this.text = text;
		this.value = value;
	}

	public String toString() {
		return text;
	}

}
