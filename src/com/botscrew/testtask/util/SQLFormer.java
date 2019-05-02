package com.botscrew.testtask.util;

public class SQLFormer {
	private SQLFormer() {}
	public static String formSQL(String template, String... values) {
		String[] sqlFragments = template.split("[?]");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sqlFragments.length; i++) {
			sb.append(sqlFragments[i]);
			if (i != values.length) {
				sb.append(values[i]);
			}
		}
		return sb.toString();
	}
}
