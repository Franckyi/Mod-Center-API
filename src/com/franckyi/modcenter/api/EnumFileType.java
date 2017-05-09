package com.franckyi.modcenter.api;

/**
 * <p>
 * Enum used to qualify a {@link ProjectFile} stability.
 * </p>
 * 
 * @author Franckyi
 *
 */
public enum EnumFileType {

	ANY("", "Any"), ALPHA("ALPHA", "Alpha"), BETA("BETA", "Beta"), RELEASE("RELEASE", "Release");

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

	public static EnumFileType toType(String string) {
		for(EnumFileType type : values()){
			if(type.getDbKey().equals(string))
				return type;
		}
		return ANY;
	}

}
