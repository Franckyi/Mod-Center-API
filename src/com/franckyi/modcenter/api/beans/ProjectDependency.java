package com.franckyi.modcenter.api.beans;

import java.util.ArrayList;
import java.util.List;

import com.franckyi.modcenter.api.misc.DependencyManager;

/**
 * <p>
 * The bean class used when calculating {@link ProjectDependencies}. It stores
 * informations about the project itself, and the file to download.
 * </p>
 * 
 * @author Franckyi
 *
 */
public class ProjectDependency {

	private Project project;
	private ProjectFile file;

	/**
	 * <p>
	 * The ProjectDependency constructor.
	 * </p>
	 * 
	 * @param project
	 * @param file
	 */
	public ProjectDependency(Project project, ProjectFile file) {
		this.project = project;
		this.file = file;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @return the file
	 */
	public ProjectFile getFile() {
		return file;
	}

	/**
	 * <p>
	 * The bean class returned by the {@link DependencyManager}. It stores
	 * informations about the required libraries and the optional libraries.
	 * </p>
	 * 
	 * @author Franckyi
	 *
	 */
	public static class ProjectDependencies {

		private List<ProjectDependency> requiredLibraries = new ArrayList<>(), optionalLibraries = new ArrayList<>();

		/**
		 * @return the requiredLibraries
		 */
		public List<ProjectDependency> getRequiredLibraries() {
			return requiredLibraries;
		}

		/**
		 * @return the optionalLibraries
		 */
		public List<ProjectDependency> getOptionalLibraries() {
			return optionalLibraries;
		}

	}

}
