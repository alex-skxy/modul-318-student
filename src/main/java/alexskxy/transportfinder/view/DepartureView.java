package alexskxy.transportfinder.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartureView {
    public final String fromStationName;
    public final String toStationName;
    public final String departure;
}