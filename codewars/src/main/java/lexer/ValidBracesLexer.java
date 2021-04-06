package lexer;

public class ValidBracesLexer {
    static int testsCount = 0;
    public static void main(String[] args) {

        // Sample tests

        assertInput("()");
        assertInput("{}");
        assertInput("[]");
        assertInput("()[]");

        assertInput("(())");
        assertInput("(((())))");
        assertInput("([])");
        assertInput("([({})])");
        assertInput("([{}])");

        assertInput("(())()");
        assertInput("(())([])");

        assertInput("/**/");
        assertInput("(/**/)");
        assertInput("{/**/}");
        assertInput("[/**/]");
        assertInput("(/**/)[]");
        assertInput("()[/**/]");

        assertInput("((/**/))");
        assertInput("([/**/])");
        assertInput("([{/**/}])");

        assertInput("((/**/))()");
        assertInput("(())(/**/)");
        assertInput("((/**/))([])");
        assertInput("(())([/**/])");
        assertInput("((((/**/))))");
        assertInput("([({/**/})])");
        assertInput("([{/**/}])");

        assertInput("/**//**/");
        assertInput("/**//**//**//**/");
        assertInput("(/**//**/)");
        assertInput("{/**//**/}");
        assertInput("[/**//**/]");
        assertInput("(/**//**/)[]");
        assertInput("()[/**//**/]");
        assertInput("(/**/)[/**//**/]");

        assertInput("((/**//**/))");
        assertInput("([/**//**/])");
        assertInput("([{/**//**/}])");

        assertInput("((/**//**/))()");
        assertInput("(())(/**//**/)");
        assertInput("((/**//**/))([])");
        assertInput("(())([/**//**/])");
        assertInput("((((/**//**/))))");
        assertInput("([({/**//**/})])");
        assertInput("([{/**//**/}])");

        assertInput("(/*)(*/)");
        assertInput("/*()*//*()*/");
        assertInput("/**//*([])*//*{}{}{}*//*[{()}]()*/");

        assertInput("(/*(*//**/)");
        assertInput("(/*(*//*x7*/)");
        assertInput("{/*this*//*is*/()/*a*//*test*/}");

        assertInput("(", false);
        assertInput("{", false);
        assertInput("[", false);
        assertInput("()[", false);

        assertInput(")(", false);
        assertInput(")", false);
        assertInput("}", false);
        assertInput("]", false);
        assertInput("]()", false);

        assertInput("(()", false);
        assertInput("((())))", false);
        assertInput("(((()))", false);
        assertInput("(([])", false);
        assertInput("([]))", false);
        assertInput("(([]))()[", false);

        assertInput("/*", false);
        assertInput("/*abc", false);
        assertInput("/*abc()", false);
        assertInput("()/*abc()", false);
        assertInput("()[/*]", false);
        assertInput("()[/*]/*", false);

        assertInput("*/", false);
        assertInput("()(*/)", false);
        assertInput("()()*/", false);

        assertInput("/*/*/", false); // Match only alphanums
        assertInput("((/**********/))", false); // Match only alphanums
        assertInput("{/*This*//*is*/()/*a*//*Test*/}", false); // Match only lower case

        System.out.println(String.format("Passed %d tests.", testsCount));
    }

    public static void assertInput(String input) {
        assertInput(input, true);
    }

    public static void assertInput(String input, boolean expectedResult) {
        String testingString = String.format("Test of '%s'", input);
        if (expectedResult == isValid(input)) {
            System.out.println(String.format("Passed: %s", testingString));
            testsCount++;
            return;
        }
        throw new RuntimeException(String.format("Failed test #%d: %s", testsCount, testingString));
    }

    public static boolean isValid(String input) {
        boolean result = true;
        Engine engine = new Engine();
        Lexer lexer = new Lexer(input);
        while(lexer.hasNext() && result) {
            if (lexer.next().getState() == Lexer.State.SUCCESS) {
                result &= engine.next(lexer.currentLexema());
            }
        }
        //System.out.println("Lexer state " + lexer.getState());
        //System.out.println("Input length " + input.length());
        //System.out.println("Lexer character position " + lexer.getInputCharacterPosition());
        //System.out.println("Stack empty " + engine.isStackEmtpied());
        return result && engine.isStackEmtpied();
    }
}
