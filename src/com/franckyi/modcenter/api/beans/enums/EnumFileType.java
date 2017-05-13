package com.franckyi.modcenter.api.beans.enums;

import com.franckyi.modcenter.api.beans.ProjectFile;

/**
 * <p>
 * Enum used to qualify a {@link ProjectFile} stability.
 * </p>
 * 
 * @author Franckyi
 *
 */
public enum EnumFileType {

	ANY("", "Any", 0),
	ALPHA("ALPHA", "Alpha", 1),
	BETA("BETA", "Beta", 2),
	RELEASE("RELEASE", "Release", 3);

	private String dbKey, displayText;
	private int level;

	private EnumFileType(String dbKey, String displayText, int level) {
		this.dbKey = dbKey;
		this.displayText = displayText;
		this.level = level;
	}

	/**
	 * @return the dbKey
	 */
	public String getDbKey() {
		return dbKey;
	}

	/**
	 * @return the displayText
	 */
	public String getDisplayText() {
		return displayText;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * <p>
	 * Returns a file type from a file type {@link String} that can be found in
	 * the database.
	 * </p>
	 * 
	 * @param string
	 *            The file type string
	 * @return The file type corresponding to this file type string
	 */
	public static EnumFileType toType(String string) {
		for (EnumFileType type : values()) {
			if (type.getDbKey().equals(string))
				return type;
		}
		return ANY;
	}

}
