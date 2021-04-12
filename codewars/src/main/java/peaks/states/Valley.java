package peaks.states;

import peaks.Waypoint;

import java.util.List;

public class Valley extends State {
    private Waypoint valleyStart;
    public Valley(List<Waypoint> extrema, Waypoint valleyStart) {
        super(extrema);
        this.valleyStart = valleyStart;
    }
    @Override
    public State next(Waypoint next) {
        if (next.attitude == Waypoint.Attitude.DESCEND) {
            return new Bottom(extrema);
        }
        if (next.attitude == Waypoint.Attitude.LEVEL) {
            return new Valley(extrema, valleyStart);
        }
        valleyStart.type = Waypoint.Type.VALLEY;
        extrema.add(valleyStart);
        return new Peak(extrema);
    }
}
