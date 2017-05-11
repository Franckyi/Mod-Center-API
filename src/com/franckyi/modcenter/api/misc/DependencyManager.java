package com.franckyi.modcenter.api.misc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.franckyi.modcenter.api.ModCenterAPI;
import com.franckyi.modcenter.api.beans.Project;
import com.franckyi.modcenter.api.beans.ProjectDependency;
import com.franckyi.modcenter.api.beans.ProjectDependency.ProjectDependencies;
import com.franckyi.modcenter.api.beans.ProjectFile;

/**
 * <p>
 * The class used to calculate a {@link ProjectDependencies}. The process may be
 * long, depending on your Internet connection; this is why this class
 * implements {@link Runnable}.
 * </p>
 * 
 * @author Franckyi
 *
 */
public class DependencyManager implements Runnable {

	private ProjectFile file;
	private ProjectDependencies dep = new ProjectDependencies();
	private boolean ended = false;

	/**
	 * <p>
	 * The DependencyManager constructor.
	 * </p>
	 * 
	 * @param file
	 */
	public DependencyManager(ProjectFile file) {
		this.file = file;
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(CurseURLFormatter.format(file.getFileUrl().replace("/download", ""))).get();
			Elements depsh5 = doc.select("section.details-related-projects").get(0).select("h5");
			if (depsh5.size() > 0) {
				for (Element deph5 : depsh5) {
					if (deph5.text().equalsIgnoreCase("Optional Library"))
						dep.getOptionalLibraries().addAll(addLibraries(deph5));
					else
						dep.getRequiredLibraries().addAll(addLibraries(deph5));
				}
			}
		} catch (IOException | NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		ended = true;
	}

	private List<ProjectDependency> addLibraries(Element deph5)
			throws IOException, NumberFormatException, SQLException {
		List<ProjectDependency> files = new ArrayList<>();
		Element ul = deph5.nextElementSibling();
		for (Element li : ul.select("li.project-tag")) {
			String projectUrl = li.select("a").get(0).attr("href");
			Project p = ModCenterAPI.getProjectFromId(Integer.parseInt(projectUrl.replace("/projects/", "")));
			files.add(new ProjectDependency(p, ModCenterAPI.getFilesFromProject(p, this.file.getVersion()).get(0)));
		}
		return files;
	}

	/**
	 * <p>
	 * Simply calculates the dependencies by running the process, then returns
	 * the value. If you don't want your application to freeze during the
	 * process, launch this method in another {@link Thread}. For example, you
	 * can use a {@link FutureTask}.
	 * </p>
	 * 
	 * @return The dependencies
	 */
	public ProjectDependencies calculate() {
		run();
		while (!ended)
			;
		return dep;
	}

}
