package alexskxy.swisstransport.entity;

import lombok.Data;

@Data
public class Connection {
    public ConnectionPoint from;
    public ConnectionPoint to;
    public String duration;
}
