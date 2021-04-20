import java.util.List;

public class HumanReadableTime {

    public static void main(String[] args) {
        System.out.println(makeReadable(359999));
    }

    public static String makeReadable(int seconds) {
        List<TemporalPlaceValue> placeValues = TemporalPlaceValue.standard();
        for(TemporalPlaceValue t : placeValues) {
            while(seconds / t.placeValue.value >= 1) {
                t.result++;
                seconds -= t.placeValue.value;
            }
        }
        return placeValues.stream()
            .map(p -> String.format("%02d:", p.result))
            .reduce("", (i, p) -> i + p)
            .substring(0, 8);
    }

    static class TemporalPlaceValue {
        public enum PlaceValue {
            HOUR(60 * 60), MINUTE(60), SECOND(1);
            int value;
            PlaceValue(int value) {
                this.value = value;
            }
        }
        private final PlaceValue placeValue;
        public int result = 0;
        public TemporalPlaceValue(PlaceValue placeValue) {
            this.placeValue = placeValue;
        }
        public static List<TemporalPlaceValue> standard() {
            return List.of(new TemporalPlaceValue(PlaceValue.HOUR),
                new TemporalPlaceValue(PlaceValue.MINUTE),
                new TemporalPlaceValue(PlaceValue.SECOND));
        }
    }
}
