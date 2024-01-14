package de.robertz;

// A POJO Surefire test. You get this OOTB. No JUnit/other dependencies needed.
// Surefire will pick up *Test classes and test* methods (configurable)
// Target is mvn test

// Since we also have JUnit in the classpath, Sonar will complain that there is no test in this class.
// Also, IntelliJ will not pick this up as test. Use mvn test.
// But we can run Jupiter test from IntelliJ.
// If using Jupiter it probably doesn't make sense to use Surefire POJO tests alongside though.

public class SUT_SurefirePOJOTest {

	public void testGetHello() {
		SUT s = new SUT();
		assert("Hello World.".equals(s.getHello()));
	}
}