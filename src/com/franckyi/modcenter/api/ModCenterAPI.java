package com.franckyi.modcenter.api;

import java.sql.Connection;
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

	private static final String JDBC_CONNECTION_ROOT = "jdbc:mysql://mysql-franckyi.alwaysdata.net/franckyi_modcenter";
	private static final String JDBC_USERNAME_ROOT = "franckyi_public";
	private static final String JDBC_PASSWORD_ROOT = "public";

	private static Connection conn;

	/**
	 * <p>
	 * Initialize the connection between your program and the mod center.
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
	 * number of items on a page. For SQL users, this method executes this query
	 * :<br>
	 * <code>SELECT * FROM projects LIMIT (page-1)*count, count;</code>
	 * </p>
	 * 
	 * @param page
	 *            The page number
	 * @param count
	 *            The number of items on the page
	 * @return A list of mod {@link Project}s
	 * @throws SQLException
	 */
	public static List<Project> getProjects(int page, int count) throws SQLException {
		List<Project> projects = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM projects LIMIT ?, ?;");
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
	 * number of items on a page and the query string. For SQL users, this
	 * method executes this query :<br>
	 * <code>SELECT * FROM projects WHERE name LIKE '%query%' LIMIT (page-1)*count, count;</code>
	 * </p>
	 * 
	 * @param page
	 *            The page number
	 * @param count
	 *            The number of items on the page
	 * @param query
	 *            The query string
	 * @return A list of mod {@link Project}s
	 * @throws SQLException
	 */
	public static List<Project> getProjects(int page, int count, String query) throws SQLException {
		List<Project> projects = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM projects WHERE name LIKE ? LIMIT ?, ?;");
		stmt.setString(1, "%" + query + "%");
		stmt.setInt(2, (page - 1) * count);
		stmt.setInt(3, count);
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
	 * Returns a list of {@link ProjectFile}s from a {@link Project}. For SQL
	 * users, this method executes this query :<br>
	 * <code>SELECT * FROM projects WHERE name LIKE '%query%' LIMIT (page-1)*count, count;</code>
	 * </p>
	 * 
	 * @param project
	 *            The {@link Project}
	 * @return The list of {@link ProjectFile}s from this {@link Project}
	 * @throws SQLException
	 */
	public static List<ProjectFile> getFilesFromProject(Project project) throws SQLException {
		List<ProjectFile> files = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM files WHERE projectId = ?;");
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
	 * Close the connection between your program and the mod center.
	 * 
	 * @throws SQLException
	 */
	public static void close() throws SQLException {
		conn.close();
	}

}
