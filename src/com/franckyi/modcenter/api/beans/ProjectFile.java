package com.franckyi.modcenter.api.beans;

import static com.franckyi.modcenter.api.jooq.Tables.FILES;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.jooq.Record;

import com.franckyi.modcenter.api.beans.enums.EnumFileType;

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
 * <li>The {@link Project} this file comes from</li>
 * </ul>
 * 
 * @author Franckyi
 *
 */
public class ProjectFile {

	private int fileId;
	private String fileName;
	private EnumFileType type;
	private String size;
	private Date uploaded;
	private List<String> versions = new ArrayList<>();
	private int downloads;
	private String fileUrl;
	private List<Project> optionalLibraries = new ArrayList<>();
	private List<Project> requiredLibraries = new ArrayList<>();
	private Project project;

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
	 * @param optionalLibraries
	 * @param requiredLibraries
	 * @param projectId
	 */
	public ProjectFile(int fileId, String fileName, EnumFileType type, String size, Date uploaded,
			List<String> versions, int downloads, String fileUrl, List<Project> optionalLibraries,
			List<Project> requiredLibraries, Project project) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.type = type;
		this.size = size;
		this.uploaded = uploaded;
		this.versions = versions;
		this.downloads = downloads;
		this.fileUrl = fileUrl;
		this.optionalLibraries = optionalLibraries;
		this.requiredLibraries = requiredLibraries;
		this.project = project;
	}

	/**
	 * <p>
	 * An empty ProjectFile constructor.
	 * </p>
	 */
	public ProjectFile() {
	}

	public ProjectFile(Record r, List<String> versions, List<Project> optionalLibraries,
			List<Project> requiredLibraries, Project p) {
		this(r.get(FILES.FILEID), r.get(FILES.FILENAME), EnumFileType.toType(r.get(FILES.TYPE)), r.get(FILES.SIZE),
				r.get(FILES.UPLOADED), versions, r.get(FILES.DOWNLOADS), r.get(FILES.FILEURL), optionalLibraries,
				requiredLibraries, p);
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
	 * @return the versions
	 */
	public List<String> getVersions() {
		return versions;
	}

	/**
	 * @param versions
	 *            the versions to set
	 */
	public void setVersions(List<String> versions) {
		this.versions = versions;
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
	public List<Project> getOptionalLibraries() {
		return optionalLibraries;
	}

	/**
	 * @param optionalLibraries
	 *            the optionalLibraries to set
	 */
	public void setOptionalLibraries(List<Project> optionalLibraries) {
		this.optionalLibraries = optionalLibraries;
	}

	/**
	 * @return the requiredLibraries
	 */
	public List<Project> getRequiredLibraries() {
		return requiredLibraries;
	}

	/**
	 * @param requiredLibraries
	 *            the requiredLibraries to set
	 */
	public void setRequiredLibraries(List<Project> requiredLibraries) {
		this.requiredLibraries = requiredLibraries;
	}

	/**
	 * @return the projectId
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

}
