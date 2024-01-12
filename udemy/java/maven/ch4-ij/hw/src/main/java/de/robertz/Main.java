package de.robertz;

import org.apache.commons.lang3.StringUtils;

public class Main {
	public static void main(String[] args) {
		System.out.println(
				StringUtils.abbreviate("Hello world!", 10)
		);
	}
}