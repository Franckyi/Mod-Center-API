package com.franckyi.modcenter.api.misc;

/**
 * <p>
 * Class used to format URLs to connect to the Curseforge website.
 * </p>
 * 
 * @author Franckyi
 *
 */
public class CurseURLFormatter {

	private static final String HOST = "https://minecraft.curseforge.com";
	private static final String FOLDER = "/mc-mods";
	private static final String FILES = "/files";
	private static final String PAGE_PARAMETER = "?page=";
	private static final String FILTER_PARAMETER = "?filter-sort=updated";

	/**
	 * <p>
	 * Returns the URL to the mods project list at the corresponding page.
	 * </p>
	 * 
	 * @param page
	 *            The page
	 * @return The URL
	 */
	public static String format(int page) {
		return HOST + FOLDER + PAGE_PARAMETER + page;
	}

	/**
	 * <p>
	 * Returns the URL to the corresponding folder. Often used to open project's
	 * page or file list.
	 * </p>
	 * 
	 * @param folder
	 *            The folder
	 * @return The URL
	 */
	public static String format(String folder) {
		return HOST + folder;
	}

	/**
	 * <p>
	 * Returns the URL to the corresponding folder at the corresponding page.
	 * Used to open project's file list at a defined page.
	 * </p>
	 * 
	 * @param folder
	 *            The folder
	 * @param page
	 *            The page
	 * @return The URL
	 */
	public static String format(String folder, int page) {
		return HOST + folder + FILES + PAGE_PARAMETER + page;
	}

	/**
	 * Returns the URL to the mods project list, sorted by {@link EnumSortFilter#UPDATED}.
	 * @return The URL
	 */
	public static String formatLatest() {
		return HOST + FOLDER + FILTER_PARAMETER;
	}

}
