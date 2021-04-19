import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MaxSubarraySum {
    public static void main(String[] args) {
        System.out.println(sequence(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6 (4, -1, 2, 1)
        //System.out.println(sequence(new int[] {-2, 1, -3, -4, -1, 2, 1, -5, 4})); // 6 (4, -1, 2, 1)
    }
    public static int sequence(int[] arr) {
        if(arr.length == 0) {
            return 0;
        }
        AtomicBoolean allNegative = new AtomicBoolean(true);
        AtomicInteger globalMax = new AtomicInteger(0);
        IntStream.range(0, arr.length).forEach(i -> {
            allNegative.set(allNegative.get() && (i < 0));
            IntStream.range(i, arr.length)
                .forEach(j -> {
                    final int subArraySum = Arrays.stream(arr, i, j + 1).sum();
                    if (subArraySum > globalMax.get()) {
                        globalMax.set(subArraySum);
                    }
                });
        });
        if (allNegative.get()) {
            return 0;
        }
        return globalMax.get();
    }
}
