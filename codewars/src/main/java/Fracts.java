// https://www.codewars.com/kata/54d7660d2daf68c619000d95
public class Fracts {

    public static void main(String[] args) {

    }

    public static String convertFrac(long[][] lst) {
        return "";
    }

    public void test_fractions() throws Exception {
        long[][] lst;
        lst = new long[][] { {1, 2}, {1, 3}, {10, 40} };
        if(!("(6,12)(4,12)(3,12)".equals(Fracts.convertFrac(lst)))) {
            throw new RuntimeException("Wrong");
        }
    }

    // convertFracs [(1, 2), (1, 3), (1, 4)] `shouldBe` [(6, 12), (4, 12), (3, 12)]
}
