package alexskxy.transportfinder.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectionView {
    private String fromStationName;
    private String departure;
    private String toStationName;
    private String arrival;
    private String duration;
}
