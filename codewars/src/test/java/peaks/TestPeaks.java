package peaks;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPeaks {

    @Test
    public void testPeak() {
        List<Waypoint> result = run(new int[] {5, 5, 8, 4, 3});
        assertEquals(1, result.size());
        Waypoint first = result.get(0);
        assertEquals(8, first.value);
        assertEquals(Waypoint.Type.PEAK, first.type);
    }

    @Test
    public void testBottom() {
        List<Waypoint> result = run(new int[] {10, 9, 8, 9, 9});
        assertEquals(1, result.size());
        Waypoint first = result.get(0);
        assertEquals(8, first.value);
        assertEquals(Waypoint.Type.BOTTOM, first.type);
    }

    @Test
    public void testPlateau() {
        List<Waypoint> result = run(new int[] {5, 5, 8, 8, 4, 3});
        assertEquals(1, result.size());
        Waypoint first = result.get(0);
        assertEquals(8, first.value);
        assertEquals(Waypoint.Type.PLATEAU, first.type);
    }

    @Test
    public void testValley() {
        List<Waypoint> result = run(new int[] {5, 5, 1, 1, 2, 3});
        assertEquals(1, result.size());
        Waypoint first = result.get(0);
        assertEquals(1, first.value);
        assertEquals(Waypoint.Type.VALLEY, first.type);
    }

    @Test
    public void testPeakAndBottom() {
        List<Waypoint> result = run(new int[] {5, 4, 8, 4, 3});
        assertEquals(2, result.size());
        Waypoint first = result.get(0);
        assertEquals(4, first.value);
        assertEquals(Waypoint.Type.BOTTOM, first.type);
        Waypoint second = result.get(1);
        assertEquals(8, second.value);
        assertEquals(Waypoint.Type.PEAK, second.type);
    }

    @Test
    public void testPlateauAndBottom() {
        List<Waypoint> result = run(new int[] {5, 5, 8, 8, 3, 4});
        assertEquals(2, result.size());
        Waypoint first = result.get(0);
        assertEquals(8, first.value);
        assertEquals(Waypoint.Type.PLATEAU, first.type);
        Waypoint second = result.get(1);
        assertEquals(3, second.value);
        assertEquals(Waypoint.Type.BOTTOM, second.type);
    }

    @Test
    public void testAll() {
        List<Waypoint> result = run(new int[] {2, 3, 2, 1, 2, 3, 3, 2, 1, 1, 9});
        assertEquals(4, result.size());

        Waypoint peak = result.get(0);
        assertEquals(3, peak.value);
        assertEquals(Waypoint.Type.PEAK, peak.type);

        Waypoint bottom = result.get(1);
        assertEquals(1, bottom.value);
        assertEquals(Waypoint.Type.BOTTOM, bottom.type);

        Waypoint plateau = result.get(2);
        assertEquals(3, plateau.value);
        assertEquals(Waypoint.Type.PLATEAU, plateau.type);

        Waypoint valley = result.get(3);
        assertEquals(1, valley.value);
        assertEquals(Waypoint.Type.VALLEY, valley.type);
    }

    public List<Waypoint> run(int[] input) {
        Engine engine = new Engine(testWaypoints(input));
        return engine.run().extrema;
    }

    public Waypoints testWaypoints(int[] input) {
        return new Waypoints(Arrays.stream(input).boxed().collect(Collectors.toList()));
    }
}
