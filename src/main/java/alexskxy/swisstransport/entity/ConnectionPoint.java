package alexskxy.swisstransport.entity;

import lombok.Data;

@Data
public class ConnectionPoint {
    public final Station station;
    public final String arrival;
    public final String arrivalTimestamp;
    public final String departure;
    public final String departureTimestamp;
    public final Integer delay;
    public final String Platform;
    public final String RealtimeAvailability;
}
