package com.franckyi.modcenter.api.misc;

public class CurseURLFormatter {

	private static final String HOST = "https://minecraft.curseforge.com";
	private static final String FOLDER = "/mc-mods";
	private static final String FILES = "/files";
	private static final String PAGE_PARAMETER = "?page=";
	private static final String FILTER_PARAMETER = "?filter-sort=updated";

	public static String format(int page) {
		return HOST + FOLDER + PAGE_PARAMETER + page;
	}

	public static String format(String folder) {
		return HOST + folder;
	}

	public static String format(String projectUrl, int page) {
		return HOST + projectUrl + FILES + PAGE_PARAMETER + page;
	}

	public static String formatLatest() {
		return HOST + FOLDER + FILTER_PARAMETER;
	}

}
