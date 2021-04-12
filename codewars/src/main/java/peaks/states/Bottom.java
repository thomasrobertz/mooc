package peaks.states;

import peaks.Waypoint;

import java.util.List;

public class Bottom extends State {
    public Bottom(List<Waypoint> extrema) {
        super(extrema);
    }
    @Override
    public State next(Waypoint next) {
        if (next.attitude == Waypoint.Attitude.LEVEL) {
            return new Valley(extrema, next.previous);
        }
        if (next.attitude == Waypoint.Attitude.CLIMB) {
            next.previous.type = Waypoint.Type.BOTTOM;
            extrema.add(next.previous);
            return new Peak(extrema);
        }
        return this;
    }
}
