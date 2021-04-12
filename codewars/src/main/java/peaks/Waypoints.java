package peaks;

import java.util.ArrayList;
import java.util.List;

public class Waypoints {
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
