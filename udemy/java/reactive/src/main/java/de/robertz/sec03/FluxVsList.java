package de.robertz.sec03;

import de.robertz.common.Util;
import de.robertz.sec03.helper.NameGenerator;

public class FluxVsList {
	public static void main(String[] args) {
		// "Traditional" model, we have to wait
		//System.out.println(NameGenerator.nextList(5));

		// With Reactive, even though there is a sleep(),
		// AS LONG AS THERE IS SOMETHING TO DO, it will be processed
		// That's non-blocking!
		// Also:
		// Imagine, we like on of the names and need no more generating of names.
		// In reactive, we can at this point send a cancel (= We can REACT).
		// That's not possible with the traditional model.
		NameGenerator.nextFlux(5).subscribe(Util.subscriber("f"));
	}
}
