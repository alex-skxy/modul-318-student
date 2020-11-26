package alexskxy.swisstransport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransportTest {

    private ITransport testee;

    @Test
    public void locations() {
        testee = new Transport();
        var stations = testee.getStations("Sursee,");

        assertEquals(10, stations.stations.size());
    }

    @Test
    public void stationBoard() {
        testee = new Transport();
        var stationBoard = testee.getStationBoard("Sursee", "8502007");

        assertNotNull(stationBoard);
    }

    @Test
    public void connections() {
        testee = new Transport();
        var connections = testee.getConnections("Sursee", "Luzern");

        assertNotNull(connections);
    }
}
