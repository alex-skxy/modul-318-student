package alexskxy.swisstransport.entity;

import lombok.Data;

@Data
public class Station {
    public final String id;
    public final String name;
    public final Integer score;
    public final Coordinate coordinate;
    public final Double distance;
}
