package com.franckyi.modcenter.api;

public class CurseURLFormatter {

	static String CURSE_DOMAIN = "https://minecraft.curseforge.com";

	/**
	 * <p>
	 * Used to format a Curseforge URL. Can be combined with a {@link Project}
	 * URL, for example, to get the full {@link Project} URL.
	 * </p>
	 * 
	 * @param folder
	 *            The folder
	 * @return The full URL
	 */
	public static String format(String folder) {
		return CURSE_DOMAIN + folder;
	}

}
