import org.apache.commons.lang3.StringUtils;

// To compile: 
// javac -classpath .\lib\* .\HelloWorld.java

// To run we also need to specify all used dependencies, since we didn't build a fat jar or similar.
// Note that on windows powershell, we need to quote the classpath components:
// java -classpath '.\lib\*;.\' HelloWorld

public class HelloWorld {
	public static void main(String[] args) {
		System.out.println(StringUtils.capitalize("hello"));
	}
}