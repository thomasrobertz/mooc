package peaks.states;

import peaks.Waypoint;

import java.util.List;

public class Peak extends State {
    public Peak(List<Waypoint> peaks) {
        super(peaks);
    }
    @Override
    public State next(Waypoint next) {
        if (next.attitude == Waypoint.Attitude.LEVEL) {
            return new Plateau(extrema, next.previous);
        }
        if (next.attitude == Waypoint.Attitude.DESCEND) {
            next.previous.type = Waypoint.Type.PEAK;
            extrema.add(next.previous);
            return new Bottom(extrema);
        }
        return this;
    }
}
