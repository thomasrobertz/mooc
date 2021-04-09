import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// https://www.codewars.com/kata/5279f6fe5ab7f447890006a7
public class PickPeaks {

    public static void main(String[] args) {

    }

    public static Map<String, List<Integer>> getPeaks(int[] arr) {

        return null;
    }

    /*
    * In this kata, you will write a function that returns the positions and the values
    * of the "peaks" (or local maxima) of a numeric array.
For example, the array arr = [0, 1, 2, 5, 1, 0] has a peak
*  at position 3 with a value of 5 (since arr[3] equals 5).

The output will be returned as a ``Map<String,List>with two key-value pairs:"pos"and"peaks".
* If there is no peak in the given array, simply return {"pos" => [], "peaks" => []}`.

Example: pickPeaks([3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 3]) should return
* {pos: [3, 7], peaks: [6, 3]} (or equivalent in other languages)

All input arrays will be valid integer arrays (although it could still be empty),
*  so you won't need to validate the input.

The first and last elements of the array will not be considered as peaks
* (in the context of a mathematical function, we don't know what is after and before and therefore, we don't know if it is a peak or not).

Also, beware of plateaus !!! [1, 2, 2, 2, 1] has a peak while [1, 2, 2, 2, 3] and [1, 2, 2, 2, 2] do not.
* In case of a plateau-peak, please only return the position and value of the beginning of the plateau. For example: pickPeaks([1, 2, 2, 2, 1]) returns {pos: [1], peaks: [2]} (or equivalent in other languages)


    * */
    public static class SolutionTest {

        private static String[] msg = {"should support finding peaks",
                "should support finding peaks, but should ignore peaks on the edge of the array",
                "should support finding peaks; if the peak is a plateau, it should only return the position of the first element of the plateau",
                "should support finding peaks; if the peak is a plateau, it should only return the position of the first element of the plateau",
                "should support finding peaks, but should ignore peaks on the edge of the array"};

        private static int[][] array = {{1,2,3,6,4,1,2,3,2,1},
                {3,2,3,6,4,1,2,3,2,1,2,3},
                {3,2,3,6,4,1,2,3,2,1,2,2,2,1},
                {2,1,3,1,2,2,2,2,1},
                {2,1,3,1,2,2,2,2}};

        private static int[][] posS  = {{3,7},
                {3,7},
                {3,7,10},
                {2,4},
                {2},};

        private static int[][] peaksS = {{6,3},
                {6,3},
                {6,3,2},
                {3,2},
                {3}};

        public void sampleTests() {
            for (int n = 0 ; n < msg.length ; n++) {
                final int[] p1 = posS[n], p2 = peaksS[n];
                Map<String,List<Integer>> expected = new HashMap<String,List<Integer>>() {{
                    put("pos",   Arrays.stream(p1).boxed().collect(Collectors.toList()));
                    put("peaks", Arrays.stream(p2).boxed().collect(Collectors.toList()));
                }},
                        actual = PickPeaks.getPeaks(array[n]);
                if(expected != actual) {
                    throw new RuntimeException("Wrong");
                }
            }
        }
    }

}
