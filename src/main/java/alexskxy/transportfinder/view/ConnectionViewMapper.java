package alexskxy.transportfinder.view;

import alexskxy.swisstransport.entity.Connection;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ConnectionViewMapper {
    private static final DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    public static ConnectionView mapToView(Connection connection) {
        String departure = formatter.format(
                parser.parse(connection.from.departure));
        String arrival = formatter.format(
                parser.parse(connection.to.arrival));

        return ConnectionView.builder()
                .fromStationName(connection.from.station.name)
                .departure(departure)
                .toStationName(connection.to.station.name)
                .arrival(arrival)
                .duration(connection.duration).build();
    }

    public static List<ConnectionView> mapToViews(List<Connection> connections) {
        return connections.stream().map(ConnectionViewMapper::mapToView).collect(Collectors.toList());
    }
}
