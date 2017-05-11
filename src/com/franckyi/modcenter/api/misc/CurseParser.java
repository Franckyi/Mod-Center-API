package com.franckyi.modcenter.api.misc;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.franckyi.modcenter.api.beans.Project;
import com.franckyi.modcenter.api.beans.ProjectFile;
import com.franckyi.modcenter.api.beans.enums.EnumCategory;
import com.franckyi.modcenter.api.beans.enums.EnumFileType;

/**
 * <p>
 * A situationnal class used to parse {@link Project}s and {@link ProjectFile}s
 * directly from <a href='http://minecraft.curseforge.com/'>Curseforge
 * website</a> using {@link Jsoup}. <strong>Follow the documentation for each
 * method from this class or it won't work ! (excepted for
 * {@link CurseParser#getFinalUrl} which doesn't need an {@link Element}
 * parameter.</strong>
 * </p>
 * 
 * @author Franckyi
 *
 */
public class CurseParser {

	private static final String WRONG_ELEMENT = "The element is wrong. Please see the Javadoc for more informations.";

	/**
	 * <p>
	 * Returns the project corresponding to the project element that can be
	 * selected using <code>li.project-list-item</code> as a CSS selector.
	 * </p>
	 * 
	 * @param el
	 *            The project element
	 * @return The project corresponding to the project element
	 * @throws Exception If the element is wrong
	 * @throws IOException See {@link Connection#get}
	 */
	public static Project getProject(Element el) throws Exception, IOException {
		if(!el.is("li.project-list-item")) throw new Exception(WRONG_ELEMENT);
		Project p = new Project();
		p.setName(ModCenterUtils
				.unescapeHTML(((Element) ((Element) el.select("div.info.name").get(0)).select("a").get(0)).html()));
		p.setAuthor(ModCenterUtils
				.unescapeHTML(((Element) ((Element) el.select("span.byline").get(0)).select("a").get(0)).html()));
		p.setTotalDl(Integer.parseInt(((Element) el.select("p.e-download-count").get(0)).html().replaceAll(",", "")));
		p.setUpdated(new Date(
				Long.parseLong(((Element) el.select("abbr.standard-datetime").get(0)).attr("data-epoch")) * 1000L));
		p.setDescription(ModCenterUtils
				.unescapeHTML(((Element) ((Element) el.select("div.description").get(0)).select("p").get(0)).html()));
		p.setProjectUrl(((Element) ((Element) el.select("div.info.name").get(0)).select("a").get(0)).attr("href"));
		p.setThumbnail(((Element) el.select("div.avatar").get(0)).select("img").size() == 0 ? ""
				: ((Element) ((Element) el.select("div.avatar").get(0)).select("img").get(0)).attr("src"));
		p.setCategories(getCategories(el));
		if (((Element) el.select("a").get(0)).attr("href").equals(""))
			return p;
		Document doc = Jsoup.connect(CurseURLFormatter.format(((Element) el.select("a").get(0)).attr("href"))).get();
		p.setProjectId(Integer.parseInt(((Element) ((Element) doc.select("li.view-on-curse").get(0)).select("a").get(0))
				.attr("href").split("/")[4]));
		return p;
	}

	/**
	 * <p>
	 * Returns the project file corresponding to the file element that can be
	 * selected using <code>tr.project-file-list-item</code> as a CSS selector.
	 * </p>
	 * 
	 * @param el
	 *            The file element
	 * @return The project file corresponding to the file element
	 * @throws Exception If the element is wrong
	 */
	public static ProjectFile getFile(Element el) throws Exception {
		if(!el.is("tr.project-file-list-item")) throw new Exception(WRONG_ELEMENT);
		ProjectFile f = new ProjectFile();
		f.setFileId(Integer.parseInt(((Element) ((Element) el.select("div.project-file-name-container").get(0))
				.select("a.overflow-tip").get(0)).attr("href").split("/")[4]));
		f.setFileName(
				ModCenterUtils.unescapeHTML(((Element) ((Element) el.select("div.project-file-name-container").get(0))
						.select("a.overflow-tip").get(0)).html()));
		f.setType(getType(el));
		f.setSize(((Element) el.select("td.project-file-size").get(0)).html());
		f.setUploaded(new Date(
				Long.parseLong(((Element) el.select("abbr.standard-datetime").get(0)).attr("data-epoch")) * 1000L));
		f.setVersion(((Element) el.select("span.version-label").get(0)).html());
		f.setDownloads(
				Integer.parseInt(((Element) el.select("td.project-file-downloads").get(0)).html().replaceAll(",", "")));
		f.setFileUrl(((Element) el.select("a.fa-icon-download").get(0)).attr("href"));
		return f;
	}

	/**
	 * <p>
	 * Returns the file type corresponding to the file element that can be
	 * selected using <code>tr.project-file-list-item</code> as a CSS selector.
	 * </p>
	 * 
	 * @param el
	 *            The file element
	 * @return The file type corresponding to the file element
	 * @throws Exception If the element is wrong
	 */
	public static EnumFileType getType(Element el) throws Exception {
		if(!el.is("tr.project-file-list-item")) throw new Exception(WRONG_ELEMENT);
		if (el.select("div.release-phase.tip").size() == 1) {
			return EnumFileType.RELEASE;
		}
		if (el.select("div.beta-phase.tip").size() == 1) {
			return EnumFileType.BETA;
		}
		if (el.select("div.alpha-phase.tip").size() == 1) {
			return EnumFileType.ALPHA;
		}
		return EnumFileType.ANY;
	}

	/**
	 * <p>
	 * Returns the list of categories corresponding to the project element that
	 * can be selected using <code>li.project-list-item</code> as a CSS
	 * selector.
	 * </p>
	 * 
	 * @param el
	 *            The project element
	 * @return The list or categories corresponding to the project element
	 * @throws Exception If the element is wrong
	 */
	public static List<EnumCategory> getCategories(Element el) throws Exception {
		if(!el.is("li.project-list-item")) throw new Exception(WRONG_ELEMENT);
		List<EnumCategory> list = new ArrayList<>();
		for (Element elm : el.select("div.category-icon-wrapper").get(0).children()) {
			String url = elm.select("a").get(0).attr("href");
			list.add(EnumCategory.toCategory(url.split("/")[(url.split("/").length - 1)]));
		}
		return list;
	}

	/**
	 * <p>
	 * Returns the final URL of the project file stocked on the server. Should be used to download the project file.
	 * </p>
	 * @param file The project file
	 * @return The final URL corresponding to the project file
	 * @throws IOException See {@link Connection#execute()}
	 */
	public static String getFinalUrl(ProjectFile file) throws IOException {
		Response response = Jsoup.connect(CurseURLFormatter.format(file.getFileUrl())).ignoreContentType(true)
				.execute();
		return (response.url().toString());
	}

}
