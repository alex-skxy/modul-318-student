package alexskxy.transportfinder.view;

import alexskxy.swisstransport.entity.Connection;

import java.util.List;
import java.util.stream.Collectors;

public class ConnectionViewMapper {
    public static ConnectionView mapToView(Connection connection) {
        return ConnectionView.builder()
                .fromStationName(connection.from.station.name)
                .departureTimestamp(connection.from.departureTimestamp)
                .toStationName(connection.to.station.name)
                .arrivalTimestamp(connection.to.arrivalTimestamp)
                .duration(connection.duration).build();
    }

    public static List<ConnectionView> mapToViews(List<Connection> connections) {
        return connections.stream().map(ConnectionViewMapper::mapToView).collect(Collectors.toList());
    }
}
