package alexskxy.swisstransport;

import alexskxy.swisstransport.entity.Connections;
import alexskxy.swisstransport.entity.StationBoardRoot;
import alexskxy.swisstransport.entity.Stations;

public interface ITransport {
    Stations getStations(String query);

    StationBoardRoot getStationBoard(String station, String id);

    Connections getConnections(String fromStation, String toStation);
}
