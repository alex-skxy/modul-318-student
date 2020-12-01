package alexskxy.transportfinder.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartureView {
    private String fromStationName;
    private String toStationName;
    private String departure;
}