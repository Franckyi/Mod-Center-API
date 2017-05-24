package com.franckyi.modcenter.api.beans;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.franckyi.modcenter.api.beans.enums.EnumFileType;
import com.franckyi.modcenter.api.misc.ModCenterUtils;

/**
 * <p>
 * The project file bean class. Stored informations :
 * </p>
 * <ul>
 * <li>The file ID</li>
 * <li>The file name</li>
 * <li>The file type (as an {@link EnumFileType})</li>
 * <li>The file size (as a {@link String})</li>
 * <li>The {@link Date} of upload</li>
 * <li>The Minecraft version of the file</li>
 * <li>The file downloads number</li>
 * <li>The file URL (as a {@link String})</li>
 * <li>The project ID of the {@link Project} this file comes from</li>
 * </ul>
 * 
 * @author Franckyi
 *
 */
public class ProjectFile {

	private int fileId;
	private String fileName;
	private EnumFileType type = EnumFileType.ANY;
	private String size;
	private Date uploaded;
	private String version;
	private int downloads;
	private String fileUrl;
	private List<Integer> optionalLibraries = new ArrayList<>();
	private List<Integer> requiredLibraries = new ArrayList<>();
	private int projectId;

	/**
	 * <p>
	 * A complete ProjectFile constructor.
	 * </p>
	 * 
	 * @param fileId
	 * @param fileName
	 * @param type
	 * @param size
	 * @param uploaded
	 * @param version
	 * @param downloads
	 * @param fileUrl
	 * @param projectId
	 */
	public ProjectFile(int fileId, String fileName, EnumFileType type, String size, Date uploaded, String version,
			int downloads, String fileUrl, List<Integer> optionalLibraries, List<Integer> requiredLibraries,
			int projectId) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.type = type;
		this.size = size;
		this.uploaded = uploaded;
		this.version = version;
		this.downloads = downloads;
		this.fileUrl = fileUrl;
		this.optionalLibraries = optionalLibraries;
		this.requiredLibraries = requiredLibraries;
		this.projectId = projectId;
	}

	/**
	 * <p>
	 * An empty ProjectFile constructor.
	 * </p>
	 */
	public ProjectFile() {
	}

	/**
	 * <p>
	 * A complete ProjectFile using a {@link ResultSet}.
	 * </p>
	 * 
	 * @param results
	 *            The results of a query
	 * @throws SQLException
	 */
	public ProjectFile(ResultSet results) throws SQLException {
		this(results.getInt(1), results.getString(2), EnumFileType.toType(results.getString(3)), results.getString(4),
				results.getDate(5), results.getString(6), results.getInt(7), results.getString(8),
				ModCenterUtils.toIntList(results.getString(9)), ModCenterUtils.toIntList(results.getString(10)),
				results.getInt(11));
	}

	/**
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the type
	 */
	public EnumFileType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(EnumFileType type) {
		this.type = type;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the uploaded
	 */
	public Date getUploaded() {
		return uploaded;
	}

	/**
	 * @param uploaded
	 *            the uploaded to set
	 */
	public void setUploaded(Date uploaded) {
		this.uploaded = uploaded;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the downloads
	 */
	public int getDownloads() {
		return downloads;
	}

	/**
	 * @param downloads
	 *            the downloads to set
	 */
	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	/**
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * @param fileUrl
	 *            the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * @return the optionalLibraries
	 */
	public List<Integer> getOptionalLibraries() {
		return optionalLibraries;
	}

	/**
	 * @param optionalLibraries
	 *            the optionalLibraries to set
	 */
	public void setOptionalLibraries(List<Integer> optionalLibraries) {
		this.optionalLibraries = optionalLibraries;
	}

	/**
	 * @return the requiredLibraries
	 */
	public List<Integer> getRequiredLibraries() {
		return requiredLibraries;
	}

	/**
	 * @param requiredLibraries
	 *            the requiredLibraries to set
	 */
	public void setRequiredLibraries(List<Integer> requiredLibraries) {
		this.requiredLibraries = requiredLibraries;
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

}
