package de.robertz.functional.patterns;

import java.util.function.Consumer;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Data
public class Mobile {

	final int ram;
	final int storage;
	final int camera;
	final String processor;
	final int screenSize;

	public Mobile(MobileBuilder mobileBuilder) { // Reduce ctor args to just builder
		// Copy fields over from builder
		this.ram = mobileBuilder.ram;
		this.storage = mobileBuilder.storage;
		this.camera = mobileBuilder.camera;
		this.processor = mobileBuilder.processor;
		this.screenSize = mobileBuilder.screenSize;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public static class MobileBuilder {
		int ram;
		int storage;
		int camera;
		String processor;
		int screenSize;
		public MobileBuilder with(Consumer<MobileBuilder> buildFields) {
			buildFields.accept(this);
			return this;
		}
		public Mobile build() {
			return new Mobile(this);
		}
	}
}
