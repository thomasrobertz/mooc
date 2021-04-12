package peaks;

import peaks.states.Search;
import peaks.states.State;

public class Engine {
    public State current;
    private Waypoints waypoints;
    public Engine(Waypoints waypoints) {
        current = new Search();
        this.waypoints = waypoints;
    }
    public State run() {
        while(waypoints.hasNext()) {
            current = current.next(waypoints.next());
        }
        return current;
    }
}
