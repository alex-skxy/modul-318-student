package alexskxy.swisstransport.entity;

import lombok.Data;

import java.util.List;

@Data
public class StationBoardRoot {
    public Station station;
    public List<StationBoard> stationboard;
}
