package com.franckyi.modcenter.api.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * The update result bean class. Returned when checking for updates. Stored
 * informations :
 * </p>
 * <ul>
 * <li>The list of new {@link ProjectFile}s</li>
 * <li>The latest alpha {@link ProjectFile}</li>
 * <li>The latest beta {@link ProjectFile}</li>
 * <li>The latest release {@link ProjectFile}</li>
 * </ul>
 * 
 * @author Franckyi
 *
 */
public class UpdateResult {

	private List<ProjectFile> newFiles = new ArrayList<>();
	private ProjectFile latestAlpha;
	private ProjectFile latestBeta;
	private ProjectFile latestRelease;

	/**
	 * @return the newFiles
	 */
	public List<ProjectFile> getNewFiles() {
		return newFiles;
	}

	/**
	 * @param newFiles
	 *            the newFiles to set
	 */
	public void setNewFiles(List<ProjectFile> newFiles) {
		this.newFiles = newFiles;
	}

	/**
	 * @return the latestAlpha
	 */
	public ProjectFile getLatestAlpha() {
		return latestAlpha;
	}

	/**
	 * @param latestAlpha
	 *            the latestAlpha to set
	 */
	public void setLatestAlpha(ProjectFile latestAlpha) {
		this.latestAlpha = latestAlpha;
	}

	/**
	 * @return the latestBeta
	 */
	public ProjectFile getLatestBeta() {
		return latestBeta;
	}

	/**
	 * @param latestBeta
	 *            the latestBeta to set
	 */
	public void setLatestBeta(ProjectFile latestBeta) {
		this.latestBeta = latestBeta;
	}

	/**
	 * @return the latestRelease
	 */
	public ProjectFile getLatestRelease() {
		return latestRelease;
	}

	/**
	 * @param latestRelease
	 *            the latestRelease to set
	 */
	public void setLatestRelease(ProjectFile latestRelease) {
		this.latestRelease = latestRelease;
	}

}
