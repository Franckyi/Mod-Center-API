package com.franckyi.modcenter.api.beans.enums;

import java.util.ArrayList;
import java.util.List;

public enum EnumCategory {
	
	ANY("", "Any"),
	ADDONS("mc-addons", "--- ADDONS ---"),
	APPLIED_ENERGISTICS_ADDONS("applied-energistics-2", "--- Applied Energistics Addons", ADDONS),
	BLOOD_MAGIC_ADDONS("blood-magic", "--- Blood Magic Addons", ADDONS),
	BUILDCRAFT_ADDONS("addons-buildcraft", "--- Buildcraft Addons", ADDONS),
	FORESTRY_ADDONS("addons-forestry", "--- Forestry Addons", ADDONS),
	INDUSTRIAL_CRAFT_ADDONS("addons-industrialcraft", "--- Industrial Craft Addons", ADDONS),
	THAUMCRAFT_ADDONS("addons-thaumcraft", "--- Thaumcraft Addons", ADDONS),
	THERMAL_EXPANSION_ADDONS("addons-thermalexpansion", "--- Thermal Expansion Addons", ADDONS),
	TINKERS_CONSTRUCT_ADDONS("addons-tinkers-construct", "--- Tinker's Construct Addons", ADDONS),
	ADVENTURE_RPG("adventure-rpg", "Adventure and RPG"),
	ARMOR_TOOLS_WEAPONS("armor-weapons-tools", "Armor, Tools and Weapons"),
	COSMETIC("cosmetic", "Cosmetic"),
	FOOD("mc-food", "Food"),
	MAGIC("magic", "Magic"),
	MAP_INFORMATION("map-information", "Map and Information"),
	REDSTONE("redstone", "Redstone"),
	SERVER_UTILITY("server-utility", "Server Utility"),
	STORAGE("storage", "Storage"),
	TECHNOLOGY("technology", "--- TECHNOLOGY ---"),
	ENERGY_TECHNOLOGY("technology-energy", "--- Energy", TECHNOLOGY),
	TRANSPORT_TECHNOLOGY("technology-item-fluid-energy-transport", "--- Energy, Fluid and Item Transport", TECHNOLOGY),
	FARMING_TECHNOLOGY("technology-farming", "--- Farming", TECHNOLOGY),
	GENETICS_TECHNOLOGY("technology-genetics", "--- Genetics", TECHNOLOGY),
	PLAYER_TRANSPORT_TECHNOLOGY("technology-player-transport", "--- Player Transport", TECHNOLOGY),
	PROCESSING_TECHNOLOGY("technology-processing", "--- Processing", TECHNOLOGY),
	WORLD_GEN("world-gen", "--- WORLD GEN ---"),
	BIOMES_WORLD("world-biomes", "--- Biomes", WORLD_GEN),
	DIMENSIONS_WORLD("world-dimensions", "--- Dimensions", WORLD_GEN),
	MOBS_WORLD("world-mobs", "--- Mobs", WORLD_GEN),
	ORES_RESOURCES_WORLD("world-ores-resources", "--- Ores and Resources", WORLD_GEN),
	STRUCTURES_WORLD("world-structures", "--- Structures", WORLD_GEN),
	API_LIBRARY("library-api", "API and Library"),
	MISCELLANEOUS("mc-miscellaneous", "Miscellaneous");
	
	private static final String SPLITTER_CHAR = " ";
	
	private String dbKey, displayText;
	private EnumCategory superCategory;
	
	private EnumCategory(String dbKey, String displayText) {
		this.displayText = displayText;
		this.dbKey = dbKey;
	}
	
	private EnumCategory(String dbKey, String displayText, EnumCategory superCategory) {
		this(dbKey, displayText);
		this.superCategory = superCategory;
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
	 * @return the superCategory
	 */
	public EnumCategory getSuperCategory() {
		return superCategory;
	}
	
	public static EnumCategory toCategory(String url) {
		for(EnumCategory cat : values())
			if(url.equals(cat.getDbKey()))
				return cat;
		return null;
	}
	
	public static List<EnumCategory> format(String categories){
		List<EnumCategory> list = new ArrayList<>();
		for(String cat : categories.split(SPLITTER_CHAR)) {
			EnumCategory c = toCategory(cat);
			if(c != null)
				list.add(c);
		}
		return list;	
	}
	
	public static String format(List<EnumCategory> categories){
		String s = "";
		for(EnumCategory cat : categories)	
			s += cat.getDbKey() + SPLITTER_CHAR;
		return s.substring(0, s.length() - 1);
	}
	
	public String toUrl() {
		return this.getDbKey() + "/" + ((superCategory != null) ? this.getSuperCategory().getDbKey() : "");
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return displayText;
	}
	
	

}
