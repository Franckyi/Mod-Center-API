package com.franckyi.modcenter.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	private static final String JDBC_CONNECTION_ROOT = "jdbc:mysql://mysql-franckyi.alwaysdata.net/franckyi_modcenter";
	private static final String JDBC_USERNAME_ROOT = "franckyi_public";
	private static final String JDBC_PASSWORD_ROOT = "public";

	private static Connection conn;

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
		conn = DriverManager.getConnection(JDBC_CONNECTION_ROOT, JDBC_USERNAME_ROOT, JDBC_PASSWORD_ROOT);
	}

	/**
	 * <p>
	 * Returns a list of {@link Project}s, depending on the page number and the
	 * number of items on a page. The results are sorted by a
	 * {@link SortFilter}.
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
	public static List<Project> getProjects(int page, int count, SortFilter filter) throws SQLException {
		List<Project> projects = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM projects ORDER BY " + filter.getFilter().value
				+ toOrder(filter.getOrder()) + " LIMIT ?, ?;");
		stmt.setInt(1, (page - 1) * count);
		stmt.setInt(2, count);
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			projects.add(new Project(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4),
					results.getDate(5), results.getString(6), results.getString(7), results.getString(8)));
		}
		return projects;
	}

	/**
	 * <p>
	 * Returns a list of {@link Project}s, depending on the page number, the
	 * number of items on a page and the query string. The results are sorted by
	 * a {@link SortFilter}.
	 * </p>
	 * 
	 * @param page
	 *            The page number
	 * @param count
	 *            The number of items on the page
	 * @param filter
	 *            The sort filter used
	 * @param query
	 *            The query string
	 * @return A list of mod {@link Project}s
	 * @throws SQLException
	 */
	public static List<Project> getProjects(int page, int count, SortFilter filter, String query) throws SQLException {
		List<Project> projects = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT * FROM projects WHERE name LIKE ? OR author LIKE ? OR description LIKE ? ORDER BY "
						+ filter.getFilter().value + toOrder(filter.getOrder()) + " LIMIT ?, ?;");
		stmt.setString(1, "%" + query + "%");
		stmt.setString(2, "%" + query + "%");
		stmt.setString(3, "%" + query + "%");
		stmt.setInt(4, (page - 1) * count);
		stmt.setInt(5, count);
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			projects.add(new Project(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4),
					results.getDate(5), results.getString(6), results.getString(7), results.getString(8)));
		}
		return projects;
	}

	@Deprecated
	public static List<ProjectFile> getFiles(int page, int count) throws SQLException {
		List<ProjectFile> files = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM files LIMIT ?, ?;");
		stmt.setInt(1, (page - 1) * count);
		stmt.setInt(2, count);
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			files.add(new ProjectFile(results.getInt(1), results.getString(2), results.getString(3),
					results.getString(4), results.getDate(5), results.getString(6), results.getInt(7),
					results.getString(8), results.getInt(9)));
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
			files.add(new ProjectFile(results.getInt(1), results.getString(2), results.getString(3),
					results.getString(4), results.getDate(5), results.getString(6), results.getInt(7),
					results.getString(8), results.getInt(9)));
		}
		return files;
	}

	/**
	 * <p>
	 * Returns a list of {@link ProjectFile}s from a {@link Project} for a
	 * defined {@link MCVersion}.
	 * </p>
	 * 
	 * @param project
	 *            The {@link Project}
	 * @param version
	 *            The defined {@link MCVersion}
	 * @return The list of {@link ProjectFile}s from this {@link Project}
	 * @throws SQLException
	 */
	public static List<ProjectFile> getFilesFromProject(Project project, MCVersion version) throws SQLException {
		List<ProjectFile> files = new ArrayList<>();
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM files WHERE projectId = ? AND version = ? ORDER BY fileId DESC;");
		stmt.setInt(1, project.getProjectId());
		stmt.setString(2, version.dbText);
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			files.add(new ProjectFile(results.getInt(1), results.getString(2), results.getString(3),
					results.getString(4), results.getDate(5), results.getString(6), results.getInt(7),
					results.getString(8), results.getInt(9)));
		}
		return files;
	}

	/**
	 * <p>
	 * Returns the number of pages of {@link Project}s, for a certain number of
	 * {@link Project}s per page.
	 * </p>
	 * 
	 * @param count
	 *            The number of {@link Project}s per page
	 * @return The number of pages of {@link Project}s
	 * @throws SQLException
	 */
	public static int getProjectsPages(int count) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("SELECT COUNT(*) FROM projects;");
		results.next();
		return results.getInt(1) / count;
	}

	/**
	 * <p>
	 * Returns the number of pages of {@link Project}s containing a keyword, for
	 * a certain number of {@link Project}s per page.
	 * </p>
	 * 
	 * @param count
	 *            The number of {@link Project}s per page
	 * @param query
	 *            The keyword
	 * @return The number of pages of {@link Project}s containing this keyword
	 * @throws SQLException
	 */
	public static int getProjectsPages(int count, String query) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT COUNT(*) FROM projects WHERE name LIKE ? OR author LIKE ? OR description LIKE ?;");
		stmt.setString(1, "%" + query + "%");
		stmt.setString(2, "%" + query + "%");
		stmt.setString(3, "%" + query + "%");
		ResultSet results = stmt.executeQuery();
		results.next();
		return results.getInt(1) / count;
	}

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

	private static String toOrder(boolean order) {
		if (order)
			return " ASC";
		return " DESC";
	}

}
