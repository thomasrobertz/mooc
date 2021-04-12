package peaks.states;

import peaks.Waypoint;
import peaks.Waypoints;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class State {
    public List<Waypoint> extrema;
    public State(List<Waypoint> extrema) {
        this.extrema = extrema;
    }
    public abstract State next(Waypoint next);
    public List<Waypoint> getExtremaFiltered(List<Waypoint.Type> types) {
        return extrema.stream().filter(e -> types.contains(e.type)).collect(Collectors.toList());
    }
}
