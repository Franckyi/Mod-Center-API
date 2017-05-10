package com.franckyi.modcenter.api.misc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.franckyi.modcenter.api.ModCenterAPI;
import com.franckyi.modcenter.api.beans.Project;
import com.franckyi.modcenter.api.beans.ProjectDependency;
import com.franckyi.modcenter.api.beans.ProjectFile;

import javafx.concurrent.Task;

public class DependencyManager extends Task<ProjectDependency> {
	
	private ProjectFile file;
	private ProjectDependency dep = new ProjectDependency();
	
	public DependencyManager(ProjectFile file) {
		this.file = file;
	}

	@Override
	protected ProjectDependency call() throws Exception {
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
		return dep;
	}

	private List<ProjectFile> addLibraries(Element deph5) throws IOException, NumberFormatException, SQLException {
		List<ProjectFile> files = new ArrayList<>();
		Element ul = deph5.nextElementSibling();
		for (Element li : ul.select("li.project-tag")) {
			String projectUrl = li.select("a").get(0).attr("href");
			Project p = ModCenterAPI.getProjectFromId(Integer.parseInt(projectUrl.replace("/projects/", "")));
			files.add(ModCenterAPI.getFilesFromProject(p, this.file.getVersion()).get(0));
		}
		return files;
	}

}
