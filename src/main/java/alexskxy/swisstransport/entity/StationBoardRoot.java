package alexskxy.swisstransport.entity;

import lombok.Data;

import java.util.List;

@Data
public class StationBoardRoot {
    public final Station station;
    public final List<StationBoard> stationboard;
}
