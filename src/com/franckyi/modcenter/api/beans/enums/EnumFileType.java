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

	ANY("", "Any"),
	ALPHA("ALPHA", "Alpha"),
	BETA("BETA", "Beta"),
	RELEASE("RELEASE", "Release");

	private String dbKey, displayText;

	private EnumFileType(String dbKey, String displayText) {
		this.dbKey = dbKey;
		this.displayText = displayText;
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
