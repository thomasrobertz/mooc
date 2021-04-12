import java.util.*;
import java.util.stream.Collectors;

// https://www.codewars.com/kata/5279f6fe5ab7f447890006a7
public class PickPeaks {

    public static void main(String[] args) {

        //                                      1 1 1 1
        //                  0 1 2 3 4 5 6 7 8 9 0 1 2 3
        //getPeaks(new int[] {3,2,3,6,4,1,2,3,2,1,2,2,2,1}); // 3,7,10
        //                        ^       ^     ^

        System.out.println(getPeaks(new int[] {5,4,8,4,5})); // 3,7,10

        //getPeaks(new int[] {3,2,3,6,4,1,2,3,2,1,2,3});
        //getPeaks(new int[] {1,2,3,6,4,1,2,3,2,1});
        //getPeaks(new int[] {1,2,3,6,6,4,1,2,3,2,1,1,2,3,3,4,4,1});
        //getPeaks(new int[] {1,2,3,3,2,1});
        //new SolutionTest().sampleTests();
    }

    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        Waypoints waypoints = new Waypoints(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        State current = new Search(new ArrayList<>());
        while(waypoints.hasNext()) {
            current = current.next(waypoints.next());
        }
        Map<String, List<Integer>> result = new HashMap<>();
        result.put("pos", new ArrayList<>());
        result.put("peaks", new ArrayList<>());
        current.peaks.stream().forEach(w -> {
            result.get("pos").add(w.position);
            result.get("peaks").add(w.value);
        });
        return result;
    }

    public static class Waypoint {
        enum Attitude {
            CLIMB, LEVEL, DESCEND
        }
        public Waypoint previous = null;
        public Waypoint next = null;
        public int position;
        public int value;
        public Attitude attitude = Attitude.LEVEL;
        public Waypoint(Waypoint previous, int position, int value) {
            this.previous = previous;
            this.position = position;
            this.value = value;
            determineAttitude();
        }
        public void setNext(Waypoint next) {
            this.next = next;
            determineAttitude();
        }
        private void determineAttitude() {
            if (previous instanceof Waypoint) {
                if (previous.value > value) {
                    attitude = Attitude.DESCEND;
                }
                if (previous.value < value) {
                    attitude = Attitude.CLIMB;
                }
            }
        }
    }

    public static class Waypoints {
        List<Waypoint> waypoints = new ArrayList<>();
        int index = 0;
        public Waypoints(List<Integer> elements) {
            Waypoint previous = null;
            int position = 0;
            for(Integer value : elements) {
                if (previous == null) {
                    previous = new Waypoint(null, position, value);
                    this.waypoints.add(previous);
                } else {
                    Waypoint newWaypoint = new Waypoint(previous, position, value);
                    previous.setNext(newWaypoint);
                    this.waypoints.add(newWaypoint);
                    previous = newWaypoint;
                }
                position++;
            }
        }
        public boolean hasNext() {
            return waypoints.size() > index;
        }
        public Waypoint next() {
            Waypoint waypoint = waypoints.get(index);
            index++;
            return waypoint;
        }
    }

    public static abstract class State {
        public List<Waypoint> peaks;
        public State(List<Waypoint> peaks) {
            this.peaks = peaks;
        }
        public abstract State next(Waypoint next);
    }

    public static class Search extends State {
        public Search(List<Waypoint> peaks) {
            super(peaks);
        }
        @Override
        public State next(Waypoint next) {
            if (next.attitude == Waypoint.Attitude.CLIMB) {
                return new Peak(peaks);
            }
            return this;
        }
    }

    public static class Peak extends State {
        public Peak(List<Waypoint> peaks) {
            super(peaks);
        }
        @Override
        public State next(Waypoint next) {
            if (next.attitude == Waypoint.Attitude.LEVEL) {
                return new Plateau(peaks, next.previous);
            }
            if (next.attitude == Waypoint.Attitude.DESCEND) {
                peaks.add(next.previous);
                return new Search(peaks);
            }
            return this;
        }
    }

    public static class Plateau extends State {
        private Waypoint plateauStart;
        public Plateau(List<Waypoint> peaks, Waypoint start) {
            super(peaks);
            plateauStart = start;
        }
        @Override
        public State next(Waypoint next) {
            if (next.attitude == Waypoint.Attitude.CLIMB) {
                return new Peak(peaks);
            }
            if (next.attitude == Waypoint.Attitude.LEVEL) {
                return new Plateau(peaks, plateauStart);
            }
            peaks.add(plateauStart);
            return new Search(peaks);
        }
    }
}
