package com.franckyi.modcenter.api.misc;

import java.util.Comparator;

/**
 * <p>
 * This comparator is used to compare different version strings. For example in
 * the Client, it's used to show an ordered list of versions.
 * </p>
 * 
 * @author Franckyi
 *
 */
public class VersionComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (o1.startsWith("Beta") && o2.startsWith("Beta")) {
			o1 = o1.replace("Beta ", "");
			o2 = o2.replace("Beta ", "");
		} else if (o1.startsWith("Beta"))
			return 1;
		else if (o2.startsWith("Beta"))
			return -1;
		if (o1.endsWith("Snapshot") && o2.endsWith("Snapshot")) {
			o1 = o1.replace("-Snapshot", "");
			o2 = o2.replace("-Snapshot", "");
		} else if (o1.endsWith("Snapshot"))
			o1 = o1.replace("-Snapshot", ".-1");
		else if (o2.endsWith("Snapshot"))
			o2 = o2.replace("-Snapshot", ".-1");
		o1 += (o1.split("\\.").length == 2) ? ".0" : "";
		o2 += (o2.split("\\.").length == 2) ? ".0" : "";
		String[] s1 = o1.split("\\.");
		String[] s2 = o2.split("\\.");
		int[] i1 = new int[s1.length];
		int[] i2 = new int[s2.length];
		for (int i = 0; i < i1.length; i++)
			i1[i] = Integer.parseInt(s1[i]);
		for (int i = 0; i < i2.length; i++)
			i2[i] = Integer.parseInt(s2[i]);
		int min = (i1.length < i2.length) ? i1.length : i2.length;
		for (int i = 0; i < min; i++) {
			if (i1[i] > i2[i])
				return -1;
			if (i2[i] > i1[i])
				return 1;
		}
		return 0;
	}

}
