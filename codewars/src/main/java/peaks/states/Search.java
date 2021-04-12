package peaks.states;
import peaks.Waypoint;

import java.util.ArrayList;
import java.util.List;

public class Search extends State {
    public Search() {
        super(new ArrayList<>());
    }
    public Search(List<Waypoint> extrema) {
        super(extrema);
    }
    @Override
    public State next(Waypoint next) {
        if (next.attitude == Waypoint.Attitude.CLIMB) {
            return new Peak(extrema);
        }
        if (next.attitude == Waypoint.Attitude.DESCEND) {
            return new Bottom(extrema);
        }
        return this;
    }
}