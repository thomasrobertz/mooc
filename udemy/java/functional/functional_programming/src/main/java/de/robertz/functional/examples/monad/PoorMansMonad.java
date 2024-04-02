package de.robertz.functional.examples.monad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.AllArgsConstructor;

public class PoorMansMonad {

	static Map<Integer, User> database;

	public static void main(String[] args) {

		database = Map.of(
				34, new User(34, "John", true),
				165, new User(165, "Bill", false));

		getFromDb(34);
		getFromDb(165);
		getFromDb(58);


	}

	static void getFromDb(int id) {
		// TODO apply
		dbSideEffect(id).ifPresentOrElse(u -> System.out.println(u.name + " found."),
				() -> System.out.println("User not found."));
	}

	static Optional<User> dbSideEffect(int userId) {
		if(database.containsKey(userId)) {
			User u = database.get(userId);
			return Optional.of(new User(u.id, u.name, u.hasPermission));
		}
		return Optional.empty();
	}

	static Optional<User> securitySideEffect(User u) {
		if(u.hasPermission) {
			return Optional.of(u);
		}
		return Optional.empty();
	}

	@AllArgsConstructor
	static class User {
		public int id;
		public String name;
		public boolean hasPermission;
	}
}
