package alexskxy.swisstransport.entity;

import lombok.Data;

@Data
public class Connection {
    public final ConnectionPoint from;
    public final ConnectionPoint to;
    public final String duration;
}
