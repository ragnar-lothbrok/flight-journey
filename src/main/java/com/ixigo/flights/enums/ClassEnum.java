package com.ixigo.flights.enums;

public enum ClassEnum {

	ECONOMY("Economy"), PREMIUM_ECONOMY("Premium Economy"), BUSINESS("Business");

	private String className;

	private ClassEnum(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

}
