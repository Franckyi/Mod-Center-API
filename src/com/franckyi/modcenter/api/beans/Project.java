package com.franckyi.modcenter.api.beans;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.franckyi.modcenter.api.beans.enums.EnumCategory;

/**
 * <p>
 * The project bean class. Stored informations :
 * </p>
 * <ul>
 * <li>The project ID</li>
 * <li>The project name</li>
 * <li>The project author</li>
 * <li>The total downloads number</li>
 * <li>The {@link Date} of last update</li>
 * <li>The description of the project</li>
 * <li>The project URL (as a {@link String})</li>
 * <li>The thumbnail URL (as a {@link String})</li>
 * <li>The list of {@link EnumCategory}</li>
 * </ul>
 * 
 * @author Franckyi
 *
 */
public class Project {

	private int projectId;
	private String name;
	private String author;
	private int totalDl;
	private Date updated;
	private String description;
	private String projectUrl;
	private String thumbnail;
	private List<EnumCategory> categories;

	/**
	 * <p>
	 * A complete Project constructor.
	 * </p>
	 * 
	 * @param projectId
	 * @param name
	 * @param author
	 * @param totalDl
	 * @param updated
	 * @param description
	 * @param projectUrl
	 * @param thumbnail
	 * @param categories
	 */
	public Project(int projectId, String name, String author, int totalDl, Date updated, String description,
			String projectUrl, String thumbnail, List<EnumCategory> categories) {
		this.projectId = projectId;
		this.name = name;
		this.author = author;
		this.totalDl = totalDl;
		this.updated = updated;
		this.description = description;
		this.projectUrl = projectUrl;
		this.thumbnail = thumbnail;
		this.categories = categories;
	}

	/**
	 * <p>
	 * An empty Project constructor.
	 * </p>
	 */
	public Project() {
	}

	/**
	 * <p>
	 * A complete Project using a {@link ResultSet}.
	 * </p>
	 * 
	 * @param results
	 *            The results of a query
	 * @throws SQLException
	 */
	public Project(ResultSet results) throws SQLException {
		this(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4), results.getDate(5),
				results.getString(6), results.getString(7), results.getString(8),
				EnumCategory.format(results.getString(9)));
	}

	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the totalDl
	 */
	public int getTotalDl() {
		return totalDl;
	}

	/**
	 * @param totalDl
	 *            the totalDl to set
	 */
	public void setTotalDl(int totalDl) {
		this.totalDl = totalDl;
	}

	/**
	 * @return the lastUpdated
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the projectUrl
	 */
	public String getProjectUrl() {
		return projectUrl;
	}

	/**
	 * @param projectUrl
	 *            the projectUrl to set
	 */
	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param thumbnail
	 *            the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * @return the categories
	 */
	public List<EnumCategory> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<EnumCategory> categories) {
		this.categories = categories;
	}

}
