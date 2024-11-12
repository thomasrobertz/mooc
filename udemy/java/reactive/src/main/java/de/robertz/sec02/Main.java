package de.robertz.sec02;

import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		new LazyStream().noop();
		new LazyStream().terminal();
	}
}
