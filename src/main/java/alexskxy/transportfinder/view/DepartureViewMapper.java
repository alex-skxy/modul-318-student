package alexskxy.transportfinder.view;

import alexskxy.swisstransport.entity.StationBoard;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureViewMapper {
    private static final DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

    public static DepartureView mapToView(StationBoard stationBoard) {
        String departureTime = formatter.format(parser.parse(stationBoard.stop.departure));
        return DepartureView.builder()
                .fromStationName(stationBoard.stop.station.name)
                .toStationName(stationBoard.to)
                .departure(departureTime).build();
    }

    public static List<DepartureView> mapToViews(List<StationBoard> stationBoards) {
        return stationBoards.stream().map(DepartureViewMapper::mapToView).collect(Collectors.toList());
    }
}