package de.robertz;

// A POJO Surefire test. You get this OOTB. No JUnit/other dependencies needed.
// Surefire will pick up *Test classes and test* methods (configurable)
// Target is mvn test

public class SUT_SurefirePOJOTest {

	public void testGetHello() {
		SUT s = new SUT();
		assert("Hello World.".equals(s.getHello()));
	}
}