package com.franckyi.modcenter.api;

import java.sql.Date;

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

	/**
	 * <p>
	 * A complete Project constructor.
	 * </p>
	 * 
	 * @param projectId
	 * @param name
	 * @param author
	 * @param totalDl
	 * @param lastUpdated
	 * @param description
	 * @param projectUrl
	 * @param thumbnail
	 */
	public Project(int projectId, String name, String author, int totalDl, Date updated, String description,
			String projectUrl, String thumbnail) {
		this.projectId = projectId;
		this.name = name;
		this.author = author;
		this.totalDl = totalDl;
		this.updated = updated;
		this.description = description;
		this.projectUrl = projectUrl;
		this.thumbnail = thumbnail;
	}

	/**
	 * <p>
	 * An empty Project constructor.
	 * </p>
	 */
	public Project() {
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
	 * @param lastUpdated
	 *            the lastUpdated to set
	 */
	public void setUpdated(Date lastUpdated) {
		this.updated = lastUpdated;
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

}
