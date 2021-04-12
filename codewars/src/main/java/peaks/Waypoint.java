package peaks;

public class Waypoint {
    public enum Attitude {
        CLIMB, LEVEL, DESCEND
    }
    public enum Type {
        NONE, PEAK, BOTTOM, PLATEAU, VALLEY
    }
    public Waypoint previous = null;
    public Waypoint next = null;
    public int position;
    public int value;
    public Type type = Type.NONE;
    public Waypoint.Attitude attitude = Waypoint.Attitude.LEVEL;
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
                attitude = Waypoint.Attitude.DESCEND;
            }
            if (previous.value < value) {
                attitude = Waypoint.Attitude.CLIMB;
            }
        }
    }
}