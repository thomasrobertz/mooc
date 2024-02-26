package de.robertz.functional.patterns;

import lombok.Getter;

public class AC {
	@Getter private int temperature = 30;
	public void increaseTemparature() {
		temperature++;
	}
	public void decreaseTemparature() {
		temperature--;
	}

	public interface Command {
		void execute();
	}

	public static class Remote {
		Command command;
		public void setCommand(Command command) {
			this.command = command;
		}
		public void onButtonPressed() {
			command.execute();
		}
	}
}
