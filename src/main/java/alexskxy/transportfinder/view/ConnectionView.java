package alexskxy.transportfinder.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectionView {
    private String fromStationName;
    private String departureTimestamp;
    private String toStationName;
    private String arrivalTimestamp;
    private String duration;
}
