package com.franckyi.modcenter.api.beans;

import java.util.ArrayList;
import java.util.List;

import com.franckyi.modcenter.api.beans.enums.EnumFileType;

/**
 * <p>
 * The update result bean class. Returned when checking for updates. Stored
 * informations :
 * </p>
 * <ul>
 * <li>An {@link EnumFileType} telling the update level :</li>
 * <ul>
 * <li>{@link EnumFileType#RELEASE} if a new Release file is found</li>
 * <li>{@link EnumFileType#BETA} if a new Beta file is found</li>
 * <li>{@link EnumFileType#ALPHA} if a new Alpha file is found</li>
 * <li>{@link EnumFileType#ANY} if the {@link Project} is up to date</li>
 * </ul>
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

	private EnumFileType updateLevel = EnumFileType.ANY;
	private List<ProjectFile> newFiles = new ArrayList<>();
	private ProjectFile latestAlpha;
	private ProjectFile latestBeta;
	private ProjectFile latestRelease;

	/**
	 * @return the updateLevel
	 */
	public EnumFileType getUpdateLevel() {
		return updateLevel;
	}

	/**
	 * @param updateLevel
	 *            the updateLevel to set
	 */
	public void setUpdateLevel(EnumFileType updateLevel) {
		this.updateLevel = updateLevel;
	}

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
