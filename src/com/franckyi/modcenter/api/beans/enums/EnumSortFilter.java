package com.franckyi.modcenter.api.beans.enums;

import org.jooq.TableField;

import com.franckyi.modcenter.api.beans.filters.SortedProjectFilter;
import com.franckyi.modcenter.api.jooq.Tables;
import com.franckyi.modcenter.api.jooq.tables.records.ProjectsRecord;

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

	UPDATED("Date Updated", Tables.PROJECTS.UPDATED),
	TOTAL_DL("Total Downloads", Tables.PROJECTS.TOTALDL),
	NAME("Name", Tables.PROJECTS.NAME),
	CREATED("Date Created", Tables.PROJECTS.PROJECTID);

	private String text;
	private TableField<ProjectsRecord,?> field;

	private EnumSortFilter(String text, TableField<ProjectsRecord,?> field) {
		this.text = text;
		this.field = field;
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
	public TableField<ProjectsRecord,?> getField() {
		return field;
	}

}
