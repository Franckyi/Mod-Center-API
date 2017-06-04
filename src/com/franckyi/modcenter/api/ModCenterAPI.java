package com.franckyi.modcenter.api;

import static com.franckyi.modcenter.api.jooq.Tables.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import com.franckyi.modcenter.api.beans.Project;
import com.franckyi.modcenter.api.beans.ProjectFile;
import com.franckyi.modcenter.api.beans.enums.EnumFileType;
import com.franckyi.modcenter.api.beans.filters.ProjectFileFilter;
import com.franckyi.modcenter.api.beans.filters.ProjectFilter;
import com.franckyi.modcenter.api.beans.filters.SortedProjectFilter;
import com.franckyi.modcenter.api.jooq.tables.records.CategoriesRecord;
import com.franckyi.modcenter.api.jooq.tables.records.FilesRecord;
import com.franckyi.modcenter.api.jooq.tables.records.OptionallibrariesRecord;
import com.franckyi.modcenter.api.jooq.tables.records.ProjectsRecord;
import com.franckyi.modcenter.api.jooq.tables.records.RequiredlibrariesRecord;
import com.franckyi.modcenter.api.jooq.tables.records.VersionsRecord;

public class ModCenterAPI {

	private static final String JDBC_CONNECTION = "jdbc:mysql://mysql-franckyi.alwaysdata.net/franckyi_modcenter";
	private static final String JDBC_USERNAME = "franckyi_public";
	private static final String JDBC_PASSWORD = "public";

	private static Connection conn;
	private static DSLContext ctx;

	/**
	 * <p>
	 * Closes the connection between your program and the mod center.
	 * </p>
	 * 
	 * @throws SQLException
	 */
	public static void close() throws SQLException {
		conn.close();
	}

	/**
	 * <p>
	 * Returns the connection object used to communicate with the database.
	 * </p>
	 * 
	 * @return The connection
	 */
	public static Connection getConnection() {
		return conn;
	}

	public static DSLContext getDSLContext() {
		return ctx;
	}

	/**
	 * <p>
	 * Returns a {@link ProjectFile} from the database with the given name.
	 * </p>
	 * 
	 * @param name
	 *            The file name
	 * @return
	 * @throws SQLException
	 */
	public static Optional<ProjectFile> getFileFromName(String name) throws SQLException {
		String nameLike = "%" + name + "%";
		Result<FilesRecord> rec = ctx.selectFrom(FILES).where(FILES.FILENAME.like(nameLike)).fetch();
		if (rec.size() == 1)
			return newFile(rec.get(0));
		return Optional.empty();
	}

	/**
	 * <p>
	 * Returns a list of {@link ProjectFile}s from a {@link Project} for a
	 * defined version and release types.
	 * </p>
	 * 
	 * @param project
	 *            The project
	 * @param filter
	 *            The project file filter to use in the query
	 * @return The list of {@link ProjectFile}s from this {@link Project}
	 * @throws SQLException
	 */
	public static List<ProjectFile> getFilesFromProject(Project project, ProjectFileFilter filter) throws SQLException {
		List<ProjectFile> files = new ArrayList<>();
		Result<FilesRecord> rec = ctx.selectFrom(FILES).where(FILES.PROJECTID.eq(project.getProjectId()))
				.and(FILES.TYPE.in(filter.getTypesKeys())).fetch();
		for (FilesRecord file : rec) {
			Result<VersionsRecord> versions = ctx.selectFrom(VERSIONS).where(VERSIONS.FILEID.eq(file.getFileid()))
					.fetch();
			if (versions.getValues(VERSIONS.VERSION).contains(filter.getVersion())) {
				Optional<ProjectFile> pf = newFile(file);
				if (pf.isPresent())
					files.add(pf.get());
			}
		}
		return files;
	}

	/**
	 * <p>
	 * Returns the {@link Project} where the given {@link ProjectFile} comes
	 * from.
	 * </p>
	 * 
	 * @param file
	 *            The file
	 * @return
	 * @throws SQLException
	 */
	public static Optional<Project> getProjectFromFile(ProjectFile file) throws SQLException {
		Result<ProjectsRecord> rec = ctx.selectFrom(PROJECTS)
				.where(PROJECTS.PROJECTID.eq(file.getProject().getProjectId())).fetch();
		if (rec.size() == 1)
			return Optional.of(newProject(rec.get(0)));
		return Optional.empty();
	}

	/**
	 * <p>
	 * Returns the project corresponding to the project ID.
	 * </p>
	 * 
	 * @param id
	 *            The project ID
	 * @return The project
	 * @throws SQLException
	 */
	public static Optional<Project> getProjectFromId(int id) throws SQLException {
		Result<ProjectsRecord> rec = ctx.selectFrom(PROJECTS).where(PROJECTS.PROJECTID.eq(id)).fetch();
		if (rec.size() == 1)
			return Optional.of(newProject(rec.get(0)));
		return Optional.empty();
	}

	/**
	 * <p>
	 * Returns a list of {@link Project}s, depending on the page number and the
	 * number of items on a page. The results are sorted by a
	 * {@link SortedProjectFilter}.
	 * </p>
	 * 
	 * @param page
	 *            The page number
	 * @param count
	 *            The number of items on the page
	 * @param filter
	 *            The sorted project filter to use in the query
	 * @return A list of mod projects
	 * @throws SQLException
	 */
	public static List<Project> getProjects(int page, int count, SortedProjectFilter filter) throws SQLException {
		List<Project> projects = new ArrayList<>();
		String query = "%" + filter.getQuery() + "%";
		SortField<?> order = (filter.getOrder()) ? filter.getSortFilter().getField().asc()
				: filter.getSortFilter().getField().desc();
		Result<Record1<Integer>> sub = ctx.selectDistinct(FILES.PROJECTID).from(FILES).innerJoin(VERSIONS)
				.on(FILES.FILEID.eq(VERSIONS.FILEID)).where(VERSIONS.VERSION.like("%" + filter.getVersion() + "%"))
				.fetch();
		Result<Record> rec = ctx.selectDistinct(PROJECTS.fields()).from(PROJECTS).innerJoin(CATEGORIES)
				.on(PROJECTS.PROJECTID.eq(CATEGORIES.PROJECTID)).where(PROJECTS.PROJECTID.in(sub))
				.and((PROJECTS.NAME.like(query)).or(PROJECTS.AUTHOR.like(query)).or(PROJECTS.DESCRIPTION.like(query)))
				.and(CATEGORIES.CATEGORY.like("%" + filter.getCategory().getDbKey() + "%")).orderBy(order)
				.limit((page - 1) * count, count).fetch();
		System.out.println(ctx.selectDistinct(PROJECTS.fields()).from(PROJECTS).innerJoin(CATEGORIES)
				.on(PROJECTS.PROJECTID.eq(CATEGORIES.PROJECTID)).where(PROJECTS.PROJECTID.in(sub))
				.and((PROJECTS.NAME.like(query)).or(PROJECTS.AUTHOR.like(query)).or(PROJECTS.DESCRIPTION.like(query)))
				.and(CATEGORIES.CATEGORY.like("%" + filter.getCategory().getDbKey() + "%")).orderBy(order)
				.limit((page - 1) * count, count).toString());
		for (Record record : rec)
			projects.add(newProject(record));
		return projects;
	}

	/**
	 * <p>
	 * Returns the number of pages of {@link Project}s for a certain number of
	 * {@link Project}s per page.
	 * </p>
	 * 
	 * @param count
	 *            The number of {@link Project}s per page
	 * @param filter
	 *            The project filter to use in the query
	 * @return The number of pages of {@link Project}s containing this keyword
	 * @throws SQLException
	 */
	public static int getProjectsPageNumber(int count, ProjectFilter filter) throws SQLException {
		String query = "%" + filter.getQuery() + "%";
		Result<Record1<Integer>> sub = ctx.select(FILES.PROJECTID).from(FILES).innerJoin(VERSIONS)
				.on(FILES.FILEID.eq(VERSIONS.FILEID)).where(VERSIONS.VERSION.like("%" + filter.getVersion() + "%"))
				.fetch();
		Result<Record1<Integer>> rec = ctx.selectCount().from(PROJECTS).innerJoin(CATEGORIES)
				.on(PROJECTS.PROJECTID.eq(CATEGORIES.PROJECTID)).where(PROJECTS.PROJECTID.in(sub))
				.and((PROJECTS.NAME.like(query)).or(PROJECTS.AUTHOR.like(query)).or(PROJECTS.DESCRIPTION.like(query)))
				.and(CATEGORIES.CATEGORY.eq(filter.getCategory().getDbKey())).fetch();
		if (rec.size() == 1)
			return rec.get(0).value1();
		return 0;
	}

	/**
	 * <p>
	 * Returns the list of all {@link ProjectFile}'s versions that can be found
	 * in the database.
	 * </p>
	 * 
	 * @return The list of versions
	 * @throws SQLException
	 */
	public static List<String> getVersions() throws SQLException {
		List<String> list = new ArrayList<>();
		Result<Record1<String>> rec = ctx.selectDistinct(VERSIONS.VERSION).from(VERSIONS).fetch();
		for (Record1<String> str : rec)
			list.add(str.value1());
		return list;
	}

	/**
	 * <p>
	 * Returns the list of all {@link ProjectFile}'s versions that can be found
	 * in the database for a project.
	 * </p>
	 * 
	 * @param project
	 *            The project
	 * @return The list of versions for this project
	 * @throws SQLException
	 */
	public static List<String> getVersions(Project project) throws SQLException {
		List<String> list = new ArrayList<>();
		Result<Record1<String>> rec = ctx.selectDistinct(VERSIONS.VERSION).from(VERSIONS).innerJoin(FILES)
				.on(VERSIONS.FILEID.eq(FILES.FILEID)).where(FILES.PROJECTID.eq(project.getProjectId())).fetch();
		for (Record1<String> str : rec)
			list.add(str.value1());
		return list;
	}

	/**
	 * <p>
	 * Initializes the connection between your program and the mod center.
	 * </p>
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void init() throws SQLException, ClassNotFoundException {
		DriverManager.setLoginTimeout(5);
		conn = DriverManager.getConnection(JDBC_CONNECTION, JDBC_USERNAME, JDBC_PASSWORD);
		ctx = DSL.using(conn, SQLDialect.MYSQL);
	}

	/**
	 * <p>
	 * Initializes the connection between your program and the mod center, with
	 * a defined username and password for the database.
	 * </p>
	 * 
	 * @param username
	 *            The username
	 * @param password
	 *            The password
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void init(String username, String password) throws SQLException, ClassNotFoundException {
		DriverManager.setLoginTimeout(5);
		conn = DriverManager.getConnection(JDBC_CONNECTION, username, password);
		ctx = DSL.using(conn, SQLDialect.MYSQL);
	}

	/**
	 * <p>
	 * This method is used to check for updates. It will return the updated
	 * file. The result depends on the current file stability :
	 * <ul>
	 * <li>If the file is a release file, it will only check for new
	 * releases.</li>
	 * <li>If the file is a beta file, it will only check for new betas or
	 * releases.</li>
	 * <li>If the file is an alpha file, it will check for any new file.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param file
	 *            The project file to update
	 * @return The updated file
	 * @throws SQLException
	 */
	public static Optional<ProjectFile> update(ProjectFile file) throws SQLException {
		List<String> types = new ArrayList<>();
		for (EnumFileType type : EnumFileType.values())
			if (!type.equals(EnumFileType.ANY) && type.getLevel() > file.getType().getLevel())
				types.add(type.getDbKey());
		Result<Record> rec = ctx.select(FILES.fields()).from(FILES).innerJoin(VERSIONS)
				.on(FILES.FILEID.eq(VERSIONS.FILEID)).where(FILES.PROJECTID.eq(file.getProject().getProjectId()))
				.and(FILES.FILEID.gt(file.getFileId())).and(VERSIONS.VERSION.in(file.getVersions()))
				.and(FILES.TYPE.in(types)).limit(1).fetch();
		if (rec.size() == 1)
			return newFile(rec.get(0));
		return Optional.empty();
	}

	public static Project newProject(Record rec) {
		Result<CategoriesRecord> categories = ctx.selectFrom(CATEGORIES)
				.where(CATEGORIES.PROJECTID.eq(rec.get(PROJECTS.PROJECTID))).fetch();
		return new Project(rec, categories.getValues(CATEGORIES.CATEGORY));
	}

	public static Optional<ProjectFile> newFile(Record rec) throws SQLException {
		Result<VersionsRecord> versions = ctx.selectFrom(VERSIONS).where(VERSIONS.FILEID.eq(rec.get(FILES.FILEID))).fetch();
		Result<OptionallibrariesRecord> optionalLibraries = ctx.selectFrom(OPTIONALLIBRARIES)
				.where(OPTIONALLIBRARIES.FILEID.eq(rec.get(FILES.FILEID))).fetch();
		Result<RequiredlibrariesRecord> requiredLibraries = ctx.selectFrom(REQUIREDLIBRARIES)
				.where(REQUIREDLIBRARIES.FILEID.eq(rec.get(FILES.FILEID))).fetch();
		Result<ProjectsRecord> project = ctx.selectFrom(PROJECTS).where(PROJECTS.PROJECTID.eq(rec.get(FILES.PROJECTID)))
				.fetch();
		if (project.size() == 1) {
			List<Project> opts = new ArrayList<>(), reqs = new ArrayList<>();
			for (Integer opt : optionalLibraries.getValues(OPTIONALLIBRARIES.PROJECTID)) {
				Optional<Project> p = getProjectFromId(opt);
				if (p.isPresent())
					opts.add(p.get());
			}
			for (Integer req : requiredLibraries.getValues(OPTIONALLIBRARIES.PROJECTID)) {
				Optional<Project> p = getProjectFromId(req);
				if (p.isPresent())
					reqs.add(p.get());
			}
			return Optional.of(
					new ProjectFile(rec, versions.getValues(VERSIONS.VERSION), opts, reqs, newProject(project.get(0))));
		}

		return Optional.empty();

	}

}
