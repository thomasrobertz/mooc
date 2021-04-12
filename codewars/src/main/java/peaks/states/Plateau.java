package peaks.states;

import peaks.Waypoint;

import java.util.List;

public class Plateau extends State {
    private Waypoint plateauStart;
    public Plateau(List<Waypoint> extrema, Waypoint plateauStart) {
        super(extrema);
        this.plateauStart = plateauStart;
    }
    @Override
    public State next(Waypoint next) {
        if (next.attitude == Waypoint.Attitude.CLIMB) {
            return new Peak(extrema);
        }
        if (next.attitude == Waypoint.Attitude.LEVEL) {
            return new Plateau(extrema, plateauStart);
        }
        plateauStart.type = Waypoint.Type.PLATEAU;
        extrema.add(plateauStart);
        return new Bottom(extrema);
    }
}
