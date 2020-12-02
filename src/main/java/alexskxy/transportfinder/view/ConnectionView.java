package alexskxy.transportfinder.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectionView {
    public final String fromStationName;
    public final String departure;
    public final String toStationName;
    public final String arrival;
    public final String duration;
}
