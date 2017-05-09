package com.franckyi.modcenter.api;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * The main class for the Mod Center API. This Class is used to get information
 * from the mod center.
 * </p>
 * <ul>
 * <li>Firstly, you have to initialize it using {@link ModCenterAPI#init}
 * method.</li>
 * <li>Then, you can use static methods to get informations you want.</li>
 * <li>Finally, you should use {@link ModCenterAPI#close} method when you stop
 * using the connection.</li>
 * </ul>
 * 
 * @author Franckyi
 *
 */
public class ModCenterAPI {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	private static final String JDBC_CONNECTION = "jdbc:mysql://mysql-franckyi.alwaysdata.net/franckyi_modcenter";
	private static final String JDBC_USERNAME = "franckyi_public";
	private static final String JDBC_PASSWORD = "public";

	private static Connection conn;

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
	 * Returns a {@link ProjectFile} from the database with the given name.
	 * <strong><u>If the file isn't found, it returns
	 * <code>null</code></u></strong>.
	 * </p>
	 * 
	 * @param name
	 *            The file name
	 * @return
	 * @throws SQLException
	 */
	public static ProjectFile getFileFromName(String name) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM files WHERE name LIKE ?");
		stmt.setString(1, "%" + name + "%");
		ResultSet results = stmt.executeQuery();
		if (results.next())
			return new ProjectFile(results);
		else
			return null;
	}

	@Deprecated
	public static List<ProjectFile> getFiles(int page, int count) throws SQLException {
		List<ProjectFile> files = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM files LIMIT ?, ?;");
		stmt.setInt(1, (page - 1) * count);
		stmt.setInt(2, count);
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			files.add(new ProjectFile(results));
		}
		return files;
	}

	/**
	 * <p>
	 * Returns a list of {@link ProjectFile}s from a {@link Project}.
	 * </p>
	 * 
	 * @param project
	 *            The {@link Project}
	 * @return The list of {@link ProjectFile}s from this {@link Project}
	 * @throws SQLException
	 */
	public static List<ProjectFile> getFilesFromProject(Project project) throws SQLException {
		List<ProjectFile> files = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM files WHERE projectId = ? ORDER BY fileId DESC;");
		stmt.setInt(1, project.getProjectId());
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			files.add(new ProjectFile(results));
		}
		return files;
	}

	/**
	 * <p>
	 * Returns a list of {@link ProjectFile}s from a {@link Project} for a
	 * defined version.
	 * </p>
	 * 
	 * @param project
	 *            The {@link Project}
	 * @param version
	 *            The defined version
	 * @return The list of {@link ProjectFile}s from this {@link Project}
	 * @throws SQLException
	 */
	public static List<ProjectFile> getFilesFromProject(Project project, String version) throws SQLException {
		List<ProjectFile> files = new ArrayList<>();
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM files WHERE projectId = ? AND version LIKE ? ORDER BY fileId DESC;");
		stmt.setInt(1, project.getProjectId());
		stmt.setString(2, "%" + version + "%");
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			files.add(new ProjectFile(results));
		}
		return files;
	}

	/**
	 * <p>
	 * Returns the {@link Project} where the given {@link ProjectFile} comes
	 * from. <strong><u>If the project isn't found, it returns
	 * <code>null</code></u></strong>.
	 * </p>
	 * 
	 * @param file
	 *            The file
	 * @return
	 * @throws SQLException
	 */
	public static Project getProjectFromFile(ProjectFile file) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM projects WHERE projectId = ?");
		stmt.setInt(1, file.getProjectId());
		ResultSet results = stmt.executeQuery();
		if (results.next())
			return new Project(results);
		else
			return null;
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
	 *            The sort filter used
	 * @return A list of mod {@link Project}s
	 * @throws SQLException
	 */
	public static List<Project> getProjects(int page, int count, SortedProjectFilter filter) throws SQLException {
		List<Project> projects = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT * FROM projects WHERE projects.projectId IN (SELECT files.projectId FROM files WHERE version LIKE ?) AND (name LIKE ? OR author LIKE ? OR description LIKE ?) AND categories LIKE ? ORDER BY "
						+ filter.getSortFilter().getValue() + toOrder(filter.getOrder()) + " LIMIT ?, ?;");
		stmt.setString(1, "%" + filter.getVersion() + "%");
		stmt.setString(2, "%" + filter.getQuery() + "%");
		stmt.setString(3, "%" + filter.getQuery() + "%");
		stmt.setString(4, "%" + filter.getQuery() + "%");
		stmt.setString(5, "%" + filter.getCategory().getDbKey() + "%");
		stmt.setInt(6, (page - 1) * count);
		stmt.setInt(7, count);
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			projects.add(new Project(results));
		}
		return projects;
	}

	/**
	 * <p>
	 * Returns the number of pages of {@link Project}s for
	 * a certain number of {@link Project}s per page.
	 * </p>
	 * 
	 * @param count
	 *            The number of {@link Project}s per page
	 * @param filter
	 *            The filtering to use in the query. 
	 * @return The number of pages of {@link Project}s containing this keyword
	 * @throws SQLException
	 */
	public static int getProjectsPageNumber(int count, ProjectFilter filter) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT COUNT(*) FROM projects WHERE projects.projectId IN (SELECT files.projectId FROM files WHERE version LIKE ?) AND (name LIKE ? OR author LIKE ? OR description LIKE ?) AND categories LIKE ?");
		stmt.setString(1, "%" + filter.getVersion() + "%");
		stmt.setString(2, "%" + filter.getQuery() + "%");
		stmt.setString(3, "%" + filter.getQuery() + "%");
		stmt.setString(4, "%" + filter.getQuery() + "%");
		stmt.setString(5, "%" + filter.getCategory().getDbKey() + "%");
		ResultSet results = stmt.executeQuery();
		results.next();
		return results.getInt(1) / count + 1;
	}

	public static List<String> getVersions() throws SQLException {
		List<String> list = new ArrayList<>();
		ResultSet set = conn.createStatement().executeQuery("SELECT DISTINCT version FROM files;");
		while (set.next()) {
			if (!(set.getString(1).startsWith("CB") || set.getString(1).equals("-")))
				list.add(set.getString(1));
		}
		list.sort(new VersionComparator());
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
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(JDBC_CONNECTION, JDBC_USERNAME, JDBC_PASSWORD);
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
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(JDBC_CONNECTION, username, password);
	}

	/**
	 * <p>
	 * Executes a user-defined query, with or without parameters. See how works
	 * {@link PreparedStatement} for more informations. The supported parameters
	 * types are : {@link String}, {@link Integer}, {@link Date}, {@link Long},
	 * {@link Float}, {@link Double}, {@link Boolean} and {@link Short}.
	 * </p>
	 * 
	 * @param query
	 *            The SQL query string
	 * @param parameters
	 *            The parameters
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 *             if the parameter type isn't supported
	 */
	public static ResultSet query(String query, Object... parameters) throws SQLException, Exception {
		if (parameters.length == 0)
			return conn.createStatement().executeQuery(query);
		else {
			PreparedStatement stmt = conn.prepareStatement(query);
			for (int i = 1; i <= parameters.length; i++) {
				if (parameters[i] instanceof String)
					stmt.setString(i, (String) parameters[i]);
				else if (parameters[i] instanceof Integer)
					stmt.setInt(i, (Integer) parameters[i]);
				else if (parameters[i] instanceof Date)
					stmt.setDate(i, (Date) parameters[i]);
				else if (parameters[i] instanceof Long)
					stmt.setLong(i, (Long) parameters[i]);
				else if (parameters[i] instanceof Float)
					stmt.setFloat(i, (Float) parameters[i]);
				else if (parameters[i] instanceof Double)
					stmt.setDouble(i, (Double) parameters[i]);
				else if (parameters[i] instanceof Boolean)
					stmt.setBoolean(i, (Boolean) parameters[i]);
				else if (parameters[i] instanceof Short)
					stmt.setShort(i, (Short) parameters[i]);
				else
					throw new Exception("Unknown parameter type at position " + (i - 1));
			}
			return stmt.executeQuery();
		}
	}

	private static String toOrder(boolean order) {
		if (order)
			return " ASC";
		return " DESC";
	}

	public static UpdateResult update(Project project, ProjectFile file) throws SQLException {
		UpdateResult res = new UpdateResult();
		PreparedStatement pstmt;
		ResultSet set;
		pstmt = conn.prepareStatement("SELECT * FROM files WHERE projectId = ? ORDER BY fileId DESC LIMIT 0,1;");
		pstmt.setInt(1, project.getProjectId());
		set = pstmt.executeQuery();
		res.setUpToDate((set.next() && set.getInt(1) == file.getFileId()));
		pstmt = conn.prepareStatement("SELECT * FROM files WHERE type = 'ALPHA' AND projectId = ?;");
		pstmt.setInt(1, project.getProjectId());
		set = pstmt.executeQuery();
		if (set.next())
			res.setLatestAlpha(new ProjectFile(set));
		pstmt = conn.prepareStatement("SELECT * FROM files WHERE type = 'BETA' AND projectId = ?;");
		pstmt.setInt(1, project.getProjectId());
		set = pstmt.executeQuery();
		if (set.next())
			res.setLatestAlpha(new ProjectFile(set));
		pstmt = conn.prepareStatement("SELECT * FROM files WHERE type = 'RELEASE' AND projectId = ?;");
		pstmt.setInt(1, project.getProjectId());
		set = pstmt.executeQuery();
		if (set.next())
			res.setLatestAlpha(new ProjectFile(set));
		return res;
	}
	
	public static Connection getConnection() {
		return conn;
	}

}
