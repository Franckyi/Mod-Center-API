package com.franckyi.modcenter.api.beans;

import java.util.Arrays;
import java.util.List;

import com.franckyi.modcenter.api.beans.enums.EnumFileType;

/**
 * <p>
 * This class is used when showing {@link ProjectFile}s from a {@link Project}.
 * It contains informations about the file version and release types.
 * </p>
 * 
 * @author Franck
 *
 */
public class ProjectFileFilter {

	public static final ProjectFileFilter DEFAULT = new ProjectFileFilter("", Arrays.asList(EnumFileType.values()));

	private String version;
	private List<EnumFileType> types;

	/**
	 * <p>
	 * A complete ProjectFileFilter constructor.
	 * </p>
	 * 
	 * @param version
	 * @param types
	 */
	public ProjectFileFilter(String version, List<EnumFileType> types) {
		this.version = version;
		this.types = types;
	}

	/**
	 * <p>
	 * An alternative complete ProjectFileFilter constuctor. It will not filter
	 * file types.
	 * </p>
	 * 
	 * @param version
	 */
	public ProjectFileFilter(String version) {
		this.version = version;
		this.types = Arrays.asList(EnumFileType.values());
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the types
	 */
	public List<EnumFileType> getTypes() {
		return types;
	}

}
