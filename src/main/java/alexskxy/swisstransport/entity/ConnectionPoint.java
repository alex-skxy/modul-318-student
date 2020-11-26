package alexskxy.swisstransport.entity;

import lombok.Data;

@Data
public class ConnectionPoint {
    public Station station;
    public String arrival;
    public String arrivalTimestamp;
    public String departure;
    public String departureTimestamp;
    public Integer delay;
    public String Platform;
    public String RealtimeAvailability;
}
