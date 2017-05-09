package com.franckyi.modcenter.api;

/**
 * <p>
 * The update result bean class. Returned when checking for updates. Stored informations :
 * </p>
 * <ul>
 * <li>A boolean saying if the project is up to date</li>
 * <li>The latest alpha {@link ProjectFile}</li>
 * <li>The latest beta {@link ProjectFile}</li>
 * <li>The latest release {@link ProjectFile}</li>
 * </ul>
 * 
 * @author Franckyi
 *
 */
public class UpdateResult {
	
	private boolean upToDate;
	private ProjectFile latestAlpha;
	private ProjectFile latestBeta;
	private ProjectFile latestRelease;
	
	/**
	 * <p>
	 * A complete UpdateResult constructor.
	 * </p>
	 * 
	 * @param upToDate
	 * @param latestAlpha
	 * @param latestBeta
	 * @param latestRelease
	 */
	public UpdateResult(boolean upToDate, ProjectFile latestAlpha, ProjectFile latestBeta,
			ProjectFile latestRelease) {
		this.upToDate = upToDate;
		this.latestAlpha = latestAlpha;
		this.latestBeta = latestBeta;
		this.latestRelease = latestRelease;
	}

	/**
	 * <p>
	 * An empty UpdateResult constructor.
	 * </p>
	 */
	public UpdateResult() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the upToDate
	 */
	public boolean isUpToDate() {
		return upToDate;
	}

	/**
	 * @param upToDate the upToDate to set
	 */
	public void setUpToDate(boolean upToDate) {
		this.upToDate = upToDate;
	}

	/**
	 * @return the latestAlpha
	 */
	public ProjectFile getLatestAlpha() {
		return latestAlpha;
	}

	/**
	 * @param latestAlpha the latestAlpha to set
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
	 * @param latestBeta the latestBeta to set
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
	 * @param latestRelease the latestRelease to set
	 */
	public void setLatestRelease(ProjectFile latestRelease) {
		this.latestRelease = latestRelease;
	}

}
