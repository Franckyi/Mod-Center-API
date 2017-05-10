package com.franckyi.modcenter.api.beans;

import java.util.ArrayList;
import java.util.List;

public class ProjectDependency {

	private List<ProjectFile> requiredLibraries = new ArrayList<>(), optionalLibraries = new ArrayList<>();

	/**
	 * @return the requiredLibraries
	 */
	public List<ProjectFile> getRequiredLibraries() {
		return requiredLibraries;
	}

	/**
	 * @return the optionalLibraries
	 */
	public List<ProjectFile> getOptionalLibraries() {
		return optionalLibraries;
	}

}
